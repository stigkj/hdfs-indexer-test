package de.rwhq.hdfs.index.test;

import de.rwhq.btree.Range;
import de.rwhq.comparator.IntegerComparator;
import de.rwhq.hdfs.index.*;
import de.rwhq.serializer.IntegerSerializer;

import java.io.File;
import java.io.Serializable;

public abstract class BaseBuilder extends AbstractIndexBuilder {

	@Override
	public MFIBuilder configure(MFIBuilder bTreeIndexBuilder) {
		String indexDir = "/indexer/data";

		return configure2(bTreeIndexBuilder
				.indexFolder(indexDir)
				.addDefaultRange(new Range(0, 10))
				.cacheSize(25 * 1000)
				.treePageSize(128 * 1024 * 1024)
				.keySerializer(IntegerSerializer.INSTANCE)
				.keyExtractor(new IntegerCSVExtractor(0, "\\|"))
				.comparator(IntegerComparator.INSTANCE));
	}

	protected abstract MFIBuilder configure2(MFIBuilder b);
}
