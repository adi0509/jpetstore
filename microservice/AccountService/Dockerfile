FROM maven:3.8.2-jdk-11 as maven

WORKDIR /usr/src/app

#download dependency
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . /usr/src/app

#download dependency
# RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]

RUN mvn package 

ENTRYPOINT ["java","-jar","target/account-microservice-0.0.1-SNAPSHOT.jar"]
