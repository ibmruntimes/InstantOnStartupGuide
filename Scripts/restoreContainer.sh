#!/bin/sh

docker run --rm --cap-add=ALL --privileged -it restorerun criu restore -D ./checkpointData --shell-job
