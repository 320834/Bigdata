hdfs dfs -rm -r output_final
hdfs dfs -rm -r tax
hdfs dfs -rm -r transport
hdfs dfs -rm -r water

hdfs dfs -mkdir tax
hdfs dfs -mkdir transport
hdfs dfs -mkdir water

hdfs dfs -put tax tax
hdfs dfs -put transportation transport
hdfs dfs -put water_quality water