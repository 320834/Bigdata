hdfs dfs -mkdir bigdata/modthree

hdfs dfs -put data.txt bigdata/modthree

javac -classpath `yarn classpath` -d . WordCountMapper.java
javac -classpath `yarn classpath` -d . WordCountReducer.java
javac -classpath `yarn classpath`:. -d . WordCount.java

jar -cvf wordCount.jar *.class

hadoop jar wordCount.jar WordCount bigdata/modthree/data.txt bigdata/modthree/output