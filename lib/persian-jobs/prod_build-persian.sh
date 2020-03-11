#!/bin/sh

echo "Compiling and uploading to local repository of Persian"
mvn clean package -f lib/persian-jobs
mvn install:install-file -Dfile=target/persian-jobs-1.0-SNAPSHOT.jar -Dsource=target/persian-jobs-1.0-SNAPSHOT-sources.jar -DpomFile=pom.xml -DlocalRepositoryPath=../../service/local-persian-repository -f lib/persian-jobs
