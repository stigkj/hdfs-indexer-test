
#result file looks like this: SF, USE_INDEX, RUN, JAVA_MAX, TIME

export CLASS="de.rwhq.hdfs.index.test.WithoutIndex"
export RUNS=1
export JAVA_MIN=1024
export JAVA_MAX=1024
export HDFS_FILE="/test/lineitem.tbl"
export SF=1
export RESULT_FILE="build/result.csv"
export RESULT_APPEND=false
export BUILD_TPCH=true

# just use the first jar you find in there (should be the right one)
export JAR=`ls build/libs/*.jar -1t|head -n 1`

