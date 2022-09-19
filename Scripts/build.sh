#!/bin/sh

docker build -f Dockerfiles/Dockerfile.ubuntu20 -t instantondemo:ub20 .
docker build -f Dockerfiles/Dockerfile.checkpoint -t instantoncheckpoint:ub20 .
docker run --name checkpointrun --cap-add=ALL --privileged -it instantoncheckpoint:ub20
docker commit checkpointrun restorerun
docker rm checkpointrun