## Kubernetes/Container Engine Visualizer

This is a simple visualizer for use with the Kubernetes API.

![Screenshot][screenshot]

### Usage:
   * First install a Kubernetes or Container Engine Cluster
   * Clone this repository
   * Run the script `sh run.sh` or start the proxy manually with `kubectl proxy -w=src/`

### Prerequisites
The visualizer uses labels to organize the visualization. To enable visualization of kubernetes entities set `visualize` to `true`.

Connections and grouping is done as follows.

  * Pods are grouped with services if labels match service selectors.

  * A Service is connected to a pod when the selector matches.

  * Deployments are grouped with pods when they are responsible for maintaining the pods.

Here follows minimized `.yaml` files to show the configuration, only for examples.

Service configuration

```yaml
apiVersion: v1
kind: Service
metadata:
  name: hello-kubernetes-svc
  labels:
    visualize: "true"
spec:
  selector:
    app: hello-kubernetes-pod
```

Deployment configuration

```yaml
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: hello-kubernetes-deployment
  labels:
    visualize: "true"
spec:
  template:
    metadata:
      labels:
        app: hello-kubernetes-pod
        visualize: "true"
```

[screenshot]: visualizer.gif
