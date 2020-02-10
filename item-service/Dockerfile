FROM openjdk:8
VOLUME /tmp
ADD ./build/libs/item-service-0.0.1-SNAPSHOT.jar item-service.jar
ENTRYPOINT ["java", "-jar", "item-service.jar"]