docker-compose -f docker-compose.mongo.yml up -d
./mvnw clean compile test install
./mvnw -pl main -am spring-boot:run

