# Instructions for merging data on hive

Make sure in the directory final_input, taxdata.csv, transportationdata.csv, and waterdata.csv in same directory. 

1. Put files on hdfs 

- Make a directory for each input file
hdfs dfs -mkdir tax_final
hdfs dfs -mkdir transport_final
hdfs dfs -mkdir water_final

- Create a directory for output
hdfs dfs -mkdir output_final 

cd final_input

hdfs dfs -put taxdata.csv tax_final
hdfs dfs -put transportationdata.csv transport_final
hdfs dfs -put waterdata.csv water_final

2. Go onto hive
- beeline
- !connect jdbc:hive2://babar.es.its.nyu.edu:10000/;
- use (Your Net ID);

3. Create tables

- Create transport table

create external table transport(hash string, state string, county string, numberOfBridges int, numberOfResidents int, pctOfMediumToFairConditionBridges double, pctOfPoorConditionBridges double, primaryAndCommercialAirports int, routeMilesOfFreightRailroad double, routeMilesOfPassengerRailroadAndRailTransit double, roadsAcceptable double) row format delimited fields terminated by ',' location '/user/(your net id)/transport_final';

- Create Tax table

create external table tax(hash string, state string, county string, numberOfReturns int, adjustGrossIncome int, totalIncomeAmount int, salariesAndWagesAmount int, stateAndLocalIncomeTaxAmount int, realEstateTaxes int, totalTaxesPaid int, residentialEnergyTaxCreditAmount int) row format delimited fields terminated by ',' location '/user/(your net id)/tax_final';

- Create Water Quality table

- create external table water(hash string, state string, county string, populationServed int, waterSystems int, citiesServed int, waterSystemsPerCapita double) row format delimited fields terminated by ',' location '/user/(your net id)/water_final';

4. Combine Tables

- Combine tax and transport tables to create taxtransport

create table taxtransport as select transport.*, tax.numberofreturns, tax.adjustgrossincome, tax.totalincomeamount, tax.salariesandwagesamount, tax.stateandlocalincometaxamount, tax.realestatetaxes, tax.totaltaxespaid, tax.residentialenergytaxcreditamount from transport left outer join tax on (transport.hash = tax.hash);

- Combine taxtransport and water tables to create final

create table final as select taxtransport.*, water.populationserved, water.watersystems, water.citiesserved, water.watersystemspercapita from taxtransport left outer join water on (taxtransport.hash = water.hash);

- Get hive table 

INSERT OVERWRITE LOCAL DIRECTORY '/' ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY "\n" SELECT * FROM final;

# Misc

- Table column names

transport.hash transport.state transport.county transport.numberofbridges transport.numberofresidents transport.pctofmediumtofairconditionbridges transport.pctofpoorconditionbridges transport.primaryandcommercialairports transport.routemilesoffreightrailroad transport.routemilesofpassengerrailroadandrailtransit transport.roadsacceptable 

tax.hash,tax.state tax.county,tax.numberofreturns,tax.adjustgrossincome,tax.totalincomeamount,tax.salariesandwagesamount,tax.stateandlocalincometaxamount,tax.realestatetaxes,tax.totaltaxespaid,tax.residentialenergytaxcreditamount

water.hash, water.state, water.county, water.populationserved, water.watersystems, water.citiesserved, water.watersystemspercapita