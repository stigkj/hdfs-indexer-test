package de.rwhq.hdfs.index.test;

import de.rwhq.hdfs.index.IndexBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public abstract class Base extends Configured implements Tool {
	private static final Log LOG      = LogFactory.getLog(Base.class);

	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one  = new IntWritable(1);
		private              Text        word = new Text();


		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String oId = value.toString().split("\\|")[0];
			int orderId;
			try {
				orderId = Integer.parseInt(oId);
			} catch (Exception e) {
				LOG.warn("coundn't parse '" + oId + "', which is the fist part of line\n" + value, e);
				return;
			}

			LOG.info("got order id " + oId);

			if (orderId < 10) {
				word.set("" + orderId);
				context.write(word, one);
			}
		}
	}

	public static class Reduce extends org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, Text, IntWritable> {

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable value : values)
				sum++;

			context.write(key, new IntWritable(sum));
		}
	}

	private void printUsage() {
		System.out.println("Usage : .jar <input_file>");
	}

	private int runJob(String name, Class<? extends Map> map, Class<? extends Reduce> reduce,
	                   String input, String output) throws Exception {

		// configuration for indexing
		Configuration conf = getConf();


		conf.setClass("indexBuilder", getBuilderClass(), IndexBuilder.class);

		setConf(conf);

		Job job = new Job(conf, name);
		job.setJarByClass(Main.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(map);
		job.setReducerClass(reduce);

		job.setInputFormatClass(getInputFormatClass());
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));

		return job.waitForCompletion(true) ? 0 : 1;
	}

	protected abstract Class<? extends InputFormat> getInputFormatClass();
	protected abstract Class<? extends BaseBuilder> getBuilderClass();

	@Override
	public int run(String[] args) throws Exception {

		String hdfsFile = args[0];
		LOG.info("running job with hdfs file: " + hdfsFile);
		return runJob("CSV", Map.class, Reduce.class, hdfsFile, "/csv_output");

	}

	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new Main(), args);
		System.exit(ret);
	}
}
