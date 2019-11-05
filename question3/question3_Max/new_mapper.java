package question3_max;

import java.io.*; 
import java.util.*; 

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.mapreduce.Mapper; 

public class new_Mapper extends Mapper<Object, Text, Text, FloatWritable> { 

	private TreeMap<Float,String> tmap; 

	@Override
	public void setup(Context context) throws IOException, 
									InterruptedException 
	{ 
		tmap = new TreeMap<Float,String>(); 
	} 

	@Override
	public void map(Object key, Text value,	Context context) throws IOException,InterruptedException 
	{ 

		String line = value.toString();
		
		//Checking if the line is not empty
			
			if (!(line.length() == 0)) 
			{
				String date = line.substring(6, 14);				
				Float temp_Max = Float.parseFloat(line.substring(39, 45).trim());
				Float temp_Min = Float.parseFloat(line.substring(47, 53).trim());

				tmap.put( temp_Max,date); 
				//tmap.put(date, temp_Min); 

				if (tmap.size() > 10) 
				{ 
					tmap.remove(tmap.firstKey()); 
				} 
			} 		
	}

	@Override
	public void cleanup(Context context) throws IOException, 
									InterruptedException 
	{ 
		for (Map.Entry<Float,String> entry : tmap.entrySet()) 
		{ 

			float count = entry.getKey(); 
			 String name = entry.getValue(); 

			context.write(new Text(name), new FloatWritable(count)); 
		} 
	} 
} 
