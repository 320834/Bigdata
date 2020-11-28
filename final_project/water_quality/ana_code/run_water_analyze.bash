hdfs dfs -rm -r /user/ma4759/infrastructure/water_quality/analysis_output

#Compiling 
javac -classpath `yarn classpath`:. -sourcepath . -d . AnalyzeWritable.java
javac -classpath `yarn classpath`:. -sourcepath . -d . AnalyzeWaterMapper.java
javac -classpath `yarn classpath`:. -sourcepath . -d . AnalyzeWaterReducer.java
javac -classpath `yarn classpath`:. -d . AnalyzeWater.java

jar -cvf AnalyzeWater.jar AnalyzeWaterMapper.class AnalyzeWaterReducer.class AnalyzeWater.class AnalyzeWritable.class

#Run Jar from input file from cleaned
hadoop jar AnalyzeWater.jar AnalyzeWater /user/ma4759/infrastructure/water_quality/clean_output/part-r-00000 /user/ma4759/infrastructure/water_quality/analysis_output

#Move file from hdfs to local
hdfs dfs -copyToLocal /user/ma4759/infrastructure/water_quality/analysis_output/part-r-00000 ./

#Rename copied filed
mv ./part-r-00000 ./analyze_data