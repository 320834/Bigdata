hdfs dfs -rm -r /user/ma4759/infrastructure/water_quality/clean_output

javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf Clean.jar CleanMapper.class CleanReducer.class Clean.class

hadoop jar Clean.jar Clean /user/ma4759/infrastructure/water_quality/inputdata.csv /user/ma4759/infrastructure/water_quality/clean_output

# Copy To Local File
# hdfs dfs -copyToLocal /user/ma4759/infrastructure/water_quality/clean_output/part-r-00000 /home/jc8017/Repository/Bigdata/final_project/water_quality/clean
# mv /home/jc8017/Repository/Bigdata/final_project/water_quality/clean/part-r-00000 /home/jc8017/Repository/Bigdata/final_project/water_quality/clean/cleaned_data