package de.rwhq.hdfs.index.test;

import com.google.common.collect.Lists;
import de.rwhq.btree.Range;
import de.rwhq.hdfs.index.IndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class SecondaryIndexS0 extends Base {
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
			// long is 8 byte
			return b.secondaryIndex()
					.addDefaultRange(new Range(-1, -1))
					.cacheSize(20 * 1000 * 1000 + 100 * 1000);
		}
	}

	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new SecondaryIndexS0(), args);
		System.exit(ret);
	}
}
