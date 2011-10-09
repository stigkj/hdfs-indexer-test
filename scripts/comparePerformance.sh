#!/usr/bin/env bash

# OUTPUT: SF, USE_INDEX, RUN, JAVA_MAX, TIME

PERF_SUMMARY="build/perf_summary.csv"
NUMBER_OF_RUNS=4
JAVA_MAX=512
USE_INDEX=true
SAMPLE_FILE_HDFS="/test/lineitem.tbl"
export SF=1

#JAVA_MAX=(256 512 1024)
#JAVA_INIT=(256 512 1024)



setup(){
	rm build/lineitem.tbl
	hadoop fs -rm "/test/lineitem.tbl"
	scripts/setupHdfs.sh
}

run(){
	START=$(date +%s)
	JAR=`ls build/libs/*.jar -1t|head -n 1`
	hadoop jar ${JAR} com.freshbourne.hdfs.index.test.Main "${SAMPLE_FILE_HDFS}" ${USE_INDEX} -Dmapred.child.java.opts=-Xmx${JAVA_MAX}M
	END=$(date +%s)
	DIFF=$(( $END - $START ))
	echo "${SF},${USE_INDEX},${i},${JAVA_MAX},${DIFF}" >> $PERF_SUMMARY
}

setup
# with index
for (( i=1; i <= NUMBER_OF_RUNS; i++ ));do
	run
done

USE_INDEX=false
run

exit


for i in {1..5}; do
	sleep 5
	scripts/setupHdfs.sh
	START=$(date +%s)
	hadoop jar build/libs/hdfs-indexer-0.01.jar com.freshbourne.hdfs.index.test.PerformanceMain "${SAMPLE_FILE_HDFS}" true -Dmapred.child.java.opts=-Xmx1024M
	END=$(date +%s)
	DIFF_INDEX=$(( $END - $START ))
	echo "LARGE,true,${i},${DIFF_INDEX}" >> $PERF_SUMMARY

	DIFF=$(( $DIFF_INDEX - $DIFF_NO_INDEX ))
	echo "INDEX was ${DIFF} seconds faster"
done


