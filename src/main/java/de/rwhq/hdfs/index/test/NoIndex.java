package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.AbstractIndexBuilder;
import de.rwhq.hdfs.index.IndexedInputFormat;
import de.rwhq.hdfs.index.MFIBuilder;
import org.apache.hadoop.mapreduce.InputFormat;

public class NoIndex extends Base {
	@Override
	protected Class<? extends InputFormat> getInputFormatClass() {
		return IndexedInputFormat.class;
	}

	@Override
	protected Class<? extends BaseBuilder> getBuilderClass() {
		return null;
	}

	public static class Builder extends BaseBuilder {

		@Override
		protected MFIBuilder configure2(MFIBuilder b) {
			return b.noIndex();
		}
	}
}
