#
# Build stage
#
FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /home/app/target/* /usr/local/lib/mail.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/usr/local/lib/mail.jar"]