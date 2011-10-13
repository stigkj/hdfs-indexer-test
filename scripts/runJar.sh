#!/usr/bin/env bash

SAMPLE_FILE_HDFS="/test/lineitem.tbl"
export SF=0.1

scripts/setupHdfs.sh
if [ $? -ne 0 ]; then
    echo "setting up the hdfs failed."
    exit
fi

# use first jar in the libs directory and hope that it is the right one
JAR=`ls build/libs/*.jar -1t|head -n 1`
USE_INDEX=true
JAVA_MAX=512
hadoop jar ${JAR} com.freshbourne.hdfs.index.test.Main "${SAMPLE_FILE_HDFS}" ${USE_INDEX} -Dmapred.child.java.opts=-Xmx${JAVA_MAX}M


# make sure indexer was successfull
if [ $? -ne 0 ];then
	echo "hdfs-indexer failed!"
	exit -1
fi

# output the results
hadoop fs -cat /csv_output/*



