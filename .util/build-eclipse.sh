#!/usr/bin/env bash
set -e
OLD_VERSION=0.0.1-SNAPSHOT
NEW_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
echo "Old Verison: ${OLD_VERSION}"
echo "New Verison: ${NEW_VERSION}"
mvn -f plugins/eclipse tycho-versions:set-version -DnewVersion=$NEW_VERSION -Djgitver.skip=true
mvn -f plugins/eclipse package
echo "Resetting all versions to ${NEW_VERSION}"
mvn -f plugins/eclipse tycho-versions:set-version -DnewVersion=$OLD_VERSION -Djgitver.skip=true
