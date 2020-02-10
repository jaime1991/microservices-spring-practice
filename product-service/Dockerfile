FROM openjdk:8
VOLUME /tmp
ADD ./build/libs/product-service-0.0.1-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java", "-jar", "product-service.jar"]