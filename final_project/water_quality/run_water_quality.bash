cd data_injest

bash run_water_injest.bash

cd ..
cd clean

bash run_water_clean.bash

cd ..
cd analysis

bash run_water_analyze.bash

cd ..
cd profiling_code

bash run_water_profile.bash

# Take water analysis and put it into a seperate folder on hdfs

hdfs dfs -put ./analysis/analyze_data /user/ma4759/infrastructure/water_final