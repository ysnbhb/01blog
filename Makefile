run:
	./mvnw spring-boot:run
db:      
	docker rm -f 01blogdb
	docker run --name 01blogdb \
  	-e POSTGRES_USER=ysnbhb \
  	-e POSTGRES_PASSWORD=Yassine@blog@01@blog \
  	-e POSTGRES_DB=blogdb \
  	-p 5432:5432 \
  	-d postgres:15
db_bash:
	docker exec -it 01blogdb bash 
admin:
	./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081 --admin"