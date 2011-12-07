#!/usr/bin/env bash

if [ ! -f build.gradle ];then
	echo "please start the script from the root directory of the project" 1>&2
	exit -1
fi


if [ "$HDFS_FILE" == "" ]; then
	echo "HDFS_FILE must be set" 1>&2
	exit -1
fi


if [ "$RESULT_FILE" == "" ]; then
	echo "RESULT_FILE must be set" 1>&2
	exit -1
fi


# remove local file if it already exists
if [ -f $LOCAL_FILE ]; then
	rm $LOCAL_FILE
fi

if [ "${SF}" == "" ];then
	echo "SF must be set" 1>&2
	exit -1
fi


# create the local file with tpch
mkdir -p build
cd build
echo "dbgen: dbgen -T P -s ${SF} -f" 1>&2
dbgen -T P -s ${SF} -f
echo "dbgen done" 1>&2
cd ..

echo "removing samplefile from hadoop" 1>&2
echo hadoop fs -rmr $HDFS_FILE
hadoop fs -rmr $HDFS_FILE

echo hadoop fs -moveFromLocal "$LOCAL_FILE" "$HDFS_FILE"
hadoop fs -moveFromLocal "$LOCAL_FILE" "$HDFS_FILE"