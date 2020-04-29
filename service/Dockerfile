FROM openjdk:12

RUN mkdir -p persian/service/bin
RUN mkdir persian/service/ssl

COPY /ssl/keystore.jks ./persian/service/ssl
COPY /target/PersonalWebsite-1.0.jar ./persian/service/bin
COPY /config.yml ./persian/service
COPY .credentials ./persian/service/
WORKDIR ./persian/service
EXPOSE 8080
EXPOSE 8443
ENTRYPOINT ["java", "-Ddw.databaseURI=mongodb://persian_admin:123456@persian_database:27017/", "-jar","bin/PersonalWebsite-1.0.jar", "server", "config.yml"]
