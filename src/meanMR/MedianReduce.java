package meanMR;



import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianReduce extends
        Reducer<Text, Text, Text, Text> {
	ArrayList<Integer> temperatureList = new ArrayList<Integer>();
	int med;
	protected void reduce(Text key, Iterable<Text> values, Context context) throws java.io.IOException, InterruptedException {
		int median = 0;
		for (Text value : values) {
			temperatureList.add(Integer.parseInt(value.toString()));
		}
		Collections.sort(temperatureList);
		int size  = temperatureList.size();

		if(size%2 == 0){
			int half = size/2;

			median  = temperatureList.get(half);
		}else {
			int half = (size + 1)/2;
			median = temperatureList.get(half -1);
		}
		//med=
		String Med = Integer.toString(median);
		String key2;
		key2 = Integer.toString(1);
		context.write(new Text(key2), new Text(Med));
	}

}