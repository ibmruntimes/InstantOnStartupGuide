#!/bin/sh

###############################################################################
# Copyright 2023, IBM Corp.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
###############################################################################

curl -OLJSks https://github.com/eclipse-openj9/openj9/files/8774222/criuseccompprofile.json.txt
mv criuseccompprofile.json.txt criuseccompprofile.json

podman build -f Containerfiles/Containerfile.ubi8.unprivileged.rh9 -t instantoncheckpoint:ubi8 . || exit 1
podman run --name checkpointrun --privileged -it instantoncheckpoint:ubi8 || exit 1
podman wait checkpointrun || exit 1
podman commit checkpointrun restorerun || exit 1
podman rm checkpointrun || exit 1

