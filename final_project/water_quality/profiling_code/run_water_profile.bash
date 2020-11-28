hdfs dfs -rm -r /user/ma4759/infrastructure/water_quality/profiling_output

#Compiling 
javac -classpath `yarn classpath`:. -sourcepath . -d . CountCountyMapper.java
javac -classpath `yarn classpath`:. -sourcepath . -d . CountCountyReducer.java
javac -classpath `yarn classpath`:. -d . CountCounty.java

jar -cvf CountCounty.jar CountCountyMapper.class CountCountyReducer.class CountCounty.class

#Run Jar from input file from cleaned
hadoop jar CountCounty.jar CountCounty /user/ma4759/infrastructure/water_quality/analysis_output/part-r-00000 /user/ma4759/infrastructure/water_quality/profiling_output

# Move to local directory
hdfs dfs -copyToLocal /user/ma4759/infrastructure/water_quality/profiling_output/part-r-00000 ./

#Rename copied filed
mv ./part-r-00000 ./profile_data