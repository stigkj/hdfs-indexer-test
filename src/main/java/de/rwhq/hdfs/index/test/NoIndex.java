package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.IndexBuilder;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.ToolRunner;

public class NoIndex extends Base {

	@Override
	protected Class<TextInputFormat> getInputFormatClass() {
		return TextInputFormat.class;
	}

	@Override
	protected Class<? extends BaseBuilder> getBuilderClass() {
		return Builder.class;
	}

	public static class Builder extends BaseBuilder {
		@Override
		protected IndexBuilder configure2(IndexBuilder b) {
			return b;
		}
	}


	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new NoIndex(), args);
		System.exit(ret);
	}
}