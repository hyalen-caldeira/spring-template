# spring-template

### Questions
* Where to launch exception in case something goes wrong? Controller, service, DAO, ...?
* Take a look on 2.11 as example to see how o use @Query annotation on Repositories

### Dependencies
  * Spring Web
  * Thymeleaf
  * Spring Boot DevTools
  * Spring Security
  * H2 Database
  * MyBatis Framework
  * Spring Data JPA
  * MapStruct
    * Observe that, if you are using Lombok, annotationProcessorPaths must be properly configured on pom.xml
  * Lombok
---
### Keywords
* Ioc
* Dependency Injection
* **_Application Context_**
  * Spring's application context is just a giant data structure that holds all application component instances. It can be queried to gain access to a specified component at runtime, and it's what Spring uses to resolve dependencies
  * Kinda of collection that Spring uses to cache, retrieve and manage components in your application
  * You need to tell Spring which components our application needs and how they depend on one another
---
### Annotations
* **_@SpringBootApplication_** - In a generated project starter, you're always given a main application class with the **_@SpringBootApplication_** annotation. This annotation is actually equivalent to three other annotations from Spring: **_@Configuration_**, **_@EnableAutoConfiguration_**, and **_@ComponentScan_**. The **_@SpringBootApplication_** configures Spring's component scanning and auto-configuration.
  * **_@Configuration_** - It tells Spring that the annotated class includes component definitions for Spring to process. These take the form of **_@Bean-annotated_** method whose return values are included as components in the application context. These methods can also take parameters, which act like the dependencies of the components returned by the methods.
    * **_@Bean_**
  * **_@EnableAutoConfiguration_** (by name or type) - It tells Spring that it's okay to try to match dependencies to components automatically. Usually, that means dependencies are filled based on type, so in the example above, we have the compoundMessage method which depends on the basicMessage implicitly - the only String bean that Spring is aware of when constructing the compoundMessage is the basicMessage, so it uses that.
  * **_@ComponentScan_** - If we want to declare custom classes as Spring Components, the best way to do it is to make use of **_@ComponentScan_**, an annotation that tells Spring to search your code base for classes annotated with @Component. These classes will automatically be instantiated as Spring beans, so there's no need to define an **_@Bean_**-annotated method if you already have **_@Component_** on you classes. There are other variants of @Component that identify specific roles for each component to play.
    * **_@Component_**
      * **_@Service_**
      * **_@Controller_**
      * **_@Repository_**
* **_@Primary_**
* **_@Qualifier_**
---
### REST Development
##### Creating Order
1. Entity - Class
   1. data.sql
   2. schema.sql
2. DTO - Class
3. Mapper - Interface
4. Repository - Interface
5. DAO
6. Service
   1. Interface
   2. Implementation
7. Controller

* Pagination
* Profile
---
### H2 Database
* host:port/h2/
---
### Thymeleaf
* Further Reading
  * [The official Thymeleaf tutorial](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#introducing-thymeleaf), which explains the entire framework from first principles.
  * [The official Thymeleaf expression syntax tutorial](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax) - Read this section if all those ${}s aren't making much sense to you.
  * [The official Spring Expression Language docs](https://docs.spring.io/spring-framework/docs/4.3.10.RELEASE/spring-framework-reference/html/expressions.html) - This will tell you how to perform various computations inside of the Thymeleaf expression brackets
---
##### Tags
|Tag|Example|
|---|-------|
|th:text|```<h1 th:text="${welcomeMessage}">Any text here.</h1>```|
|th:each|```<h1 th:each="msg : ${greetings}" th:text="${msg}">Any text here.</h1>```|
|th:if|```<h1 th:if="${anyCondition}">Hello first time ...</h1>```|
|th:unless|```th:unless="${msg.contains('AnyValue'}"```|
---
### Selenium
* Selenium tips here ...
---
### Postman
* Postman tips here ...
---
### Unit & Integration Test
* Test tips here ...
---
### Mapper
* Mapper tips here ...
---
### Swagger
* Swagger tips here ...
---
### JPA - Hibernate
* Hibernate tips here ...
---
### MapStruct
* Mapper tips here ...
---
### GraphQL
* GraphQL tips here ...
---