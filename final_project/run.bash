hdfs dfs -rm -r bigdata/final_project/output

javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf Clean.jar CleanMapper.class CleanReducer.class Clean.class

hadoop jar Clean.jar Clean bigdata/final_project/data.csv bigdata/final_project/output