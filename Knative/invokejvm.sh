#!/bin/bash

curl -w '\n\nEstablish Connection: %{time_connect}s\nTotal: %{time_total}s\n' -H "Host: jvm-spring-demo.default.example.com" http://localhost:8080
