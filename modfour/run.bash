hdfs dfs -mkdir bigdata/modfour

hdfs dfs -put AB_NYC_2019.csv bigdata/modfour

javac -classpath `yarn classpath` -d . NeighborhoodMapper.java
javac -classpath `yarn classpath` -d . NeighborhoodReducer.java
javac -classpath `yarn classpath`:. -d . NeighborhoodCount.java

jar -cvf NeighborhoodCount.jar *.class

hadoop jar NeighborhoodCount.jar NeighborhoodCount bigdata/modfour/AB_NYC_2019.csv bigdata/modfour/output