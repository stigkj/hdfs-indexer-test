package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.BTreeIndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import org.apache.hadoop.mapreduce.InputFormat;

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
		protected BTreeIndexBuilder configure2(BTreeIndexBuilder b) {
			// long is 8
			return b.secondaryIndex().cacheSize(1024 * 1024 * 1024 / 4); // 2GB
		}
	}
}
