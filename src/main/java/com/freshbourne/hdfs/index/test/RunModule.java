package com.freshbourne.hdfs.index.test;

import com.freshbourne.hdfs.index.CSVModule;
import com.freshbourne.hdfs.index.Index;
import com.freshbourne.hdfs.index.IntegerCSVIndex;
import com.google.inject.AbstractModule;

import java.io.Serializable;

public class RunModule extends CSVModule {
	public RunModule(){
		this.delimiter = "\\|";
	}
}
