#!/bin/bash

classpath=build/classes/main

java -cp $classpath $@
# sample: ./tool.sh DirectedDFS algs4-data/tinyDG.txt 2
