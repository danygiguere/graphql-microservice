# Microservices example with a GraphQL bff (back end for front end) and micro services, using Spring Boot with Kotlin Coroutines r2dbc and JWT


### Help needed
In the bff, I can't figure out how to implement the SecurityConfig.
I'm following this documentation:
- https://docs.spring.io/spring-graphql/reference/security.html
- https://github.com/spring-projects/spring-graphql/blob/1.0.x/samples/webflux-security/src/main/java/io/spring/sample/graphql/SecurityConfig.java
But I can't make it work. 
I'm not pro in terms of spring security but what I understand is that I need to login on http://localhost:8011/login and then I'd have access to the graphql console at http://localhost:8011/graphql. 
From where I'd be able to to reach my api routes.


### About

- This is just a demo project to demonstrate how to build microservices with a GraphQL bff and micro services (using Spring Boot with Kotlin Coroutines, r2dbc and JWT).

### Installation

- To install this project, make sure you have a mysql database running
- Or you can use this dockerized db: https://github.com/danygiguere/docker_db (Note that I'm not adding the h2 in-memory database to this example project).
- Create two database: `micro-services-example-users-service` and `micro-services-example-posts-service`
- Then start the bff and the micro services.
- Then using postman hit this route: http://localhost:8011/graphql

### Todo

- create an app client
- aws
- add jwt and implement Login and register and refresh token
- block micro services so they only accept requests from the bff
- implement reset and forgot password
