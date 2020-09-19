import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {
        String line = value.toString().toLowerCase();

        if(line.contains("hackathon"))
        {
            context.write(new Text("hackathon"), new IntWritable(1));
        }

        if(line.contains("dec"))
        {
            context.write(new Text("dec"), new IntWritable(1))
        }

        if(line.contains("java"))
        {
            context.write(new Text("java"), new IntWritable(1))
        }

        if(line.contains("chicago"))
        {
            context.write(new Text("chicago"), new IntWritable(1))
        }
    }
}