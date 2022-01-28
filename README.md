# spring-template

### TODO
* Where to launch exception in case something goes wrong? Controller, service, DAO, ...?
* Take a look on 2.11 as example to see how o use @Query annotation on Repositories
* Swagger bean validation
* Messages returned by controller's methods
* Validate - CompanyServiceImpl
  * Test validate
* Implement profile
* Logs
* How to make testing to ignore swagger?
* Create application properties per environment
  * application-test.properties, application-dev.test, etc
* config
  * domain
    * All DAOs
  * AuditConfig
  * DataConfig
  * SecurityConfig
  * WebMvcConfig
  * Parametrize what is possible on application.properties
* Test
  * TestDataConfig
---
### Run app
* ```mvn clean install -DskipTests```
---
# MySQL Database
* Create portfolio_db schema and run the following query to create the user
  *```create user 'sa'@'localhost' identified by 'sa';```
  * ```grant all on portfolio_db.* to 'sa'@'localhost';```
---
### Dependencies
* Spring Web
* Thymeleaf
* Spring Boot DevTools
* Spring Security
* H2 Database
* MyBatis Framework
* Spring Data JPA
* Others:
  * MapStruct
    * Observe that, if you are using Lombok, annotationProcessorPaths must be properly configured on pom.xml
  * Lombok
  * Swagger - springfox (springfox-boot-starter)
  * commons-lang3
  * Guava
  * MySQL
  * javax.validation - validation-api
  * org.springframework.boot - spring-boot-starter-validation
---
### Directory Structures
* config
* core
  * Packages
    * dao
    * dto
    * mapper
    * service
    * web
  * ApiResponse
  * Domain
  * InvalidFieldException
  * NotFoundException
  * ValidationError
---
### Active Profiles
* In order to set up which profile (Production, Development, Test, etc) should be considered, each application should set up the environment variable SPRING_PROFILES_ACTIVE. If there are more than one profile it should be separated by comma
  * Example: $ export SPRING_PROFILES_ACTIVE=QA, Development
  * The environment variable could obviously be created through of any shell script startup file
* The profiles can also be informed by command line
  * Example: $ java -jar -Dspring.profiles.active=production name_of_application.jar
* Profile can be set on maven through <profiles> tag
  * Run specific profile - mvn clean package -Pprod
  * On application.properties - spring.profiles.active=@spring.profiles.active@
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
* **_@Value_**
* **_@Profile_**
##### **Unit & Integration Test**
* **_@WebMvcTest_** - used for Unit Test
  * Is used for controller layer unit testing. Scans only for the controllers (@Controller, @RestController, ...)
* **_@MockBean_**
  * The beans (Services, Repositories, etc) are not auto-created. You must mock them
  * Autowired MockMvc to simulate http request
* **_SpringBootTest_** - user for Integration Test
  * The **_@SpringBootTest_** annotation is useful for integration testing and is chosen over @WebMvcTest because **_@SpringBootTest_** starts the full application context (including the server) and does not customize component scanning at all. 
  * **_@SpringBootTest_** will look for the main configuration class, annotated with@SpringBootTest and use that to start a Spring application context that simulates a calling client.
---
### REST Development
##### Creating Order
1. Entity - model
   1. data.sql
   2. schema.sql
2. Core
   1. Files
      1. ApiResponse
      2. Domain
      3. InvalidFieldException
      4. NotFoundException
      5. ValidationError
   2. DTO - Class
   3. Mapper - Interface
   4. Repository - Interface
   5. DAO
   6. Service
      1. Interface
      2. Implementation
   7. Web - Controller

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
* **_@SpringBootTest_**
  * This annotation goes on your unit test class. creates an entire Spring ApplicationContext when running unit tests. It is used if you need to test controller or service classes, or perform integration tests spanning multiple layers.
* **_@DataJpaTest_**
  * This annotation provides an alternate way to test your data layer without providing an application.properties file. It disables Spring autoconfiguration and automatically uses an in-memory database if available. It only loads Entities and Spring Data JPA repositories, but not your Services or Controllers.
* **_TestEntityManager_**
  * **_TestEntityManager_** is a class provided by Spring Boot that provides useful methods for persisting test data inside persistence unit tests. It is still available in @DataJpaTests despite the rest of the app not being wired up.
* **_@AutoConfigureTestDatabase_**
  * This annotation can be used with either @SpringBootTest or @DataJpaTest. You can use it to customize Spring’s behavior for replacing the normal datasource. For example, the following annotation could be used in conjunction with @DataJpaTest to indicate that Spring should NOT replace the datasource with an in-memory datasource.
    * ```@AutoConfigureTestDatabase(replace=Replace.NONE)```
---
### Mapper - MapStruct
* pom.xml (annotationProcessorPaths) must be properly configured. Lombok may cause issues in case not well configured
---
### Swagger
* Create a class SwaggerConfig.java
  * Set "useDefaultResponseMessages" to false in case you want to customize response messages
  * Set the required custom message in the controllers
* application.properties - include the following set
  * spring.mvc.pathmatch.matching-strategy=ant-path-matcher
* http://localhost:8080/swagger-ui/ or http://localhost:8080/swagger-ui/index.html
---
### JPA - Hibernate - DataSource - application.properties
* ```spring.datasource.initialization-mode```
  * Embedded: Default. Initialization performed on embedded databases only.
  * Always: Initialization for both embedded and external databases.
  * Never: For production - No initialization for either embedded or external databases.
* ```spring.jpa.hibernate.ddl-auto``` - this property allows you to customize Hibernate’s initialization behavior.
  * create: Drop all tables for defined Entities, then create them.
  * create-drop: Create tables, drop them when application stops.
  * update: Attempt to migrate previous version of tables to match current Entities.
  * validate: Throw an exception if tables or columns are missing.
  * none: Do not initialize tables.
* show-sql- Spring offers a useful command to print all generated sql commands to the console
  * ```spring.jpa.show-sql=true```
* There’s also a hibernate property for formatting the sql output that makes it easier to read
  * ```spring.jpa.properties.hibernate.format_sql=true```
---
### GraphQL
* GraphQL tips here ...
---