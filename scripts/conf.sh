export CLASS="de.rwhq.hdfs.index.test.WithoutIndex"
export RUNS=10
export SF=400    # 400 = 80 mio entries = 9.6 GB
export SPLIT_SIZE=184;
export RESULT_APPEND=true
export BUILD_TPCH=true

export JAVA_MIN=$((3 * 1024 ))
export JAVA_MAX=$((3 * 1024 ))

export JAR_OPTS="-Dmapred.min.split.size=$(($SPLIT_SIZE*1024*1024)) -Dmapred.max.split.size=$(($SPLIT_SIZE*1024*1024))"
export HDFS_FILE="/test/lineitem.tbl"
export LOCAL_FILE="build/part.tbl"
export RESULT_FILE="build/result.csv"

# just use the first jar you find in there (should be the right one)
export JAR=`ls build/libs/*.jar -1t|head -n 1`