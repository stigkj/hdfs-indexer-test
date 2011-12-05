#result file looks like this: TIME, RUN, CLASS, SF, JAVA_MAX

export CLASS="de.rwhq.hdfs.index.test.WithoutIndex"
export RUNS=1
export JAVA_MIN=1024
export JAVA_MAX=5120
export LOCAL_FILE="build/part.tbl"
export HDFS_FILE="/test/lineitem.tbl"
export SF=400 #9.6 GB # 80 mio entries
export RESULT_FILE="build/result.csv"
export RESULT_APPEND=false
export BUILD_TPCH=true

# just use the first jar you find in there (should be the right one)
export JAR=`ls build/libs/*.jar -1t|head -n 1`