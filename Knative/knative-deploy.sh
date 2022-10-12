#!/bin/bash

shopt -s expand_aliases
alias kubectl="minikube kubectl --"

kubectl apply -f https://github.com/knative/serving/releases/download/knative-v1.7.2/serving-crds.yaml
kubectl apply -f https://github.com/knative/serving/releases/download/knative-v1.7.2/serving-core.yaml
kubectl apply -f https://github.com/knative/net-kourier/releases/download/knative-v1.7.0/kourier.yaml

sleep 10

kubectl patch configmap/config-network \
  --namespace knative-serving \
  --type merge \
  --patch '{"data":{"ingress-class":"kourier.ingress.networking.knative.dev"}}'

kubectl apply -f knative-config.yaml
