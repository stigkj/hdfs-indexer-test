package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.IndexedInputFormat;
import de.rwhq.hdfs.index.MFIBuilder;
import de.rwhq.serializer.StringCutSerializer;
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
		protected MFIBuilder configure2(MFIBuilder b) {
			// line = 100 Bytes
			return b.primaryIndex().cacheSize(10 *1000 * 1000).valueSerializer(StringCutSerializer.get(25)); // 10 mio entrys a 25 byte
		}
	}


	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new PrimaryIndex1(), args);
		System.exit(ret);
	}
}
