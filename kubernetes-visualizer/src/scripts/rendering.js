/* global extractVersion, truncate, matchObjects */

const CANVAS_NODES = '.nodesbar .canvas';
const CANVAS_CLUSTER = '.cluster .canvas';

const NODE_SPACE = 30;
const ENTITY_HEIGHT = 95;

const SRV_POD_SPACE_HOR = 190;
const POD_SPACE_HOR = 130;
const DEPL_MIN_LEFT = 900;
const DEPL_POD_SPACE = 200;

const GROUP_VERTICAL_SPACE = 10;
const GROUP_VERTICAL_LAYER_SPACE = -10;

const CONN_LINE_WIDTH = 3;
const CONN_LINE_RADIUS = 3;

const COLORS_SVC = [
    '#009939',
    '#7A0063',
];
const COLORS_DPL = [
    '#3369E8',
    '#FFB521',
];

/**
 * Render all groups to the supplied jsPlumb instance.
 * @param {Array} groups  The array of groups.
 * @param {Object} jsPlumbInstance The jsPlumb instance
 */
function renderGroups(groups, jsPlumbInstance) {
    const canvas = document.querySelector(CANVAS_CLUSTER);
    let y = 0;
    groups.forEach(group => {
        let groupDiv = '<div class="group">';

        if (group.services) {
            groupDiv += renderServices(group.services, y);
            y += group.services.length * 1.1 * ENTITY_HEIGHT + GROUP_VERTICAL_LAYER_SPACE;
        }
        if (group.pods) {
            groupDiv += renderPods(group.pods, y);
            y += ENTITY_HEIGHT + GROUP_VERTICAL_LAYER_SPACE;
        }
        if (group.deployments) {
            groupDiv += renderDeployments(group.deployments, group.pods, y);
            y += group.deployments.length * 1.1 * ENTITY_HEIGHT + GROUP_VERTICAL_LAYER_SPACE;
        }

        groupDiv += '</div>';
        canvas.insertAdjacentHTML('beforeend', groupDiv);

        y += GROUP_VERTICAL_SPACE;

        if (!group.pods) {
            return;
        }

        if (group.deployments) {
            connectDeployments(group.deployments, group.pods, jsPlumbInstance);
        }

        if (group.services) {
            connectServices(group.services, group.pods, jsPlumbInstance);
        }
    });
    canvas.setAttribute('style', `height: ${y}px`);
}

/**
 * Render pods relative to the supplied yOffset.
 * @param {Array} pods The pods to render.
 * @param {number} yOffset The y axis offset to render the deployments with.
 */
function renderPods(pods, yOffset) {
    let x = 0;
    let renderedPods = '';
    pods.forEach(pod => {
        const name = pod.metadata.name;
        const version = pod.metadata.labels.version;
        let phase = pod.status.phase ? pod.status.phase.toLowerCase() : '';

        if ('deletionTimestamp' in pod.metadata) {
            phase = 'terminating';
        }

        const podIp = pod.status.podIP;

        const entity =
            `<div class="window pod ${phase}" title="${name}" id="pod-${name}"
            style="left: ${x + SRV_POD_SPACE_HOR}px; top: ${yOffset}px">
            <span>
            v.${extractVersion(pod.spec.containers[0].image)}
            ${version ? `<br/>${version}` : ''}<br/><br/>
            ${name ? truncate(name, 28) : 'No name'}<br/><br/>
            ${podIp ? `<em>${podIp}</em>` : `<em>${phase}</em>`}
            </span>
            </div>`;
        renderedPods += entity;

        x += POD_SPACE_HOR;
    });
    return renderedPods;
}

/**
 * Render services relative to the supplied yOffset.
 * @param {Array} deployments The deployments to render.
 * @param {number} yOffset The y axis offset to render the deployments with.
 */
