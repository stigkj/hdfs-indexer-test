#!/usr/bin/env bash

SAMPLE_FILE_HDFS="/test/lineitem.tbl"
RUN_SUMMARY="build/run_summary.csv"
export SF=0.5

# use first jar in the libs directory and hope that it is the right one
JAR=`ls build/libs/*.jar -1t|head -n 1`
USE_INDEX=true
JAVA_MAX=1024
JAVA_MIN=1024

rm $RESULT_FILE
scripts/setup.sh

if [ $? -ne 0 ]; then
    echo "setting up the hdfs failed."
    exit
fi

# run
scripts/baseHadoopRun.sh

# make sure indexer was successfull
if [ $? -ne 0 ];then
	echo "hdfs-indexer failed!"
	exit -1
fi

# output the results
hadoop fs -cat /csv_output/*



