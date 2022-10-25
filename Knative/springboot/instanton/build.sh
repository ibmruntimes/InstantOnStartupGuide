#/bin/bash

podman build -t instanton-spring-demo-base . || exit 1
podman run --name instanton-spring-demo-checkpointrun --privileged -it instanton-spring-demo-base || exit 1
podman wait instanton-spring-demo-checkpointrun || exit 1
podman commit instanton-spring-demo-checkpointrun instanton-spring-demo-restorerun || exit 1
podman rm instanton-spring-demo-checkpointrun || exit 1
podman rmi instanton-spring-demo-base || exit 1
