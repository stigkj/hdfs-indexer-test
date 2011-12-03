package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.MFIBuilder;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.ToolRunner;

public class WithoutIndex extends Base {

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
		protected MFIBuilder configure2(MFIBuilder b) {
			return b;
		}
	}


	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new WithoutIndex(), args);
		System.exit(ret);
	}
}