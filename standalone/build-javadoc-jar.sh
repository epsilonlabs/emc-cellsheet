#!/usr/bin/env sh

# For Java 8
#JAVADOC_FLAGS="-Xdoclint:none"
JAVADOC_FLAGS=""

for srcjar in target/*-sources.jar; do
    rm -rf tmpsrc tmpjavadoc
    mkdir tmpsrc tmpjavadoc

    pushd tmpsrc
    unzip "../$srcjar"
    javadoc $JAVADOC_FLAGS -subpackages org.eclipse.epsilon -d ../tmpjavadoc
    popd

    pushd tmpjavadoc
    TARGET="../${srcjar%-sources.jar}-javadoc.jar"
    rm -f "$TARGET"
    find . -mindepth 1 -type d -print0 | xargs -0 zip -r "$TARGET"
    popd
done
