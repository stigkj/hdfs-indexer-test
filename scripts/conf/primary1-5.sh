
#result file looks like this: TIME, RUN, CLASS, SF, JAVA_MAX

export CLASS="de.rwhq.hdfs.index.test.PrimaryIndex1"
export RUNS=10
export JAVA_MIN=1024
export JAVA_MAX=5120
export HDFS_FILE="/test/lineitem.tbl"
export LOCAL_FILE="build/part.tbl"
export SF=400 #9.6 GB # 80 mio entries
export RESULT_FILE="build/result.csv"
export RESULT_APPEND=true
export BUILD_TPCH=true

# just use the first jar you find in there (should be the right one)
export JAR=`ls build/libs/*.jar -1t|head -n 1`