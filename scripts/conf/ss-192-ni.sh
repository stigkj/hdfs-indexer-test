export CLASS="de.rwhq.hdfs.index.test.NoIndex"
export RUNS=10
export RESULT_APPEND=true
export BUILD_TPCH=false
export SF=400    # 400 = 80 mio entries = 9.6 GB

export JAVA_MIN=3072
export JAVA_MAX=3072
export JAR_OPTS="-Dmapred.min.split.size=201326592"
export HDFS_FILE="/test/lineitem.tbl"
export LOCAL_FILE="build/part.tbl"
export RESULT_FILE="build/result.csv"

# just use the first jar you find in there (should be the right one)
export JAR=`ls build/libs/*.jar -1t|head -n 1`