package question1;

import java.io.*; 

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Reducer; 

public class WCReducer extends Reducer<Text, 
									FloatWritable, FloatWritable, Text> { 

	static int count; 
	static int count2;

	@Override
	public void setup(Context context) throws IOException, 
									InterruptedException 
	{ 
		count = 0; 
		count2 = 0;
	} 

	public void reduce(Text key, FloatWritable values, 
	Context context) throws IOException, InterruptedException 
	{ 

		// key				 values 
		//-ve of no_of_views [ movie_name ..] 
		float no_of_views = (-1) * values.get(); 

		Text movie_name = key; 

		if(movie_name.toString().equals("Cold Day ")){
			// we just write 10 records as output 
			if (count < 10) 
			{ 
				context.write(new FloatWritable(no_of_views), 
									new Text(movie_name)); 
				count++; 
			} 
		}
		
       if(movie_name.toString().equals("Hot Day ")){
    		// we just write 10 records as output 
   		if (count2 < 10) 
   		{ 
   			context.write(new FloatWritable(no_of_views), 
   								new Text(movie_name)); 
   			count2++; 
   		} 
		}
	
	} 
} 
