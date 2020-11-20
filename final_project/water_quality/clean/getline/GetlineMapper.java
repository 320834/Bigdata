import java.io.IOException;
// import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    int lineNum = 0;

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {    
        String line = value.toString();

        String[] lineArr = line.split("\",\"");

        String strLineNum = String.valueOf(this.lineNum);

        if(lineArr.length < 10 && line.charAt(line.length() - 2) == '\"' && line.charAt(line.length() - 1) == '\n')
        {
            //End of line, want to keep the new line
            Text textKey = new Text(strLineNum);
            Text textValue = new Text(line);

            lineNum += 1;

            context.write(textKey, textValue);
        }
        else
        {
            // Continue with the same line
            line = line.substring(0, line.length() - 1);
            Text textKey = new Text(strLineNum);
            Text textValue = new Text(line);

            context.write(textKey, textValue);
        }

    }
} 
