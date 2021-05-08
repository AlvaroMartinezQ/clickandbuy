#!/bin/bash

# Don't forget to run the following command before running this file:
#   $ chmod +x run.sh

echo "Shell script to compile Spring boot projects"

cd ./clickandbuy-app
if [ -d "/target" ]
then
    mvn clean
fi
mvn package -DskipTests

cd ../internal-service
if [ -d "/target" ]
then
    mvn clean
fi
mvn package -DskipTests
