/**
Copyright 2014 Google Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
/* global jsPlumb, renderGroups, renderNodes, forProperty, matchObjects, addClass, removeClass */

const UPDATE_INTERVAL = 3000;
const REQUEST_TIMEOUT = 2000;

let pods;
let services;
let deployments;
let nodes;
let groups;

jsPlumb.ready(() => {
    const clusterInstance = jsPlumb.getInstance();

    setDefaults(clusterInstance);
    draw(clusterInstance);

    setInterval(() => {
        draw(clusterInstance);
    }, UPDATE_INTERVAL);
});

function draw(clusterInstance) {
    pods = [];
    services = [];
    deployments = [];
    nodes = [];
    groups = [];

    loadData().then(() => {
        document.querySelector(CANVAS_CLUSTER).innerHTML = '';
        document.querySelector(CANVAS_NODES).innerHTML = '';

        groupByName();
        clusterInstance.batch(() => {
            clusterInstance.detachEveryConnection();
            clusterInstance.deleteEveryEndpoint();

            renderNodes(nodes);
            renderGroups(groups, clusterInstance);
        });
    });
}

function setDefaults(clusterInstance) {
    clusterInstance.importDefaults({
        Connector: ['Flowchart', { cornerRadius: 5 }],
    });
}

function loadData() {
    const requests = [];

    const podErrorElement = document.getElementById('pod-error');
    const deploymentErrorElement = document.getElementById('deployment-error');
    const serviceErrorElement = document.getElementById('service-error');
    const nodeErrorElement = document.getElementById('node-error');

    const podsReq = getJson('/api/v1/pods?labelSelector=visualize%3Dtrue')
        .then((data) => {
            pods = data.items;
            hideError(podErrorElement);
        }, (error) => {
            showError(podErrorElement, error);
        });
    requests.push(podsReq);

    const deploymentsReq = getJson('/apis/extensions/v1beta1/namespaces/default/deployments/?labelSelector=visualize%3Dtrue')
        .then((data) => {
            deployments = data.items;
            hideError(deploymentErrorElement);
        }, (error) => {
            showError(deploymentErrorElement, error);
        });
    requests.push(deploymentsReq);

    const servicesReq = getJson('/api/v1/services?labelSelector=visualize%3Dtrue')
        .then((data) => {
            services = data.items;
            hideError(serviceErrorElement);
        }, (error) => {
            showError(serviceErrorElement, error);
        });
    requests.push(servicesReq);

    const nodesReq = getJson('/api/v1/nodes')
        .then((data) => {
            nodes = data.items;
            hideError(nodeErrorElement);
        }, (error) => {
            showError(nodeErrorElement, error);
        });
    requests.push(nodesReq);

    return Promise.all(requests);
}

function getJson(url) {
    const promise = new Promise((resolve, reject) => {
        const httpRequest = new XMLHttpRequest();

        httpRequest.onload = () => {
            if (httpRequest.status === 200) {
                const data = JSON.parse(httpRequest.responseText);

                resolve(data);
            } else {
                reject({ httpRequest });
            }
        };

        httpRequest.onerror = error => {
            reject({ httpRequest, error });
        };

        httpRequest.ontimeout = timeout => {
            reject({ httpRequest, timeout });
        };

        httpRequest.open('GET', url, true);
        httpRequest.timeout = REQUEST_TIMEOUT;
        httpRequest.send();
    });

    return promise;
}

/**
 * Group all services, pods and deployments into groups array.
 */
function groupByName() {
    services.forEach(insertService);
    pods.forEach(insertPod);
    deployments.forEach(insertDeployment);
}

/**
 * Insert service into groups array according to the spec.selector property.
 *
 * @param {Object} service The service.
 */
function insertService(service) {
    if (!service || !service.spec || !service.spec.selector) {
        return;
    }
    const selector = service.spec.selector;

    const groupIndex = getGroupIndex(selector);

    if (groupIndex > -1) {
        groups[groupIndex].services.push(service);
    } else {
        groups.push({
            identifier: selector,
            services: [service],
        });
    }
}

/**
 * Insert pod into groups array according to the metadata.labels property.
 *
 * @param {Object} pod The pod.
 */
function insertPod(pod) {
    if (!pod || !pod.metadata || !pod.metadata.labels) {
        return;
    }

    const labels = pod.metadata.labels;

    const groupIndex = getGroupIndex(labels);

    if (groupIndex > -1) {
        if (!groups[groupIndex].pods) {
            groups[groupIndex].pods = [];
        }
        groups[groupIndex].pods.push(pod);
    } else {
        sanitizePodLabels(labels);
        groups.push({
            identifier: labels,
            pods: [pod],
        });
    }
}

/**
 * Remove unique identifiers in pod labels.
 * GCE adds a pod-template-hash to Pods created with a deployment.
 * This breaks grouping capabilities and is not needed elsewhere.
 *
 * @param {Object} labels The labels.
 */
function sanitizePodLabels(labels) {
    forProperty(labels, key => {
        if (key.search('-hash') > -1) {
            /* eslint-disable no-param-reassign */
            delete labels[key];
            /* eslint-enable no-param-reassign */
        }
    });
}

/**
 * Insert deployments into groups array according to the
 * generated selector.matchLabels property.
 *
 * @param {Object} deployment The deployment.
 */
function insertDeployment(deployment) {
    if (
        !deployment ||
        !deployment.spec.selector ||
        !deployment.spec.selector.matchLabels ||
        !deployment.spec.selector.matchLabels.app ||
        !deployment.metadata.name
    ) {
        return;
    }

    const labels = deployment.spec.selector.matchLabels;
    const groupIndex = getGroupIndex(labels);

    if (groupIndex > -1) {
        if (!groups[groupIndex].deployments) {
            groups[groupIndex].deployments = [];
        }
        groups[groupIndex].deployments.push(deployment);
    } else {
        groups.push({
            identifier: labels,
            deployments: [deployment],
        });
    }
}

/**
 * Find array index of group by matching object with identifier.
 * If not found return -1.
 *
 * @param {Object} object The object to match with.
 */
function getGroupIndex(object) {
    for (let index = 0; index < groups.length; index++) {
        if (matchObjects(object, groups[index].identifier)) {
            return index;
        }
    }

    return -1;
}
