import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class AnalyzeWaterMapper extends Mapper<LongWritable, Text, Text, AnalyzeWritable> 
{
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    {    
        String line = value.toString();

        String[] lineArr = line.split("\",\"");

        //0 - Name
        //1 - Population Served
        //2 - Source Type
        //3 - City Served
        //4 - County
        //5 - State

        //Toss out if it doesn't have a state and county 
        if(lineArr.length == 6 && lineArr[4].length() > 0 && lineArr[5].length() > 0)
        {
            int population = getPop(lineArr[1]);
            int citiesServed = getCitiesServed(lineArr[3]);
            int waterSystems = 1;
            double sysPerCapita = 0.0;
            
            // sysPerCapita(population, waterSystems);


            //Use state, county as key
            String state = lineArr[5].replace("\"", "");
            String county = lineArr[4];

            state = getRidTabs(state);
            county = getRidTabs(county);

            county = normalizeCounty(county);
            
            //Check if multiple counties are listed
            String[] counties = county.split(", ");
            if(counties.length > 0)
            {
                for(int i = 0; i < counties.length; i++)
                {
                    String currCounty = counties[i];

                    AnalyzeWritable valueMap = new AnalyzeWritable(
                        new IntWritable(population), 
                        new IntWritable(citiesServed),
                        new IntWritable(waterSystems),
                        new DoubleWritable(sysPerCapita)
                    );

                    Text keyMap = new Text(state + "," + currCounty);
                    context.write(keyMap, valueMap);
                }
            }
            else
            {
                AnalyzeWritable valueMap = new AnalyzeWritable(
                    new IntWritable(population), 
                    new IntWritable(citiesServed),
                    new IntWritable(waterSystems),
                    new DoubleWritable(sysPerCapita)
                );

                //For Virginia counties where cities are individual counties
                if(lineArr[3].length() > 0 && lineArr[4].length() == 0)
                {
                    county = lineArr[3];
                }

                Text keyMap = new Text(state + "," + county);

                context.write(keyMap, valueMap);
            }

        }
    }

    public int getPop(String pop)
    {
        if(pop.length() > 0)
        {
            try{
                pop = pop.replace(",", "");
                return Integer.parseInt(pop);
            }
            catch(Exception e)
            {
                return 0;
            }
            
        }

        return 0;
    }

    public int getCitiesServed(String city)
    {
        return city.length() > 0 ? 1 : 0;
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

    public getRidTabs(String value)
    {
        if(value.contains("\t"))
        {
            return value.split("\t")[0];
        }

        return value;
    }

    public normalizeCounty(String county)
    {
        if(county.contains("Parish"))
        {
            return county.split(" Parish")[0];
        }

        return county;
    }
} 