function renderServices(services, yOffset) {
    let renderedServices = '';
    services.forEach((service, index) => {
        const name = service.metadata.name;
        const version = service.metadata.labels.version;
        const phase = service.status.phase ? service.status.phase.toLowerCase() : '';
        const externalIps = service.spec.externalIPs ? `${service.spec.externalIPs[0]}:${service.spec.ports[0].port}` : undefined;
        const clusterIp = service.spec.clusterIP;
        const loadBalancer = service.status.loadBalancer && service.status.loadBalancer.ingress
            ? service.status.loadBalancer.ingress[0].ip
            : undefined;
        const y = yOffset + index * 1.1 * ENTITY_HEIGHT;

        const entity =
            `<div class="window wide service ${phase}" title="${name}" id="service-${name}" style="top: ${y}px">
            <span>
            <div>${name}</div>
            ${version ? `<br/>${version}` : ''}
            ${externalIps ? `<br/><br/><a href="http://${externalIps}" target="_blank" rel="noreferrer nofollow">${externalIps}</a>` : ''}
            ${clusterIp ? `<br/><br/>${clusterIp}` : ''}
            ${loadBalancer ? `<br/><a href="http://${loadBalancer}" target="_blank" rel="noreferrer nofollow">${loadBalancer}</a>` : ''}
            </span>
            </div>`;
        renderedServices += entity;
    });

    return renderedServices;
}

/**
 * Render deployments relative to the supplied yOffset.
 * @param {Array} deployments The deployments to render.
 * @param {Array} pods The pods in group to calculate x offset.
 * @param {number} yOffset The y axis offset to render the deployments with.
 */
function renderDeployments(deployments, pods, yOffset) {
    let renderedDeployments = '';
    const podsCount = pods ? pods.length : 0;

    deployments.forEach((deployment, index) => {
        const name = deployment.metadata.name;
        const version = deployment.metadata.labels.version;
        const phase = deployment.status.phase ? deployment.status.phase.toLowerCase() : '';

        const x = getDeploymentLeftOffset(deployment, podsCount);
        const y = yOffset + index * 1.1 * ENTITY_HEIGHT;

        const entity =
            `<div class="window wide deployment ${phase}" title="${name}" id="deployment-${name}"
            style="left: ${x}px; top: ${y}px">
            <span>
            <div>${name}</div>
            <br/>
            <div class="replicas">Replicas: ${deployment.spec.replicas}</div>
            ${version ? `<br/>${version}` : ''}
            </span>
            </div>`;

        renderedDeployments += entity;
    });

    return renderedDeployments;
}

/**
 * Calculate the left offset according to number of pods.
 * @param {Object} deployment The deployment.
 * @param {number} podsCount The number of pods in group.
 */
function getDeploymentLeftOffset(deployment, podsCount) {
    const calculatedReplicaLeft = DEPL_POD_SPACE + (deployment.status.replicas * POD_SPACE_HOR);
    const calculatedPodsLeft = DEPL_POD_SPACE + (podsCount * POD_SPACE_HOR);

    let left;
    if (DEPL_MIN_LEFT > calculatedReplicaLeft && DEPL_MIN_LEFT > calculatedPodsLeft) {
        left = DEPL_MIN_LEFT;
    } else if (calculatedReplicaLeft > DEPL_MIN_LEFT && calculatedReplicaLeft > calculatedPodsLeft) {
        left = calculatedReplicaLeft;
    } else {
        left = calculatedPodsLeft;
    }
    return left;
}


/**
 * Connect deployments and pods with jsPlumb if image versions match.
 * @param {Array} deployments The deployments.
 * @param {Array} pods The pods.
 * @param {Object} jsPlumbInstance The jsPlumb instance.
 */
function connectDeployments(deployments, pods, jsPlumbInstance) {
    deployments.forEach((deployment, i) => {
        pods.forEach(pod => {
            if (extractVersion(deployment.spec.template.spec.containers[0].image) !== extractVersion(pod.spec.containers[0].image)) {
                return;
            }
            jsPlumbInstance.connect({
                source: `deployment-${deployment.metadata.name}`,
                target: `pod-${pod.metadata.name}`,
                anchors: ['Left', 'Bottom'],
                paintStyle: { lineWidth: CONN_LINE_WIDTH, strokeStyle: COLORS_DPL[i & 1] },
                endpointStyle: { fillStyle: COLORS_DPL[i & 1], radius: CONN_LINE_RADIUS },
            });
        });
    });
}

