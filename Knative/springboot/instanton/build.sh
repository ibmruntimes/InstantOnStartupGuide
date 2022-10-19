#/bin/bash

podman build -t instanton-spring-demo-base . || exit 1
podman run --name instanton-spring-demo-checkpointrun --privileged -it instanton-spring-demo-base || exit 1
podman wait instanton-spring-demo-checkpointrun || exit 1
podman commit instanton-spring-demo-checkpointrun instanton-spring-demo-restorerun || exit 1
podman rm instanton-spring-demo-checkpointrun || exit 1

#podman run --rm --cap-add=CHECKPOINT_RESTORE --cap-add=SYS_PTRACE --cap-add=NET_ADMIN -v /proc/sys/kernel/ns_last_pid:/proc/sys/kernel/ns_last_pid --security-opt seccomp=criuseccompprofile.json --name restore_run restorerun
