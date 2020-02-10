FROM openjdk:8
VOLUME /tmp
EXPOSE 9001
ADD ./build/libs/oauth-service-0.0.1-SNAPSHOT.jar oauth-service.jar
ENTRYPOINT ["java", "-jar", "oauth-service.jar"]