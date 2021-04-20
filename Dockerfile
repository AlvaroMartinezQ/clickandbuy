FROM openjdk:latest
LABEL Alvaro Martinez <alvaroo2302@gmail.com>
RUN java -version
COPY ./clickandbuy-app/target/clickandbuy-app-0.0.1-SNAPSHOT.jar /opt
ENTRYPOINT ["java","-jar","/opt/clickandbuy-app-0.0.1-SNAPSHOT.jar"]