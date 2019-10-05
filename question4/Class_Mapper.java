package question4;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Class_Mapper extends
Mapper<LongWritable, Text, Text, Text> {
public static final int MISSING = 9999;
public void map(LongWritable arg0, Text Value, Context context)
				throws IOException, InterruptedException {

		//Converting the record (single line) to String and storing it in a String variable line
			
			String line = Value.toString();
			
		//Checking if the line is not empty
			
            
            
			if (!(line.length() == 0)) {
				
				//date
				
				String date = line.substring(6, 14);

				//maximum temperature
				
				float temp_Max = Float
						.parseFloat(line.substring(39, 45).trim());
				
				//minimum temperature
				
				float temp_Min = Float
						.parseFloat(line.substring(47, 53).trim());

				//if maximum temperature is greater than 35 , its a hot day
				
				if ( temp_Max != MISSING && temp_Min != MISSING) {
					// Hot day
					context.write(new Text("|Time/Date: " + date+ " | "),
							new Text(String.valueOf(" Maximum Temperature: "+ temp_Max+ "\t|  Minimum Temperature: "+ temp_Min+ "\t|")));
				}

				
				
               
			}
		}
}
	