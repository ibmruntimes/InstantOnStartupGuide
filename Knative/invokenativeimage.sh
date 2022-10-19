#!/bin/bash

curl -w '\n\nEstablish Connection: %{time_connect}s\nTotal: %{time_total}s\n' -H "Host: nativeimage-spring-demo.default.example.com" http://localhost:8080
