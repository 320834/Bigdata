javac -classpath `yarn classpath` -d . GetlineMapper.java
javac -classpath `yarn classpath` -d . GetlineReducer.java
javac -classpath `yarn classpath`:. -d . Getline.java

jar -cvf Getline.jar GetlineMapper.class GetlineReducer.class Getline.class

hadoop jar Getline.jar Getline bigdata/final_project/water_quality/clean/inputcleandata.csv bigdata/final_project/water_quality/clean/getlineoutput
