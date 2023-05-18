FROM eclipse-temurin:17
WORKDIR /app
COPY . .
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
ENTRYPOINT ["java","-jar","target/universitypanel-0.0.1-SNAPSHOT.jar"]