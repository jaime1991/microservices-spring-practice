# Tecnologias utilizadas

- Spring boot 2
- Spring security
- spring data
- spring web
- Eureka (Service discovery)
- Zuul (Api getway)
- Ribbon (Load Balancing)
- Hystrix (latency and fault tolerance)
- OAuth 2 (security, jwt)
- Docker (container)


# Docker commands

#### Config service

```shell
docker build -t config-service:v1 .
docker network create springcloud
docker run -p 8888:8888 --name config-service --network springcloud config-service:v1
```

#### Eureka server
```shell
docker build -t eureka-server:v1 .
docker run -p 8761:8761 --name eureka-server --network springcloud eureka-server:v1
```

#### Product service
```shell
docker build -t product-service:v1 .
docker run -P --network springcloud product-service:v1
```

#### User service
```shell
docker build -t user-service:v1 .
docker run -P --network springcloud user-service:v1
```

#### Oauth service
```shell
docker build -t oauth-service:v1 .
docker run -p 9100:9100 --network springcloud oauth-service:v1
```

#### Item service
```shell
docker build -t item-service:v1 .
docker run -p 8002:8002 -p 8005:8005 -p 8007:8007 --network springcloud item-service:v1
```

#### Rabbitmq
```shell
docker pull rabbitmq:3.8-management-alpine
docker run -p 15672:15672 -p 5672:5672 --name rabbitmq-service --network springcloud -d rabbitmq:3.8-management-alpine
docker logs -f rabbitmq-service
```

#### Zipkin
```shell
docker build -t zipkin-server:v1 .
docker run -p 9411:9411 --name zipkin-server --network springcloud -e RABBIT_ADDRESSES=rabbitmq-service:5672 zipkin-server:v1
docker logs -f zipkin-server
```

#### Zuul server
```shell
docker build -t zuul-server:v1 .
docker run -p 8090:8090 --network springcloud zuul-server:v1
```

#### Stop and remove all containers
```shell
docker stop $(docker ps -q)
docker rm $(docker ps -a -q)
```

#### Delete all images
```shell
docker rmi $(docker images -a -q)
```
