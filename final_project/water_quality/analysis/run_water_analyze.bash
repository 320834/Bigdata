hdfs dfs -rm -r bigdata/final_project/output_analysis

#Compiling 
javac -classpath `yarn classpath` -d . AnalyzeWritable.java
javac -classpath `yarn classpath` -d . AnalyzeWaterMapper.java
javac -classpath `yarn classpath` -d . AnalyzeWaterReducer.java
javac -classpath `yarn classpath`:. -d . AnalyzeWater.java

jar -cvf AnalyzeWater.jar AnalyzeWaterMapper.class AnalyzeWaterReducer.class AnalyzeWater.class AnalyzeWaterWritable.class

#Run Jar from input file from cleaned
hadoop jar AnalyzeWater.jar AnalyzeWater bigdata/final_project/output/part-r-00000 bigdata/final_project/output_analysis

#Move file from hdfs to local
hdfs dfs -copyToLocal bigdata/final_project/output_analysis/part-r-00000 /home/jc8017/Repository/Bigdata/final_project/water_quality/analysis

#Rename copied filed
mv ./part-r-00000 ./analyze_data