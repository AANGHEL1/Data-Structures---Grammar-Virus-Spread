#!/bin/sh

if [ $# -lt 1 ]; then
    echo "Usage: $0 ClassName"
    exit
fi

if [ -e /c ]; then
    SEP=";"
else
    SEP=":"
fi

mkdir -p build

CLASSPATH=build${SEP}src${SEP}lib/algs4.jar

printandrun () {
    echo "$@" >&2
    "$@" || exit $?
}

compilemaybe () {
    if [ \! -e src/"$1".java ]; then
        echo "Error: $1.java not found."
        exit
    fi
    if [ \! -e build/"$1".class -o src/"$1".java -nt build/"$1".class ]; then
        printandrun javac -d build/ -Xlint:all,-unchecked -cp $CLASSPATH src/"$1".java
    fi
}
for class in src/*.java; do
    compilemaybe "$(echo "$class" | sed 's@.*/@@;s/\..*//')"
done
printandrun java -ea -cp $CLASSPATH "$@"
