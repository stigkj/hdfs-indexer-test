#!/usr/bin/env bash

export LOCAL_FILE="build/lineitem.tbl"

if [ "$HDFS_FILE" == "" ]; then
	echo "HDFS_FILE must be set" 1>&2
	exit -1
fi


if [ "$RESULT_FILE" == "" ]; then
	echo "RESULT_FILE must be set" 1>&2
	exit -1
fi

# make sure we are in the right directory for the relative paths in the rest of the script to work
if [ ! -f build.gradle ];then
	echo "please start the script from the root directory of the project" 1>&2
	exit -1
fi

# remove local file if it already exists
if [ -f $LOCAL_FILE ]; then
	rm $LOCAL_FILE
fi

# create the local file
scripts/tpch.sh
if [ $? -ne 0 ];then
	echo "script/tpch.sh returned unsuccessful. Aborting." 1>&2
	exit -1
fi


echo "removing csv_output and samplefile from hadoop" 1>&2
hadoop fs -rmr /csv_output
hadoop fs -rmr $HDFS_FILE
hadoop fs -put "$LOCAL_FILE" "$HDFS_FILE"

# exit 0 # put fails if $HDFS_FILE does already exist
