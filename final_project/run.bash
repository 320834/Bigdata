javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf Clean.jar CleanMapper.class CleanReducer.class Clean.class

hadoop jar Clean.jar Clean bigdata/final_project/station.csv bigdata/final_project/output