#!/bin/bash

shopt -s expand_aliases
alias kubectl="minikube kubectl --"

scaled_to_zero() {
  out=`kubectl get pod 2>&1`
  if [ "$out" == "No resources found in default namespace." ]
  then
    return 1
  else
    return 0
  fi
}

wait_until_scaled_to_zero() {
  echo "Waiting until pods are scaled to zero"
  while (scaled_to_zero -eq 1)
  do
    sleep 5
  done
  echo "Pods are scaled to zero"
}

wait_until_scaled_to_zero

for iter in {1..1}
do
  echo "Iter: $iter"

  echo ""
  echo ""
  echo "Invoking JVM"
  ./invokejvm.sh
  wait_until_scaled_to_zero

  echo ""
  echo ""
  echo "Invoking Native Image"
  ./invokenativeimage.sh
  wait_until_scaled_to_zero

  echo ""
  echo ""
  echo "Invoking InstantOn"
  ./invokeinstanton.sh
  wait_until_scaled_to_zero

  echo ""
  echo ""
  echo "Invoking InstantOn on OL"
  ./invokeinstanton-ol.sh
  wait_until_scaled_to_zero

done

