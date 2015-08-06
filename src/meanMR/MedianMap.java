package meanMR;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class MedianMap extends Mapper<LongWritable, Text, Text, Text> {

	  public Text yearText = new Text();
	  public Text nm = new Text();

	  protected void map(LongWritable key, Text value, Context context)
	      throws java.io.IOException, InterruptedException {
        String[] line = value.toString().split("\t");
	    String year = line[0];
	    yearText.set(year);
	    String num = line[1];
	    nm.set(num);
	    
	    context.write(new Text("1"),nm);
	  }
	  
	  
	}