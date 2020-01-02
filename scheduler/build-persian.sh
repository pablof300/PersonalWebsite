#!/bin/sh

VERSION="$1"
echo "Compiling and uploading to local repository of Persian"
mvn clean package
mvn install:install-file -Dfile=target/scheduler-$VERSION-SNAPSHOT.jar -DpomFile=pom.xml -DlocalRepositoryPath=../service/local-persian-repository
