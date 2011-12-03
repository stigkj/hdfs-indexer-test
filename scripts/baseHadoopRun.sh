#!/bin/bash

if [ "$JAR" == "" ]; then
	echo "JAR must be set" 1>&2
	exit -1
fi

if [ "$HDFS_FILE" == "" ]; then
	echo "HDFS_FILE must be set" 1>&2
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

if [ "$CLASS" == "" ]; then
	echo "CLASS must be set" 1>&2
	exit -1
fi

if [ "$RUN_COUNT" == "" ]; then
    echo "RUN_COUNT must be set to one. It can't be defined in this script this changes would get lost when returning" 1>&2
else
    export RUN_COUNT=$(($RUN_COUNT+1))
fi

hadoop fs -rmr /csv_output

START=$(date +%s)
hadoop jar ${JAR} ${CLASS} "-Dmapred.child.java.opts=-Xmx${JAVA_MAX}m -Xms${JAVA_MIN}m" "${HDFS_FILE}"
END=$(date +%s)
DIFF=$(( $END - $START ))
echo "${DIFF},${RUN_COUNT},${CLASS},${SF},${JAVA_MIN},${JAVA_MAX}" >> "$RESULT_FILE"

hadoop fs -cat /csv_output/*
