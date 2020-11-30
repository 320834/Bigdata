import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AnalyzeWaterReducer
extends Reducer<Text, AnalyzeWritable, Text, Text> 
{
    @Override
    public void reduce(Text key, Iterable<AnalyzeWritable> values, Context context)
        throws IOException, InterruptedException {
        
        int population = 0;
        int citiesServed = 0;
        int waterSystems = 0;
        double systemsPerPop = 0;

        for (AnalyzeWritable value : values) {
            population += value.getPop().get();
            citiesServed += value.getCitiesServed().get();
            waterSystems += value.getWaterSystems().get();
        }

        systemsPerPop = (double)population/waterSystems;
        String countyKey = key.toString();
        
        context.write(new Text(countyKey + "," + population + "," + waterSystems), new Text(""));
    }
}