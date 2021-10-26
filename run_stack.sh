docker-compose -f docker-compose.mongo.yml up -d
mvn clean compile test install
mvn -pl main -am spring-boot:run

