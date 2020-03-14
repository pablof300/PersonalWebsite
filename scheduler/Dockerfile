FROM openjdk:12
RUN mkdir -p persian/scheduler/bin
COPY /target/scheduler-1.0-SNAPSHOT.jar ./persian/scheduler/bin
COPY .credentials ./persian/scheduler
WORKDIR ./persian/scheduler
ENTRYPOINT ["java", "-jar", "-DdatabaseURI=mongodb://persian_admin:123456@database:27017/", "-DpersianRestHost=rest", "bin/scheduler-1.0-SNAPSHOT.jar"]
