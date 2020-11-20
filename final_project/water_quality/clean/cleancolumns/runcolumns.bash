hdfs dfs -put ../raw_data/inputdata.csv bigdata/final_project/inputdata.csv

hdfs dfs -rm -r bigdata/final_project/output
hdfs dfs -mkdir bigdata/final_project/output

javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf Clean.jar CleanMapper.class CleanReducer.class Clean.class

hadoop jar Clean.jar Clean bigdata/final_project/inputdata.csv bigdata/final_project/output

hdfs dfs -copyToLocal bigdata/final_project/output/part-r-00000 /home/jc8017/Repository/Bigdata/final_project/water_quality/clean