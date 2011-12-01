#!/usr/bin/env bash

# make sure we are in the right directory for the relative paths in the rest of the script to work
if [ ! -f build.gradle ];then
	echo "please start the script from the root directory of the project" 1>&2
	exit -1
fi

. scripts/configure.sh

if [ "$RESULT_FILE" == "" ]; then
	echo "RESULT_FILE must be set" 1>&2
	exit -1
fi

if [ "$RESULT_APPEND" == "" ]; then
	echo "RESULT_APPEND must be set" 1>&2
	exit -1
fi


if [ "$BUILD_TPCH" == "" ]; then
    echo "BUILD_TPCH must be set" 1>&2
	exit -1
fi

if ! $RESULT_APPEND; then
    rm "$RESULT_FILE"
fi

# setup
if $BUILD_TPCH; then
    scripts/setup.sh
fi

for (( i=1; i <= RUNS; i++ ));do
	scripts/baseHadoopRun.sh
done

