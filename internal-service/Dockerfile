FROM openjdk:12
RUN echo internal service container is booting up
COPY ./target/clickandbuy-api-0.0.1-SNAPSHOT.jar /opt
# Do NOT change this port. Otherwise this service won't work
EXPOSE 8081
CMD ["java", "-jar", "/opt/clickandbuy-api-0.0.1-SNAPSHOT.jar"]