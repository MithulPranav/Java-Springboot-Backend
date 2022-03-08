# Java-Springboot-Backend
A basic Java Spring Boot Webserver

Project Specifications:
Java Version: 17
Spring Boot Version: 2.6.4
Project: Gradle project
Database: MongoDB
Docker required to run DB

About
It is a train booking system where APIs can be hit for the following Actions:
1. List all Trains
2. List all Coaches
3. List all Users
4. List all Seats
5. Creating/Deleting Train
6. Creating/Deleting Coach
7. Creating/Deleting User
8. Booking a single seat
9. Booking multiple seats

Project Initialisation:
1. Make sure to have the required dependencies like Java 17 and Docker
2. Download the repo
3. Navigate to the project directory in terminal and start the database with the command ```docker-compose -f docker-compose-dev.yml up```
4. Open another terminal window and navigate to the project directory and start the server with the command ```./gradlew bootRun```

Note:
1. This project was done for an assesment I had attended.
2. By default this project runs on 8080 port. So make sure to hit the APIs to that port to receive response.
3. I don't have professional work experience in Java, so I might not have followed the best practices.
4. I have not implemented database level check constraints and also did not utilise the concept of foreign keys
5. I was not able to figure out how to use JSON in Java SpringBoot, so I had only utilised HashMap.
6. This project was developed on a MacOS, the instructions to start and get the project running are for the same.
