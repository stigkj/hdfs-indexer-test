package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.BTreeIndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class PrimaryIndex1 extends Base {
	@Override
	protected Class<? extends InputFormat> getInputFormatClass() {
		return IndexedInputFormat.class;
	}

	@Override
	protected Class<? extends BaseBuilder> getBuilderClass() {
		return Builder.class;
	}

	public static class Builder extends BaseBuilder{

		@Override
		protected BTreeIndexBuilder configure2(BTreeIndexBuilder b) {
			// line = 100 Bytes
			return b.primaryIndex().cacheSize(10 *1000 * 1000); // about 1 GB
		}
	}


	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new PrimaryIndex1(), args);
		System.exit(ret);
	}
}
