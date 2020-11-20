hdfs dfs -rm -r bigdata/final_project/water_quality

hdfs dfs -mkdir bigdata/final_project/water_quality/clean
hdfs dfs -mkdir bigdata/final_project/water_quality/analyze
hdfs dfs -mkdir bigdata/final_project/water_quality/raw_data

# bash ./clean/runwaterclean.bash