package meanMR;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class MedianTR {

  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    if (args.length != 2) {
      System.err.println("Usage: MedianNumber <input path> <output path>");
      System.exit(-1);
    }
    
    Job job = new Job();
    job.setJarByClass(MedianTR.class);
    job.setJobName("Median Number");
   
    int numberOfReduceTasks = 1;
	int linesPerMapper = 20;
	
	job.setCombinerClass(MedianReduce.class);
	job.setInputFormatClass(org.apache.hadoop.mapreduce.lib.input.NLineInputFormat.class);
	job.getConfiguration().setInt("mapreduce.input.lineinputformat.linespermap",linesPerMapper);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(meanMR.MedianMap.class);
    job.setReducerClass(meanMR.MedianReduce.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    job.waitForCompletion(true);
  }
}
