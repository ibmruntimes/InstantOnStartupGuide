#/bin/bash

podman build -t instanton-ol-spring-demo-base . || exit 1
podman run --name instanton-ol-spring-demo-checkpointrun --privileged --env WLP_CHECKPOINT=applications -it instanton-ol-spring-demo-base || exit 1
podman wait instanton-ol-spring-demo-checkpointrun || exit 1
podman commit instanton-ol-spring-demo-checkpointrun instanton-ol-spring-demo-restorerun || exit 1
podman rm instanton-ol-spring-demo-checkpointrun || exit 1
podman rmi instanton-ol-spring-demo-base || exit 1
