#!/usr/bin/env bash

SAMPLE_FILE_HDFS="/test/lineitem.tbl"

scripts/setupHdfs.sh
if [ $? -ne 0 ]; then
    echo "setting up the hdfs failed."
    exit
fi

# used * instead of the concrete name to 1. be version independent and 2. so that also jars generated in the
# vagrant env work (they are generated as vagrant.*.jar)
hadoop jar build/libs/*.jar com.freshbourne.hdfs.index.test.Main "${SAMPLE_FILE_HDFS}"

# make sure indexer was successfull
if [ $? -ne 0 ];then
	echo "hdfs-indexer failed!"
	exit
fi

# output the results
hadoop fs -cat /csv_output/*



