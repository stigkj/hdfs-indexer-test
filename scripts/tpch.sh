#!/usr/bin/env bash

if [ "${SF}" == "" ];then
	echo "SF must be set" 1>&2
	exit -1
fi


if [ ! -f build.gradle ];then
	echo "please start the script from the root directory of the project" 1>&2
	exit -1
fi

mkdir -p build
cd build
echo "dbgen: dbgen -T P -s ${SF}" 1>&2
dbgen -T P -s ${SF}
echo "dbgen done" 1>&2