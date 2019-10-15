FROM java:8
FROM maven:alpine

WORKDIR /app

COPY . /app

RUN mvn -v
RUN mvn clean install -DskipTests

EXPOSE 8080

LABEL maintainer=“ravneetkaur8189@gmail.com”

ADD ./target/tradeshift-challenge-0.0.1-SNAPSHOT.jar tradeshift-challenge-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","tradeshift-challenge-0.0.1-SNAPSHOT.jar"]


