package de.rwhq.hdfs.index.test;

import de.rwhq.btree.Range;
import de.rwhq.hdfs.index.IndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import de.rwhq.serializer.StringCutSerializer;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.util.ToolRunner;

public class PrimaryIndexS0 extends Base {
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
		protected IndexBuilder configure2(IndexBuilder b) {
			// line = 100 Bytes
			return b.primaryIndex()
					.treePageSize(96 * 1024)
					.addDefaultRange(new Range(-1, -1))
					.cacheSize(8000 * 1000)
					.valueSerializer(StringCutSerializer.get(122));
		}
	}


	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new PrimaryIndexS0(), args);
		System.exit(ret);
	}
}
