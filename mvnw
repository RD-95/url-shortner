#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.
# Maven Wrapper Script
# ----------------------------------------------------------------------------

if [ -z "$MAVEN_WRAPPER_JAR" ]; then
  MAVEN_WRAPPER_JAR=".mvn/wrapper/maven-wrapper.jar"
fi

exec mvn "$@"
