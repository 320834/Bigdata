import java.io.IOException;

import org.apache.hadoop.io.*;

public class AnalyzeWritable implements WritableComparable<AnalyzeWritable> { 
    private IntWritable population;
    private IntWritable citiesServed;
    private IntWritable waterSystems;
    private DoubleWritable systemsPerPop;

    /**
        Constructor
     */
    public AnalyzeWritable(IntWritable population, IntWritable citiesServed, IntWritable waterSystems, DoubleWritable systemsPerPop)
    {
        this.population = population;
        this.citiesServed = citiesServed;
        this.waterSystems = waterSystems;
        this.systemsPerPop = systemsPerPop;
    }

    public IntWrtiable getPop()
    {
        return this.population;
    }

    public IntWrtiable getCitiesServed()
    {
        return this.citiesServed;
    }

    public IntWritable getWaterSystems()
    {
        return this.waterSystems;
    }

    public DoubleWritable getSystemsPerPop()
    {
        return this.systemsPerPop;
    }

    public void readFields(DataInput in) throws IOException {
        this.population.readFields(in);
        this.citiesServed.readFields(in);
        this.waterSystems.readFields(in);
        this.systemsPerPop.readFields(in);
    }
 
    public void write(DataOutput out) throws IOException {
        this.population.write(out);
        this.citiesServed.write(out);
        this.waterSystems.write(out);
        this.systemsPerPop.write(out);
    }

    public int compareTo(AnalyzeWritable obj)
    {
        return obj.getWaterSystems().get() - this.getWaterSystems().get();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        if(obj == null)
        {
            return false;
        }

        AnalyzeWritable other = (AnalyzeWritable) obj;

        return Objects.equals(this.population, other.getPop()) 
            && Objects.equals(this.citiesServed, other.getCitiesServed())
            && Objects.equals(this.waterSystems, other.getWaterSystems())
            && Objects.equals(this.systemsPerPop, other.getsystemsPerPop());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.population, this.citiesServed, this.waterSystems, this.systemsPerPop);
    }
}