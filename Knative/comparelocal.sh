#!/bin/bash

JVM_SPRING_DEMO="localhost/jvm-spring-demo"
NATIVEIMAGE_SPRING_DEMO="localhost/nativeimage-spring-demo"
INSTANTON_SPRING_DEMO="localhost/instanton-spring-demo-restorerun"
INSTANTON_OL_SPRING_DEMO="localhost/instanton-ol-spring-demo-restorerun"

for iter in {1..1}
do
  echo "Iter: $iter"

  echo ""
  echo "Invoking JVM"
  ./curlloop.sh&
  date +"Container Start: %s.%N"
  podman run --rm --name jvm-demo -d -p 9080:9080 $JVM_SPRING_DEMO >/dev/null 2>/dev/null
  sleep 10
  podman stop jvm-demo >/dev/null 2>/dev/null

  echo ""
  echo "Invoking Native Image"
  ./curlloop.sh&
  date +"Container Start: %s.%N"

  podman run --rm --name nativeimage-demo -d -p 9080:9080 $NATIVEIMAGE_SPRING_DEMO >/dev/null 2>/dev/null
  sleep 3
  podman stop nativeimage-demo >/dev/null 2>/dev/null

  echo ""
  echo "Invoking InstantOn"
  ./curlloop.sh&
  date +"Container Start: %s.%N"
  podman run --rm --name instanton-demo -d -p 9080:9080 --cap-add=CHECKPOINT_RESTORE --cap-add=NET_ADMIN --cap-add=SYS_PTRACE $INSTANTON_SPRING_DEMO >/dev/null 2>/dev/null

  sleep 3
  podman stop instanton-demo >/dev/null 2>/dev/null

  echo ""
  echo "Invoking InstantOn on OL"
  ./curlloop.sh&
  date +"Container Start: %s.%N"
  podman run --rm --name instanton-ol-demo -d -p 9080:9080 --env CRIU_EXTRA_ARGS="--skip-file-rwx-check" --cap-add=CHECKPOINT_RESTORE --cap-add=NET_ADMIN --cap-add=SYS_PTRACE $INSTANTON_OL_SPRING_DEMO >/dev/null 2>/dev/null

  sleep 3
  podman stop instanton-ol-demo >/dev/null 2>/dev/null

done

