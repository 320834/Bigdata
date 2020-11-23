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

        String[] lineArr = line.split("\";\"");

        //First line columns
        if(lineArr[0].compareTo("Water System Name") == 0 && lineArr[10].compareTo("State") == 0)
        {
            String name = lineArr[0];
            String population =  lineArr[4];
            String source = lineArr[5];
            String city = lineArr[8];
            String county = lineArr[9];
            String state = lineArr[10];

            context.write(new Text(name + "\",\"" + population + "\",\"" + source + "\",\""
            + city + "\",\"" + county + "\",\"" + state), new IntWritable(1));
        }

        if(lineArr.length >= 11)
        {
            String name = lineArr[0];
            String population =  lineArr[4];
            String source = lineArr[5];
            String city = lineArr[8];
            String county = lineArr[9];
            String state = lineArr[10];

            context.write(new Text(name + "\",\"" + population + "\",\"" + source + "\",\""
            + city + "\",\"" + county + "\",\"" + state), new IntWritable(1));
        }


    }
} 