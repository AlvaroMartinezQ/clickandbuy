FROM openjdk:12
RUN echo hellodocker
COPY ./clickandbuy-app/target/clickandbuy-app-0.0.1-SNAPSHOT.jar /opt
EXPOSE 8443
CMD ["java", "-jar", "/opt/clickandbuy-app-0.0.1-SNAPSHOT.jar"]