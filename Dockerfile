FROM maven:3.9.5-amazoncorretto-17-debian AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -e -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:17-alpine3.18
COPY --from=build /usr/src/app/target/leftrightcenter-1.0-SNAPSHOT.jar /usr/app/lcr.jar
RUN mkdir /opt/lcr-out
ENV FILENAME=/opt/lcr-out/lcr.out
ENTRYPOINT ["java","-jar","/usr/app/lcr.jar"]
