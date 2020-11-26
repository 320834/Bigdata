cd clean

bash run_water_clean.bash

cd ..
cd analysis

bash run_water_analyze.bash

# Take water analysis and put it into a seperate folder on hdfs

hdfs dfs -put ./analysis/analyze_data /user/ma4759/infrastructure/water_final