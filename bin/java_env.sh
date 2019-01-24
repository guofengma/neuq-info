#!/bin/bash

export JAVA_HOME=/usr/java/jdk1.8.0_202
export MAVEN_HOME=/home/apache-maven-3.3.9
export PATH=${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}

java_max_heap_size=128M
java_initail_heap_size=128M
java_young_heap_size=50M
