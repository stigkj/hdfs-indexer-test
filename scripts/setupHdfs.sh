#!/usr/bin/env bash

SAMPLE_FILE="build/lineitem.tbl"
SAMPLE_FILE_HDFS="/test/lineitem.tbl"

# make sure we are in the right directory for the relative paths in the rest of the script to work
if [ ! -f build.gradle ];then
	echo "please start the script from the root directory of the project" 1>&2
	exit -1
fi

# download the sample file, if it doesn't exist already
if [ ! -f $SAMPLE_FILE ]; then
  scripts/tpch.sh
fi


if [ $? -ne 0 ];then
	echo "script/tpch.sh returned unsuccessful. Aborting." 1>&2
	exit -1
fi

echo "removing csv_output and samplefile from hadoop" 1>&2
hadoop fs -rmr /csv_output
hadoop fs -put $SAMPLE_FILE "$SAMPLE_FILE_HDFS"
exit 0 # put fails if $SAMPLE_FILE_HDFS does already exist
