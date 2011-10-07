package com.freshbourne.hdfs.index.test;

import com.freshbourne.hdfs.index.CSVModule;
import com.freshbourne.hdfs.index.Index;
import com.freshbourne.hdfs.index.IntegerCSVIndex;

public class RunModule extends CSVModule {


    @java.lang.Override protected java.lang.String hdfsFile() {
        return "/test/lineitem.tbl";
    }

    @java.lang.Override protected java.lang.String indexRootFolder() {
        return "/tmp/index";
    }

    @java.lang.Override protected int csvColumn() {
        return 0;
    }

    @java.lang.Override protected java.lang.String delimiter() {
        return "|";
    }

	@Override protected Class<? extends Index> indexClass() {
		return IntegerCSVIndex.class;
	}
}
