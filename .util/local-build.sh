#!/usr/bin/env bash
mvn install -Djgitver.use-version=3.0.0-SNAPSHOT -P javadoc,sources
