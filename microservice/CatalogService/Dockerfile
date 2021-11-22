FROM maven:3.8.2-jdk-11 as maven

WORKDIR /usr/src/app

#download dependency
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . /usr/src/app

RUN mvn package 

ENTRYPOINT ["java","-jar","target/catalog-service-0.0.1-SNAPSHOT.jar"]
