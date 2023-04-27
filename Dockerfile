FROM eclipse-temurin:18
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} universitypanel-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/universitypanel-0.0.1-SNAPSHOT.jar"]