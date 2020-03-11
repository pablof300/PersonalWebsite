#!/bin/sh

echo "Compiling and uploading to local repository of Persian"
mvn clean package -f lib/guice-quartz/
mvn install:install-file -Dfile=target/guice-quartz-sample.jar -DpomFile=pom.xml -Dsources=target/guice-quartz-sample-sources.jar -DlocalRepositoryPath=../../service/local-persian-repository -f lib/guice-quartz/
