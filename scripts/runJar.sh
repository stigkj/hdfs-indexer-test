#!/usr/bin/env bash

SAMPLE_FILE_HDFS="/test/lineitem.tbl"
RUN_SUMMARY="build/run_summary.csv"
export SF=1.0

scripts/setupHdfs.sh
if [ $? -ne 0 ]; then
    echo "setting up the hdfs failed."
    exit
fi

# use first jar in the libs directory and hope that it is the right one
JAR=`ls build/libs/*.jar -1t|head -n 1`
USE_INDEX=true
JAVA_MAX=1536

START=$(date +%s)
hadoop jar ${JAR} de.rwhq.hdfs.index.test.Main "${SAMPLE_FILE_HDFS}" ${USE_INDEX} -Dmapred.child.java.opts=-Xmx${JAVA_MAX}M
END=$(date +%s)
DIFF=$(( $END - $START ))
echo "${SF},${USE_INDEX},${JAVA_MAX},${DIFF}" > $RUN_SUMMARY

# make sure indexer was successfull
if [ $? -ne 0 ];then
	echo "hdfs-indexer failed!"
	exit -1
fi

# output the results
hadoop fs -cat /csv_output/*



