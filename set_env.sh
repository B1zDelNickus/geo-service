docker-compose -f docker-compose.mongo.yml up -d
sudo apt install -y mongo-tools jq
echo install deps
jq --compact-output ".features" ./data/nuts_rg_60m_2013_lvl_1.geojson > ./data/nuts_rg_60m_2013_lvl_1_ready.geojson
jq --compact-output ".features" ./data/nuts_rg_60m_2013_lvl_2.geojson > ./data/nuts_rg_60m_2013_lvl_2_ready.geojson
jq --compact-output ".features" ./data/nuts_rg_60m_2013_lvl_3.geojson > ./data/nuts_rg_60m_2013_lvl_3_ready.geojson
echo prepare jsons
mongoimport --db testdb -u user -p pass -c feature --file ./data/nuts_rg_60m_2013_lvl_1_ready.geojson --jsonArray
mongoimport --db testdb -u user -p pass -c feature --file ./data/nuts_rg_60m_2013_lvl_2_ready.geojson --jsonArray
mongoimport --db testdb -u user -p pass -c feature --file ./data/nuts_rg_60m_2013_lvl_3_ready.geojson --jsonArray
echo import to DB
docker-compose -f docker-compose.mongo.yml stop