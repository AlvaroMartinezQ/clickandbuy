FROM openjdk:latest
LABEL Alvaro Martinez <alvaroo2302@gmail.com>
RUN java -version
COPY . ./clickandbuy-app/target/clickandbuy-app-0.0.1-SNAPSHOT.jar
RUN dir
ENTRYPOINT ["java","-jar","./clickandbuy-app-0.0.1-SNAPSHOT.jar"]