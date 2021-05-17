all: clean

clean:
	gradle clean

compile:
	gradle build

jar: compile
	gradle bootJar

run: jar
	echo Starting Spring at:  http://localhost:8080
	java -jar build/libs/springstarbucks-1.0.jar

# Docker

docker-build: jar
	docker build -t springstarbucks .
	docker images

docker-run: docker-build
	docker run --name springstarbucks -td -p 80:8080 springstarbucks
		#docker run -d --name starbucks-api --network kong-network -td springstarbucks 
		# in the lab ^^^
	docker ps

docker-clean:
	docker stop springstarbucks
	docker rm springstarbucks
	docker rmi springstarbucks

docker-shell:
	docker exec -it spring-hello bash 

docker-push:
	docker login
	docker build -t $(account)/springstarbucks:latest -t $(account)/springstarbucks:latest .
	docker push $(account)/springstarbucks:latest 

# Pod

pod-run:
	kubectl apply -f pod.yaml

pod-list:
	kubectl get pods

pod-desc:
	kubectl describe pods spring-hello

pod-delete:
	kubectl delete -f pod.yaml

pod-shell:
	kubectl exec -it spring-hello -- /bin/bash

pod-logs:
	kubectl logs -f spring-hello

# Service

service-create:
	kubectl create -f service.yaml

service-get:
	kubectl get services

service-get-ip:
	kubectl get service spring-hello -o wide

service-delete:
	kubectl delete service spring-hello