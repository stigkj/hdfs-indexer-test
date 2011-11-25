#!/usr/bin/env bash

# OUTPUT: SF, USE_INDEX, RUN, JAVA_MAX, TIME

NUMBER_OF_RUNS=4

. scripts/configure.sh

if [ "$RESULT_FILE" == "" ]; then
	echo "RESULT_FILE must be set" 1>&2
	exit -1
fi

# setup
rm "$RESULT_FILE"
scripts/setup.sh

# without index
export USE_INDEX=false
scripts/baseHadoopRun.sh

# with index
export USE_INDEX=true
for (( i=1; i <= NUMBER_OF_RUNS; i++ ));do
	scripts/baseHadoopRun.sh
done

