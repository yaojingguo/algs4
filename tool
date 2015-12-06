#!/bin/bash

if [ $# -eq 0 ]; then
  cat << EOF
usage sample: $0 Quick < data/tiny.txt
EOF
  exit 1
fi

classpath=build/classes/main

java -cp $classpath $@
