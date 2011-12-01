#!/bin/bash

if [ "$JAR" == "" ]; then
	echo "JAR must be set" 1>&2
	exit -1
fi

if [ "$HDFS_FILE" == "" ]; then
	echo "HDFS_FILE must be set" 1>&2
	exit -1
fi

if [ "$USE_INDEX" == "" ]; then
	echo "USE_INDEX must be set" 1>&2
	exit -1
fi

if [ "$JAVA_MAX" == "" ]; then
	echo "JAVA_MAX must be set" 1>&2
	exit -1
fi

if [ "$JAVA_MIN" == "" ]; then
	echo "JAVA_MIN must be set" 1>&2
	exit -1
fi

if [ "$RESULT_FILE" == "" ]; then
	echo "RESULT_FILE must be set" 1>&2
	exit -1
fi

hadoop fs -rmr /csv_output

START=$(date +%s)
hadoop jar ${JAR} de.rwhq.hdfs.index.test.Main "${HDFS_FILE}" ${USE_INDEX} -Dmapred.child.java.opts="-Xmx${JAVA_MAX}m -Xms${JAVA_MIN}m"
END=$(date +%s)
DIFF=$(( $END - $START ))
echo "${SF},${USE_INDEX},$JAVA_MIN,${JAVA_MAX},${DIFF}" >> "$RESULT_FILE"
