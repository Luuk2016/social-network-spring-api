
## Social network Spring REST API (Java 17)

### Build status
|Master|Develop|
|------|-------|
|[![Java CI with Maven](https://github.com/Luuk2016/socialnetwork-spring-api/actions/workflows/maven.yml/badge.svg?branch=master&event=push)](https://github.com/Luuk2016/socialnetwork-spring-api/actions/workflows/maven.yml)|[![Java CI with Maven](https://github.com/Luuk2016/socialnetwork-spring-api/actions/workflows/maven.yml/badge.svg?branch=develop&event=push)](https://github.com/Luuk2016/socialnetwork-spring-api/actions/workflows/maven.yml)|

### Description
- API Base endpoint: localhost:8080/api
- Swagger: localhost:8080/api/swagger-ui.html
- H2 console: localhost:8080/api/h2-console

### Prerequisites
* JDK 17
* Maven

### How to run (CLI)
Make sure you have all prerequisities, no Maven wrapper is included.
1. Clone/download the repository.

2. Open a terminal and move your directory to the project.

3. Run the app:
```
mvn spring-boot:run
```

### Built with
- [Spring](http://spring.io/) - Jakarta EE Framework
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [H2 Database)](https://www.h2database.com/html/main.html) - for development purposes
- [Project Lombok](https://projectlombok.org/) - used to reduce boilerplate code
- [Springdoc OpenAPI](https://github.com/springdoc/springdoc-openapi) - used for generating API docs
- [JJWT](https://github.com/jwtk/jjwt) - used for creating and verifying JWTs

### License
This application is open-sourced software licensed under the [MIT license](https://opensource.org/licenses/MIT).
