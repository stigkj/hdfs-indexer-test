package de.rwhq.hdfs.index.test;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class WithoutIndex extends Base {

	@Override
	protected Class<TextInputFormat> getInputFormatClass() {
		return TextInputFormat.class;
	}

	@Override
	protected Class<CustomBuilder> getBuilderClass() {
		return CustomBuilder.class;
	}
}
