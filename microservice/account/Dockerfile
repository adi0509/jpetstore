FROM maven:3.8.2-jdk-11 as maven

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package 

ENTRYPOINT ["java","-jar","target/account-microservice-0.0.1-SNAPSHOT.jar"]
