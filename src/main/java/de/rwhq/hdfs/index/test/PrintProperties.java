package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.MFIProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class PrintProperties {
	private static final Log LOG      = LogFactory.getLog(PrintProperties.class);

	public static void main(String [] args) throws IOException {
		String file = args[0];
		MFIProperties properties = new MFIProperties(file);
		properties.read();

		LOG.info(properties);
	}
}
