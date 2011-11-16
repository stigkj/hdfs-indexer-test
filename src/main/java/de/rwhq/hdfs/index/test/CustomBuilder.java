package de.rwhq.hdfs.index.test;

import de.rwhq.btree.Range;
import de.rwhq.comparator.IntegerComparator;
import de.rwhq.hdfs.index.*;
import de.rwhq.serializer.IntegerSerializer;

import java.io.Serializable;

public class CustomBuilder extends AbstractIndexBuilder {

	@Override
	public BTreeIndexBuilder configure(BTreeIndexBuilder bTreeIndexBuilder) {
		return bTreeIndexBuilder
				.indexFolder("/tmp/index")
				.addDefaultRange(new Range(0, 10))
				.cacheSize(10 * 1000) // 10k lines + keys + nodes = 15MB
				.keySerializer(IntegerSerializer.INSTANCE)
				.keyExtractor(new IntegerCSVExtractor(0, "|"))
				.comparator(IntegerComparator.INSTANCE);
	}
}
