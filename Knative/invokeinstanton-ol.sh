#!/bin/bash

curl -w '\n\nEstablish Connection: %{time_connect}s\nTotal: %{time_total}s\n' -H "Host: instanton-ol-spring-demo-restorerun.default.example.com" http://localhost:8080
