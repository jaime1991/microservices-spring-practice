FROM openjdk:8
VOLUME /tmp
ADD ./build/libs/user-service-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]