# Instructions for merging data hive

files
one.txt
two.txt

one 
state name, county name, pop

two 
state name, county name, status

1. Put files on hdfs 

hdfs dfs -put filename location

hdfs dfs -put one.txt one
hdfs dfs -put two.txt two

2. Go onto hive
- beeline
- !connect jdbc:hive2://babar.es.its.nyu.edu:10000/;
- Use net id for login
- use jc8017;
- create external table one(state string, county string, pop int) row format delimited fields terminated by ',' location '/user/jc8017/one/';
- create external table two(state string, county string, status string) row format delimited fields terminated by ',' location '/user/jc8017/two/';
- create table combine select one.state, one.county, one.pop, two.status from one left outer join two on (one.county = two.county and one.state = two.state);
