package at.ac.tuwien.SimpleHadoopExample;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CountWords {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private static final Logger LOG = Logger.getLogger(TokenizerMapper.class.getName());

		// Hadoop specific value for the MapReduce communication
		private static final IntWritable ONE = new IntWritable(1);

		@Override
		public void map(Object key, Text value, Context context) throws IOException,
				InterruptedException {
			String[] words = value.toString().split("\\P{Alpha}+");

			Pattern patternSingle = Pattern.compile(
					"^([aeiouqwrtzp]+)|([aeiouyxcvbnm]+)|([aeiousdfghjkl]+)$",
					Pattern.CASE_INSENSITIVE);
			Pattern patternDouble = Pattern
					.compile(
							"^([aeiouqwrtzupsdfghjkl]+)|([aeiouqwrtzpyxcvbnm]+)|([aeiousdfghjklyxcvbnm]+)$",
							Pattern.CASE_INSENSITIVE);
			Pattern patternTripple = Pattern.compile("^([abcdefghijklmnopqrstuvwxyz]+)$",
					Pattern.CASE_INSENSITIVE);
			Pattern patternVowels = Pattern.compile("^([aeiou]+)$", Pattern.CASE_INSENSITIVE);

			for (String word : words) {
				if (!patternVowels.matcher(word).matches()) {
					if (patternSingle.matcher(word).matches()) {
						context.write(new Text("1"), ONE);
						LOG.fine("One row: " + word);
					} else if (patternDouble.matcher(word).matches()) {
						context.write(new Text("2"), ONE);
						LOG.fine("Two rows: " + word);
					} else if (patternTripple.matcher(word).matches()) {
						context.write(new Text("3"), ONE);
						LOG.fine("Three rows: " + word);
					}
				}
			}
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private final IntWritable result = new IntWritable();

		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable value : values) {
				sum += value.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	// The input file and output folder
	private static final String INPUT_FILE = "src/main/resources/2600.txt";
	private static final String OUTPUT_FOLDER = "target/result/";

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf, "word count");
		job.setJarByClass(CountWords.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(INPUT_FILE));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_FOLDER));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
