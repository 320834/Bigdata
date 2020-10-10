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

        if(lineArr.length >= 5)
        {
            if(NeighborhoodMapper.neighborhood(lineArr[4].trim()))
            {
                String nGroup = lineArr[4].trim();
                String neigh = lineArr[5].trim();
                context.write(new Text(nGroup + " " + neigh), new IntWritable(1));
            }
        }
           


    }

    public static boolean neighborhood(String n)
    {
        if(
            n.compareTo("Brooklyn") == 0 ||
            n.compareTo("Manhattan") == 0 || 
            n.compareTo("Staten Island") == 0 ||
            n.compareTo("Queens") == 0 ||
            n.compareTo("Bronx") == 0)
        {
            return true;
        }

        return false;
    }
}