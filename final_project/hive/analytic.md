## This file details the instructions for the analytic on hive tables

**NOTE** Make sure that you complete the steps in the combine file first.

1. Create analytic table from combined(see combine.md)

create table analytic 
as 
select
state,
county,
bridges,
residents,
pctmediumtofairbridges as pct_medium_bridges,
pctpoorbridges as pct_poor_bridges,
milesfreightrailroad as miles_freight_railroad,
roadsacceptable as roads_acceptable,
countyarea as county_area,
taxrespondents as tax_respondants,
statelocalincometax as state_local_income_tax,
realestatetax as real_estate_tax,
populationserved as population_served,
watersystems as water_systems,
pctmediumtofairbridges/pctpoorbridges as ratio_fair_to_poor,
milesfreightrailroad/countyarea as freight_per_sq_mile,
populationserved/watersystems as water_sys_per_capita,
realestatetax/countyarea as real_estate_tax_per_sq_mile,
residents/countyarea as population_density
from combined;

2. Create a table for means of analytic

CREATE TABLE analytic_mean 
as
select 
avg(bridges) as bridges_mean,
avg(residents) as resident_mean, 
avg(pct_medium_bridges) as pct_medium_bridges_mean,
avg(pct_poor_bridges) as pct_poor_bridges_mean,
avg(miles_freight_railroad) as miles_freight_railroad_mean,
avg(roads_acceptable) as roads_acceptable_mean,
avg(county_area) as county_area_mean,
avg(tax_respondants) as tax_respondants_mean,
avg(state_local_income_tax) as state_local_income_tax_mean,
avg(real_estate_tax) as real_estate_tax_mean,
avg(population_served) as population_served_mean,
avg(water_systems) as water_systems_mean,
avg(ratio_fair_to_poor) as ratio_fair_to_poor_mean, 
avg(freight_per_sq_mile) as freight_per_sq_mile_mean, 
avg(water_sys_per_capita) as water_sys_per_cap_mean, 
avg(real_estate_tax_per_sq_mile) as estate_tax_per_sq_mean,
avg(population_density) as population_density_mean
FROM analytic;

3. Create normalized of analytic which is taking the data of each cell and divide by its column mean

CREATE TABLE analytic_normalized
AS SELECT
state as state,
county as county,
bridges/avg(bridges) over () bridges_normalized,
residents/avg(residents) over () residents_normalized,
pct_medium_bridges/avg(pct_medium_bridges) over () pct_medium_bridges_normalized,
pct_poor_bridges/avg(pct_poor_bridges) over () pct_poor_bridges_normalized,
miles_freight_railroad/avg(miles_freight_railroad) over () miles_freight_railroad_normalized,
roads_acceptable/avg(roads_acceptable) over () roads_acceptable_normalized,
county_area/avg(county_area) over () county_area_normalized,
tax_respondants/avg(tax_respondants) over () tax_respondants_normalized,
state_local_income_tax/avg(state_local_income_tax) over () state_local_income_tax_normalized,
real_estate_tax/avg(real_estate_tax) over () real_estate_tax_normalized,
population_served/avg(population_served) over () population_served_normalized,
water_systems/avg(water_systems) over () water_systems_normalized,
ratio_fair_to_poor/avg(ratio_fair_to_poor) over () ratio_fair_to_poor_normalized,
freight_per_sq_mile/avg(freight_per_sq_mile) over () freight_per_sq_mile_normalized, 
water_sys_per_capita/avg(water_sys_per_capita) over () water_sys_per_cap_normalized,
real_estate_tax_per_sq_mile/avg(real_estate_tax_per_sq_mile) over () estate_tax_per_sq_normalized,
population_density/avg(population_density) over () population_density_normalized
FROM analytic;

4. Create table for standard deviation of each column

CREATE TABLE analytic_std
as
select 
std(bridges) as bridges_std,
std(residents) as resident_std, 
std(pct_medium_bridges) as pct_medium_bridges_std,
std(pct_poor_bridges) as pct_poor_bridges_std,
std(miles_freight_railroad) as miles_freight_railroad_std,
std(roads_acceptable) as roads_acceptable_std,
std(county_area) as county_area_std,
std(tax_respondants) as tax_respondants_std,
std(state_local_income_tax) as state_local_income_tax_std,
std(real_estate_tax) as real_estate_tax_std,
std(population_served) as population_served_std,
std(water_systems) as water_systems_std,
std(ratio_fair_to_poor) as ratio_fair_to_poor_std, 
std(freight_per_sq_mile) as freight_per_sq_mile_std, 
std(water_sys_per_capita) as water_sys_per_cap_std, 
std(real_estate_tax_per_sq_mile) as estate_tax_per_sq_std,
std(population_density) as population_density_std
FROM analytic;

5. Go to export(./export.md) for further instructions