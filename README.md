# character-management
Character management API

## Built With


* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[H2](https://www.h2database.com/) - Open-Source Relational Database Management System 
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* 	[OpenFeign](https://spring.io/projects/spring-cloud-openfeign) - Declarative REST Client. Feign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
* 	[Bootstrap](https://maven.apache.org/) - Bootstrap is an open source toolkit for developing with HTML, CSS, and JS.

## External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)

## To-Do

- [ ] Microservice Architecture
- [ ] NoSQL (MongoDB)
- [ ] Security (JWT Authentication)
- [ ] HATEOS
- [ ] Docker
- [ ] Enhance Exception Handling

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.rakuten.challenge.CharacterManagementFrontendApplication` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvnw spring-boot:run
```

### Actuator

To monitor and manage your application

|  URL |  Method |
|----------|--------------|
|`http://localhost:8081`  						| GET |
|`http://localhost:8081/actuator/`             | GET |
|`http://localhost:8081/actuator/health`    	| GET |
|`http://localhost:8081/actuator/info`      	| GET |
|`http://localhost:8081/actuator/prometheus`| GET |
|`http://localhost:8081/actuator/httptrace` | GET |

## Documentation

* [Swagger](http://localhost:8081/swagger-ui.html) - Documentation & Testing
