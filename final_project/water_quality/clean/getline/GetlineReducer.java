import java.io.IOException;
// import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GetlineReducer
extends Reducer<Text, Text, Text, Text> 
{
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {

        String line = "";
        
        for (Text value : values) {
            line += value.toString();
        }
        
        Text key = new Text(line);
        Text value = new Text("");

        context.write(key, value);
    }
}