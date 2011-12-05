package de.rwhq.hdfs.index.test;

import com.google.common.collect.Lists;
import de.rwhq.btree.Range;
import de.rwhq.hdfs.index.IndexedInputFormat;
import de.rwhq.hdfs.index.MFIBuilder;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class SecondaryIndexC2S0 extends Base {
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
			// long is 8 byte
			return b.secondaryIndex()
					.defaultSearchRanges(Lists.newArrayList(new Range(-1, -1)))
					.cacheSize(1024 * 1024 * 1024 / 4); // 2GB (3 total, with values)
		}
	}

	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new SecondaryIndexC2S0(), args);
		System.exit(ret);
	}
}