/**
 * Connect services and pods with jsPlumb if labels and selectors match.
 * @param {Array} services The services.
 * @param {Array} pods The pods.
 * @param {Object} jsPlumbInstance The jsPlumb instance.
 */
function connectServices(services, pods, jsPlumbInstance) {
    services.forEach((service, i) => {
        pods.forEach(pod => {
            if (!matchObjects(pod.metadata.labels, service.spec.selector)) {
                return;
            }

            jsPlumbInstance.connect({
                source: `service-${service.metadata.name}`,
                target: `pod-${pod.metadata.name}`,
                anchors: ['Right', 'Top'],
                paintStyle: { lineWidth: CONN_LINE_WIDTH, strokeStyle: COLORS_SVC[i & 1] },
                endpointStyle: { fillStyle: COLORS_SVC[i & 1], radius: CONN_LINE_RADIUS },
            });
        });
    });
}

/**
 * Get Node provider from provider ID.
 * Default to RaspberryPi.
 *
 * @param {Object} node The node.
 * @returns Identified provider name or 'pi'.
 */
function getNodeProvider(node) {
    if (!node || !node.spec || !node.spec.providerID) {
        return 'vagrant';
    }

    const provider = node.spec.providerID.split(':')[0];
    console.log(provider);
    switch (provider) {
    case 'gce':
        return 'gce';
    default:
        return 'vagrant';
    }
}

/**
 * Render cluster nodes.
 */
function renderNodes(nodes) {
    let x = 0;
    const nodesbar = document.querySelector(CANVAS_NODES);

    nodes.forEach(node => {
        let ready;
        for (let j = 0; j < node.status.conditions.length; j++) {
            const condition = node.status.conditions[j];

            if (condition.type === 'Ready') {
                ready = (condition.status === 'True' ? 'ready' : 'not-ready');
                break;
            }
        }

        const provider = getNodeProvider(node);

        const nodeElement =
            `<div>
            <a href="http://${node.metadata.name}:4194/" target="_blank" rel="noreferrer nofollow"
            id="node-${node.metadata.name}" class="window node ${ready}" title="${node.metadata.name}" style="left: ${x}px">
            ${provider ? `<img src="assets/providers/${provider}.png" class="provider-logo" />` : ''}
            <span><p class="nodetitle">Node</p><br/>
            ${truncate(node.metadata.name, 12)}</span>
            </a>
            </div>`;

        nodesbar.insertAdjacentHTML('beforeend', nodeElement);

        x += 93 + NODE_SPACE;
    });
}

/**
 * Hide error notification.
 * @param {HTMLElement} element The element.
 */
function hideError(element) {
    if (element.classList.contains('hide')) {
        return;
    }

    addClass(element, 'hide');
}

/**
 * Show error notification.
 * @param {HTMLElement} element The element.
 * @param {Object} error Error object.
 */
function showError(element, errorObject) {
    removeClass(element, 'hide');
    if (errorObject) {
        const messageElement = element.getElementsByClassName('message')[0];
        if (errorObject.error) {
            messageElement.innerHTML = 'No connection';
        } else if (errorObject.timeout) {
            messageElement.innerHTML = 'Timeout exceeded';
        } else {
            messageElement.innerHTML = `${errorObject.httpRequest.status}: ${errorObject.httpRequest.statusText}`;
        }
    }
}

/**
 * Remove class from element.
 * @param {HTMLElement} element The element.
 * @param {string} className The class name to remove.
 */
function removeClass(element, className) {
    element.className = element.className.replace(new RegExp(`(?:^|\\s)${className}(?!\\S)`), '');
}

/**
 * Add class to element.
 * @param {HTMLElement} element The element.
 * @param {string} className The class name to add.
 */
function addClass(element, className) {
    element.className = `${element.className} ${className}`;
}
