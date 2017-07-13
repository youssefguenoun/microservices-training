#!/bin/bash
echo "## Starting kubernetes visualizer"
kubectl proxy -w=src/
