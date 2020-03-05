#!/bin/sh

echo "Compiling and uploading to local repository of Persian"
mvn clean package
mvn install:install-file -Dfile=target/exercise-tracker-1.2.jar -Dsource=target/exercise-tracker-1.2-sources.jar -DpomFile=pom.xml -DlocalRepositoryPath=../../service/local-persian-repository
