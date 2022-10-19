#!/bin/sh

bash -c 'while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' localhost:9080)" != "200" ]]; do sleep .00001; done'
date +"Response Received: %s.%N"

