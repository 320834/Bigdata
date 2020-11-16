Here are steps I've used to compile and run on Dumbo. Use the Forum if you encounter any difficulties.

// Replace 'yourNetID' below with your own NYU Net ID.

ssh -Y jc8017@dumbo.es.its.nyu.edu
// Write your driver source code using a text editor like vi (or emacs or other text editor):

vi MaxTemperature.java

// Note: The Dumbo cluster defaults to multiple reducers, normally it would be 1.
// You can assign the number of reducers in your driver code by adding this line:
// job.setNumReduceTasks(1); // 1 Reduce task
// If you add this line, your output result will be in HDFS in file part-r-00000.
// If you keep the default, your output will be scattered across 32 files from
// part-r-00000 through part-r-00031.
// Write your mapper and reducer source code:

vi MaxTemperatureMapper.java
vi MaxTemperatureReducer.java

// Compile your Java code:
java -version
yarn classpath
javac -classpath `yarn classpath` -d . MaxTemperatureMapper.java
javac -classpath `yarn classpath` -d . MaxTemperatureReducer.java
javac -classpath `yarn classpath`:. -d . MaxTemperature.java

// Note: It's important to use the correct quotes in the commands above.
// If the above did not work, try substituting `yarn classpath` with: "$(yarn classpath)"
// Notice that the quotes are different in the two options show in the preceding line.
// Create your jar file

jar -cvf maxTemp.jar *.class

HDFS Input Data
1. hdfs dfs -ls
2. hdfs dfs -mkdir file
3. hdfs dfs -put fileName /user/jc8017/folder

hadoop jar maxTemp.jar MaxTemperature bigdata/modtwo/temperatureInputs.txt bigdata/modtwo/output3

Delete: hdfs dfs -rm -r bigdata/"folder"
