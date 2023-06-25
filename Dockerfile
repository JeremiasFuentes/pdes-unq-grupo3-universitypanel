FROM maven:3.8.4-openjdk-17-slim

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y --no-install-recommends \
    wait-for-it

RUN mvn clean install -DskipTests

CMD wait-for-it -t 30 db:3306 -- java -jar /app/target/universitypanel-0.0.1-SNAPSHOT.jar