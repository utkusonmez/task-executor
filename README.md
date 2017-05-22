# task-executor

runs and calculates average time of tasks

for running tests and building

$ mvn clean install

api  : clients of task service can use api.jar to communicate

boot : how to start service with its components

impl : implementation of service

All requests are detailed in bin/task-executor.postman_collection.

Also app needs Mongodb on default ports.


(build)
$ mvn clean install
(build and fix jersey bug)
$ mvn clean install spring-boot:repackage --file boot/
(run)
$ java -jar boot/target/task-executer-boot-0.0.1-SNAPSHOT.jar

sample requests
GET localhost:8080/health

POST localhost:8080/task/{task-id}/execute
POST localhost:8080/task/{task-id}/calculateAverageExecutionTime

Sample task id values are -> short, long, always-error


TODOS : integration test with spring boot, run as docker container, a client inside api project for task service