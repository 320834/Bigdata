# Push files onto hdfs
hdfs dfs -put ./raw_data bigdata/final_project/water_quality/

#Aggregate data
hdfs dfs -getmerge bigdata/final_project/water_quality/raw_data bigdata/final_project/water_quality/clean/inputdata.csv

#bash ./getline/runline.bash