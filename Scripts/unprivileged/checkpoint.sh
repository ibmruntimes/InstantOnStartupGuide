#!/bin/sh

###############################################################################
# Copyright 2022, IBM Corp.
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

# hack to bump up the pid by 100
for i in {1..100}
do
    ./Scripts/pidplus.sh
done

mkdir checkpointData
java -XX:+EnableCRIUSupport -Dhelloinstanton_heartbeat="true" HelloInstantOn 1>out 2>/dev/null </dev/null

exit 0
