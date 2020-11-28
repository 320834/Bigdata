# Do data injest for water quality
cd data_injest
bash run_water_injest.bash
cd ..

# Do clean for water quality
cd clean
bash run_water_clean.bash
cd ..

# Do analysis for water quality

cd analysis
bash run_water_analyze.bash
cd ..

# Do profiling code for water quality
cd profiling_code
bash run_water_profile.bash
cd ..

# Take water analysis final and put it into combine input
mv ./ana_code/analyze_data ../combine/final_input
