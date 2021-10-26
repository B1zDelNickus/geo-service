# GeoService test task specification

## Table of contents
1. [Task description](#task)
2. [ENV run instruction](#compose)
3. [Prepare test data](#data)
4. [Tryout API for feo service](#api)

## <a name="task"></a> 1. Task description
- [task](./Evaluation Assignment-BackEnd-Java.pdf "go to PDF task file")

## <a name="compose"></a> 2. ENV run instruction
- `docker-compose -f docker-compose.mongo.yml down`
- `make init-mongo`
- `docker-compose -f docker-compose.mongo.yml up -d`

## <a name="data"></a> 3. Prepare test data
- `sudo apt install -y mongo-tools`
- `sudo apt install -y jq`
- `jq --compact-output ".features" ./data/nuts_rg_60m_2013_lvl_1.geojson > ./data/nuts_rg_60m_2013_lvl_1_ready.geojson`
- `jq --compact-output ".features" ./data/nuts_rg_60m_2013_lvl_2.geojson > ./data/nuts_rg_60m_2013_lvl_2_ready.geojson`
- `jq --compact-output ".features" ./data/nuts_rg_60m_2013_lvl_3.geojson > ./data/nuts_rg_60m_2013_lvl_3_ready.geojson`
- `mongoimport --db testdb -c feature --file ./data/nuts_rg_60m_2013_lvl_1_ready.geojson --jsonArray`
- `mongoimport --db testdb -c feature --file ./data/nuts_rg_60m_2013_lvl_2_ready.geojson --jsonArray`
- `mongoimport --db testdb -c feature --file ./data/nuts_rg_60m_2013_lvl_3_ready.geojson --jsonArray`

## <a name="api"></a> 4. Tryout API for feo service
- `curl http://localhost:8071/api/search/data?yMin=?&yMax=?&xMin=?&xMax=?`