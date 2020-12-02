# This file details the instructions for export

1. Export hive tables to hdfs

***NOTE***: The few commands has to run in beeline. The rest is done locally in the combine directory. Remember to change net id to your net id.

INSERT OVERWRITE DIRECTORY '/user/{net id}/output_final' 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' 
LINES TERMINATED BY "\n" 
SELECT * FROM analytic;

INSERT OVERWRITE DIRECTORY '/user/{net id}/output_final_mean'
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
LINES TERMINATED BY "\n"
SELECT * FROM analytic_mean;

INSERT OVERWRITE DIRECTORY '/user/{net id}/output_final_normalized'
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
LINES TERMINATED BY "\n"
SELECT * FROM analytic_normalized;

INSERT OVERWRITE DIRECTORY '/user/{net id}/output_final_std'
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
LINES TERMINATED BY "\n"
SELECT * FROM analytic_std;

**Remember to exit beeline**

2. Export hdfs file to local. cd to combine directory. Run the following steps below

hdfs dfs -copyToLocal output_final/000000_0 ./output
mv ./output/000000_0 ./output/analyze

hdfs dfs -copyToLocal output_final_mean/000000_0 ./output
mv ./output/000000_0 ./output/analyze_mean

hdfs dfs -copyToLocal output_final_normalized/000000_0 ./output
mv ./output/000000_0 ./output/analyze_normalized

hdfs dfs -copyToLocal output_final_std/000000_0 ./output
mv ./output/000000_0 ./output/analyze_std

3. Verify files

There should be three files in output/
1. analyze
2. analyze_mean
3. analyze_normalized
4. analyze_std

# Misc

- Columns for each dataset

state,county,bridges,residents,pct_medium_bridges,pct_poor_bridges,miles_freight_railroad,roads_acceptable,county_area, tax_respondants,state_local_income_tax,real_estate_tax,population_served,water_systems,ratio_fair_to_poor, freight_per_sq_mile,water_sys_per_capita,real_estate_tax_per_sq_mile,population_density

bridges_mean,resident_mean,pct_medium_bridges_mean,pct_poor_bridges_mean,miles_freight_railroad_mean, roads_acceptable_mean,county_area_mean,tax_respondants_mean,state_local_income_tax_mean,real_estate_tax_mean, population_served_mean,water_systems_mean,ratio_fair_to_poor_mean,freight_per_sq_mile_mean,water_sys_per_cap_mean, estate_tax_per_sq_mean,population_density_mean

state,county,bridges_normalized,residents_normalized,pct_medium_bridges_normalized,pct_poor_bridges_normalized,miles_freight_railroad_normalized,roads_acceptable_normalized,county_area_normalized,tax_respondants_normalized,state_local_income_tax_normalized,real_estate_tax_normalized,population_served_normalized,water_systems_normalized,ratio_fair_to_poor_normalized,freight_per_sq_mile_normalized,water_sys_per_cap_normalized,estate_tax_per_sq_normalized,population_density_normalized

bridges_std,resident_std,pct_medium_bridges_std,pct_poor_bridges_std,miles_freight_railroad_std,roads_acceptable_std,county_area_std,tax_respondants_std,state_local_income_tax_std,real_estate_tax_std,population_served_std,water_systems_std,ratio_fair_to_poor_std,freight_per_sq_mile_std,water_sys_per_cap_std,estate_tax_per_sq_std,population_density_std

1. @Mert Alev
 
- Number of residents, Pct of medium to fair bridges, Pct of poor bridges, Route Miles Of Freight Data, Roads Acceptable, Land Area

2. @Anand Tyagi

- Number Of Respondents, State and Local Income Tax, Real Estate Tax

3. @Justin
- Population Served, Water Systems