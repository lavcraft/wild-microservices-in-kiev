# Spring cloud example

## Samples

 * five microservices that are called each other
 * eureka server (or you may use consul with small changes) (http://{you_docker_machine_host}:8761/)
 * gateway server with hystrix dashboard (see http://{you_docker_machine_host}:9000/hystrix/) and service routing
 * ops server with open zipking tracing through sleuth (http://{you_docker_machine_host}:9411/)
 * turbine server for colleting hystrix data from all services through rabbit-mq

## Build, run, rebuild

build app:   `./gradlew build`
build image: `./gradlew dockerBuild`
run all:     `./run up -d`
rebuild:     `./rebuild-{sub-project-name}`

rebuild script redeploy app automatically

example:

    ./gradlew build && ./run up -d && ./rebuild-rent-service
