#!/bin/bash

shopt -s expand_aliases
alias kubectl="minikube kubectl --"

kubectl apply -f springboot/jvm/jvm-spring-demo.yaml
kubectl apply -f springboot/instanton/instanton-spring-demo-restorerun.yaml
kubectl apply -f springboot/nativeimage/nativeimage-spring-demo.yaml

kubectl port-forward service/kourier -n kourier-system 8080:80 &
