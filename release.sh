#!/bin/bash

lein build
aws s3 cp ./target/ s3://proofficecalculator.com/ --recursive \
  --exclude "classes/*" \
  --exclude "cljsbuild-crossover/*" \
  --exclude "stale/*" \
  --exclude "js/out/*"
