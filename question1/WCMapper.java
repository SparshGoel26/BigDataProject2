package question1;

import java.io.*; 

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper; 

public class WCMapper extends Mapper<Object, 
							Text, Text, FloatWritable> { 

	// data format => movie_name	 
	// no_of_views (tab seperated) 
	@Override
	public void map(Object key, Text value, 
	Context context) throws IOException, 
					InterruptedException 
	{ 
	
		//Converting the record (single line) to String and storing it in a String variable line
			
			String line = value.toString();
			
		//Checking if the line is not empty
			
			if (!(line.length() == 0)) {
				
				
				//maximum temperature
				
				Float temp_Max = Float
						.parseFloat(line.substring(39, 45).trim());
				temp_Max = (-1) * temp_Max; 

				//minimum temperature
				
				Float temp_Min = Float
						.parseFloat(line.substring(47, 53).trim());
				temp_Min = (-1) * temp_Min; 
				
			//if maximum temperature is greater than 35 , its a hot day
				
					// Hot day
					context.write( new Text("Hot Day "), new FloatWritable(temp_Max));
				

				//if minimum temperature is less than 10 , its a cold day
				
					// Cold day
					context.write(new Text("Cold Day "), new FloatWritable(temp_Min));

				
			}
		
	} 
} 
