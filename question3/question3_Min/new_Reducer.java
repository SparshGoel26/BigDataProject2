package question3_min;

import java.io.IOException; 
import java.util.Map; 
import java.util.TreeMap; 

import org.apache.hadoop.io.FloatWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Reducer; 

public class new_Reducer extends Reducer<Text, 
					FloatWritable, FloatWritable, Text> { 

	private TreeMap<Float,String > tmap2; 

	@Override
	public void setup(Context context) throws IOException, 
									InterruptedException 
	{ 
		tmap2 = new TreeMap<Float,String >(); 
	} 

	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, 
	Context context) throws IOException, InterruptedException 
	{ 

		// input data from mapper 
		// key			 values 
		// movie_name		 [ count ] 
		String name = key.toString(); 
		float count = 0; 

		for (FloatWritable val : values) 
		{ 
			count = val.get(); 
		} 

		// insert data into treeMap, 
		// we want top 10 viewed movies 
		// so we pass count as key 
		tmap2.put( count,name); 

		// we remove the first key-value 
		// if it's size increases 10 
		if (tmap2.size() > 10) 
		{ 
			tmap2.remove(tmap2.firstKey()); 
		} 
	} 

	@Override
	public void cleanup(Context context) throws IOException, 
									InterruptedException 
	{ 

		for (Map.Entry<Float,String > entry : tmap2.entrySet()) 
		{ 

			float count = entry.getKey(); 
			 String name = entry.getValue(); 
			context.write(new FloatWritable(count), new Text(name)); 
		} 
	} 
} 
