# Lottery

## Running the application

Set the postgres user and password in the `compose.yaml` file in the project root.
Use the same values for the postgres user and password in the `src/main/resources/application.properties` file.

Run the application database using `docker compose`.
From the project root execute:

```
docker compose up -d
```

Now run the Spring Boot application with the following command:

```
mvn spring-boot:run
```

**Note!** The project includes maven wrapper. It could be used instead, if maven is not installed locally.

```
./mvnw spring-boot:run
```

To stop and remove the database container, from the project root, run:

```
docker compose down
```

## Testing

Use `curl` to interact with the REST API.

Create a lottery:

```
curl --header "Content-Type: application/json" \
    --request POST --data '{ "name": "Test Lottery", "startDate": "2023-11-22", "endDate": "2023-11-25" }' \
    http://localhost:8080/lotteries
```

The response will be the id of the newly created lottery:

```
{"id":"ebae31c3-b4b3-413f-9cf7-65810c2aad35"}
```

Get running lotteries:

```
curl --request GET http://localhost:8080/lotteries
```

Create participant:

```
curl --header "Content-Type: application/json" \
    --request POST --data '{ "name": "Test Participant", "email": "participant@example.com" }' \
    http://localhost:8080/lotteries/participant
```

The response will be the newly created participant's id:

```
{"participantId":"22ffb50b-03c8-4b8c-bcb6-ee570d0bd9f9"}
```

Buy lottery ticket:

```
curl --header "Content-Type: application/json" \
    --request POST --data '{ "participantEmail": "participant@example.com", "lotteryId": "8e08340e-8b6d-4374-97d4-a5c40e11d764", "luckyNumbers": [23, 5] }' \
    http://localhost:8080/lotteries/ticket
```

The response will be the newly created ticket:

```
{"ticketId":"3414d2cb-3d9c-4c37-9ad3-e489775c297e","number":"XLH23-31-3-32-23-5","lotteryId":"8e08340e-8b6d-4374-97d4-a5c40e11d764","lotteryName":"Test Lottery"}
```
