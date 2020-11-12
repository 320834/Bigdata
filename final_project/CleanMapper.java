import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> 
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {    
        String line = value.toString();

        String[] lineArr = line.split(",");

        //First line columns
        if(lineArr[2].compareTo("MonitoringLocationIdentifier") == 0 && lineArr[27].compareTo("LongitudeMeasure") == 0)
        {
            String monitorId = lineArr[2];
            String stateCode = lineArr[11];
            String countyCode = lineArr[12];
            String hasAquifer = lineArr[25];
            String latitude = lineArr[26];
            String longitude = lineArr[27];

            context.write(new Text(monitorId + "," + stateCode + "," + countyCode + ","
            + hasAquifer + "," + latitude + "," + longitude), new IntWritable(1));
        }

        if(lineArr.length >= 27)
        {
            String monitorId = lineArr[2];
            int stateCode = Integer.parseInt(lineArr[11]);
            int countyCode = Integer.parseInt(lineArr[12]);
            boolean hasAquifer = lineArr[25].length > 0 ? true : false;
            double latitude = Double.parseDouble(lineArr[26]);
            double longitude = Double.parseDouble(lineArr[27])

            context.write(new Text(monitorId + "," + stateCode + "," + countyCode + ","
            + hasAquifer + "," + latitude + "," + longitude), new IntWritable(1));
        }


    }
} 

// 2
// 11
// 12
// 25
// 26
// 27