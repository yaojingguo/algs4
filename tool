#!/bin/bash

if [ $# -eq 0 ]; then
  cat << EOF
usage sample: $0
EOF
  exit 1
fi

classpath=build/classes/main
classname=$1
shift

echo "args: $@"
java -cp $classpath "edu.princeton.cs.algs4.$classname" $@
