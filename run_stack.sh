docker-compose -f docker-compose.mongo.yml start
./mvnw clean compile test install
./mvnw -pl main -am spring-boot:run

