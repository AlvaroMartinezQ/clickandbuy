#!/bin/bash

echo "Shell script to compile Spring boot projects"

cd ./clickandbuy-app
if [ -d "/target" ]
then
    mvn clean
fi
mvn package

cd ../internal-service
if [ -d "/target" ]
then
    mvn clean
fi
mvn package
