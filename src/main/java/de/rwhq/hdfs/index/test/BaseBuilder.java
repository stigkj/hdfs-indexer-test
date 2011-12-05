package de.rwhq.hdfs.index.test;

import de.rwhq.comparator.IntegerComparator;
import de.rwhq.hdfs.index.*;
import de.rwhq.hdfs.index.extractor.IntegerCSVExtractor;
import de.rwhq.serializer.IntegerSerializer;

public abstract class BaseBuilder extends AbstractIndexBuilder {

	@Override
	public MFIBuilder configure(MFIBuilder bTreeIndexBuilder) {
		String indexDir = "/indexer/data";

		return configure2(bTreeIndexBuilder
				.indexFolder(indexDir)
				.treePageSize(128 * 1024)
				.keySerializer(IntegerSerializer.INSTANCE)
				.keyExtractor(new IntegerCSVExtractor(0, "\\|"))
				.comparator(IntegerComparator.INSTANCE));
	}

	protected abstract MFIBuilder configure2(MFIBuilder b);
}
