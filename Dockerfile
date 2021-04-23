FROM openjdk:12
RUN echo application serivce container is booting up
COPY ./clickandbuy-app/target/clickandbuy-app-0.0.1-SNAPSHOT.jar /opt
COPY ./wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh
EXPOSE 8443
CMD ["java", "-jar", "/opt/clickandbuy-app-0.0.1-SNAPSHOT.jar"]