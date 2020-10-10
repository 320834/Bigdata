import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NeighborhoodMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        String line = value.toString().toLowerCase();

        //4 neighborhood, 5 neighourhood
        String[] lineArr = line.split("\",\"");

        //System.out.println(lineArr[4]);

        try
        {
            String nGroup = lineArr[4];
            String neigh = lineArr[5];
            if(NeighborhoodMapper.neighborhood(lineArr[4]))
            {
                context.write(new Text(nGroup + " " + neigh), new IntWritable(1));
            }
        }
        catch(Exception e)
        {
            //System.out.println(line);
        }

    }

    public static boolean neighborhood(String n)
    {
        if(
            n != "\"Brooklyn\"" ||
            n != "\"Manhattan\"" || 
            n != "\"Staten Island\"" ||
            n != "\"Queens\"" ||
            n != "\"Bronx\"")
        {
            return true;
        }

        return false;
    }
}