package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.IndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class NoIndex extends Base {
	@Override
	protected Class<? extends InputFormat> getInputFormatClass() {
		return IndexedInputFormat.class;
	}

	@Override
	protected Class<? extends BaseBuilder> getBuilderClass() {
		return Builder.class;
	}

	public static class Builder extends BaseBuilder {

		@Override
		protected IndexBuilder configure2(IndexBuilder b) {
			return b.noIndex();
		}
	}

	public static void main(String[] args) throws Exception {
	int ret = ToolRunner.run(new NoIndex(), args);
		System.exit(ret);
	}

}
