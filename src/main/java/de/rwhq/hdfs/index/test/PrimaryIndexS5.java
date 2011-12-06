package de.rwhq.hdfs.index.test;

import de.rwhq.btree.Range;
import de.rwhq.hdfs.index.IndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class PrimaryIndexS5 extends Base {
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
			return b.primaryIndex().cacheSize(20 * 1100 * 1000 ).addDefaultRange(new Range(1, 4 * 1000 * 1000));
		}
	}


	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new PrimaryIndexS5(), args);
		System.exit(ret);
	}
}
