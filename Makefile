.PHONY: stop start

init-mongo:
	@ docker-compose -f docker-compose.mongo.yml up -d mongodb
	@ sleep 5
	@ docker exec -it geo-service-mongo-container mongo /tmp/init-replicaset.js
