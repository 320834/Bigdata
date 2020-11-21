import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AnalyzeWaterMapper extends Mapper<LongWritable, Text, Text, AnalyzeWritable> 
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {    
        String line = value.toString();

        String[] lineArr = line.split("\";\"");

        //0 - Name
        //1 - Population Served
        //2 - Source Type
        //3 - City Served
        //4 - County
        //5 - State

        //Toss out if it doesn't have a state and county 
        if(lineArr[4].length > 0 && lineArr[5].length > 0)
        {
            int population = getPop(lineArr[1]);
            int citiesServed = getCitiesServed(lineArr[3]);
            int waterSystems = 1;
            double sysPerCapita = sysPerCapita(population, waterSystems);

            //See AnalyzeWritable class
            AnalyzeWritable value = new AnalyzeWritable(
                new IntWritable(population), 
                new IntWritable(citiesServed),
                new IntWritable(waterSystems),
                new DoubleWritable(sysPerCapita)
                );

            //Use state, county as key
            Text key = new Text(lineArr[5] + "," + lineArr[4]);

            context(key, value);

        }
    }

    public int getPop(String pop)
    {
        if(pop.length > 0)
        {
            try{
                return Integer.parseInt(pop);
            }
            catch(Exception e)
            {
                return 0;
            }
            
        }
    }

    public int getCitiesServed(String city)
    {
        return city.length > 0 ? 1 : 0;
    }

    public double sysPerCapita(int population, int systems)
    {
        if(systems > 0)
        {
            double sysPerPop = (double)population/systems;
            return sysPerPop;
        }
        
        return 0.0;
    }
} 