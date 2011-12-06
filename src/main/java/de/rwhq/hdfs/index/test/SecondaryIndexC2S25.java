package de.rwhq.hdfs.index.test;

import com.google.common.collect.Lists;
import de.rwhq.btree.Range;
import de.rwhq.hdfs.index.IndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class SecondaryIndexC2S25 extends Base {
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
					.defaultSearchRanges(Lists.newArrayList(new Range(null, 20 * 1000 * 1000)))
					.cacheSize(1024 * 1024 * 1024 / 4); // 2GB // keyvalue entries
		}
	}

	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new SecondaryIndexC2S25(), args);
		System.exit(ret);
	}
}
