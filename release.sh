#!/bin/bash

lein build
aws s3 cp ./target/ s3://proofficecalculator.com/ --recursive
