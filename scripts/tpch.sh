#!/usr/bin/env bash

if [ ! -f build.gradle ];then
	echo "please start the script from the root directory of the project"
	exit -1
fi

mkdir -p build
cd build
dbgen -T L -s 1

exit
# not used anymore since we use the tpch cookbook
curl "http://www.tpc.org/tpch/spec/tpch_2_14_0.tgz" -o "build/tpch_2_14_0.tgz"
cd build
tar xzf tpch*.tgz
rm dbgen/makefile.suite
cp ../cookbooks/tpch/templates/default/makefile.suite dbgen/
cd dbgen
make -f makefile.suite
echo "generating: dbgen -T L -s 1 -v"
./dbgen -T L -s 1 -v
mv lineitem.tbl ../