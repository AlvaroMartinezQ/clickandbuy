FROM openjdk:12
RUN echo application serivce container is booting up
COPY ./clickandbuy-app/target/clickandbuy-app-0.0.1-SNAPSHOT.jar /opt
COPY ./wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh
# Internal service PATH is passed in the next command as it can't be change from the YAML file
CMD ["java", "-jar", "/opt/clickandbuy-app-0.0.1-SNAPSHOT.jar", "--intern=cnbinternal"]