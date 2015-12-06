#!/bin/bash

if [ $# -eq 0 ]; then
  cat << EOF
usage sample: $0 DirectedDFS data/tinyDG.txt 2
EOF
  exit 1
fi

classpath=build/classes/main

java -cp $classpath $@
