package com.freshbourne.hdfs.index.test;

import com.freshbourne.btree.Range;
import com.freshbourne.hdfs.index.CSVModule;
import com.freshbourne.hdfs.index.Index;
import com.freshbourne.hdfs.index.IntegerCSVIndex;
import com.freshbourne.serializer.FixLengthSerializer;
import com.freshbourne.serializer.StringCutSerializer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Modules;

import java.io.Serializable;

public class RunModule extends AbstractModule implements Serializable {
	
	@Override protected void configure() {
		CSVModule module = new CSVModule();
		module.delimiter = "\\|";
		module.searchRange.add(new Range<Integer>(0, 10));
		install(Modules.override(module).with(new AbstractModule() {
			@Override protected void configure() {
				bind(new TypeLiteral<FixLengthSerializer<String, byte[]>>(){}).toInstance(StringCutSerializer.get(100));
			}
		}));
	}
}