package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.IndexedInputFormat;
import de.rwhq.hdfs.index.MFIBuilder;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class SecondaryIndex1 extends Base {
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
		protected MFIBuilder configure2(MFIBuilder b) {
			// long is 8
			return b.secondaryIndex().cacheSize(1024 * 1024 * 1024 / 4); // 2GB
		}
	}

	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new SecondaryIndex1(), args);
		System.exit(ret);
	}
}
