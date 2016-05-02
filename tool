#!/usr/bin/env bash

if [ $# -eq 0 ]; then
  cat << EOF
usage sample: $0
EOF
  exit 1
fi

classpath=build/classes/main
classname=$1
shift

if [[ $classname =~ ^yao.* ]]; then
  java -cp $classpath "$classname" "$@"
elif [[ $classname =~ ^aofa.* ]]; then
  java -cp $classpath "$classname" "$@"
else
  java -cp $classpath "edu.princeton.cs.algs4.$classname" "$@"
fi
