# Spring Template

---

## Directory Structures
* config
  * domain
    * All DAOs
  * AuditConfig
  * AuditorAwareImpl
  * DataConfig
  * DomainConfig
  * SecurityConfig
  * SwaggerConfig
  * WebMvcConfig
* core
  * Packages
    * dao
    * dto
    * mapper
    * repository
    * service
    * web
  * ApiResponse
  * Domain
  * InvalidFieldException
  * NotFoundException
  * ValidationError
* Interceptor
* Model
  * Audit
    * DateAudit
  * Entity Tables
* Security
---
## Dependencies
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
## Active Profiles
* In order to set up which profile (Production, Development, Test, etc) should be considered, each application should set up the environment variable SPRING_PROFILES_ACTIVE. If there are more than one profile it should be separated by comma
  * Example: $ export SPRING_PROFILES_ACTIVE=QA, Development
  * The environment variable could obviously be created through of any shell script startup file
* The profiles can also be informed by command line
  * Example: $ java -jar -Dspring.profiles.active=production name_of_application.jar
* Profile can be set on maven through <profiles> tag
  * Run specific profile - mvn clean package -Pprod
  * On application.properties - spring.profiles.active=@spring.profiles.active@
---
## REST Development
### Creating Order
1. Entity - model
   1. data.sql
   2. schema.sql
2. Core
3. Files
    1. ApiResponse
    2. Domain
    3. InvalidFieldException
    4. NotFoundException
    5. ValidationError
4. DTO - Class
5. Mapper - Interface
6. Repository - Interface
7. DAO
8. Config
   1. Domain
      1. DAO config
   2. Audit
   3. Auditor Aware
   4. Data
   5. Domain
   6. Security
   7. Swagger
   8. Web MVC
9. Service
   1. Interface
   2. Implementation
10. Web - Controller
---
## JPA - Hibernate - DataSource - application.properties
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
## **Unit & Integration Test**
### **Types of Testing**
Software testing is broadly divided into the following types - Functional, Non-Functional, and Maintenance testing, as described below:
* **_Functional_** testing is usually performed by the Quality and Development team and sometimes Business. Functional testing includes unit testing, integration testing, system testing, sanity testing, UAT(user acceptance testing) testing, regression, amongst others.
  * Unit testing is the process of testing an individual, usually small, software module or component independently to find bugs. These tests are required to be written by the developers as it requires detailed knowledge of the code.
* **_Non-Functional_** testing is performed by specialized testing teams. Non-functional testing includes performance testing, load testing, stress testing, security testing, reliability testing, usability testing, compliance testing, amongst others.
* **_Maintenance_** testing is performed on already deployed softwares. It includes regression testing, and confirmation testing. The need for maintenance testing arises when a software undergoes some addition/change in functionality.
### Spring Testing Annotations
* **_@WebMvcTest_** - used for Unit Test
  * Is used for controller layer unit testing. Scans only for the controllers (@Controller, @RestController, ...)
* **_@MockBean_**
  * The beans (Services, Repositories, etc) are not auto-created. You must mock them
  * Autowired MockMvc to simulate http request
* **_SpringBootTest_** - user for Integration Test
  * The **_@SpringBootTest_** annotation is useful for integration testing and is chosen over @WebMvcTest because **_@SpringBootTest_** starts the full application context (including the server) and does not customize component scanning at all.
  * **_@SpringBootTest_** will look for the main configuration class, annotated with@SpringBootTest and use that to start a Spring application context that simulates a calling client.
  * This annotation goes on your unit test class. creates an entire Spring ApplicationContext when running unit tests. It is used if you need to test controller or service classes, or perform integration tests spanning multiple layers.
* **_@DataJpaTest_**
  * This annotation provides an alternate way to test your data layer without providing an application.properties file. It disables Spring autoconfiguration and automatically uses an in-memory database if available. It only loads Entities and Spring Data JPA repositories, but not your Services or Controllers.
* **_TestEntityManager_**
  * **_TestEntityManager_** is a class provided by Spring Boot that provides useful methods for persisting test data inside persistence unit tests. It is still available in @DataJpaTests despite the rest of the app not being wired up.
* **_@AutoConfigureTestDatabase_**
  * This annotation can be used with either @SpringBootTest or @DataJpaTest. You can use it to customize Spring’s behavior for replacing the normal datasource. For example, the following annotation could be used in conjunction with @DataJpaTest to indicate that Spring should NOT replace the datasource with an in-memory datasource.
    * ```@AutoConfigureTestDatabase(replace=Replace.NONE)```
### Few important methods and annotations provided by Mockito are:
* **_mock()/@Mock_** - To create mock object. Here, mock() is an overloaded static method which accepts Class<T> classToMock as an argument and correspondingly returns a mock instance.
* **_when()/given()_** - To specify the behaviour of mock object. when() method takes a particular method X as argument and set the expected (mocked) return value as Y. Following are the few examples:
```java
when(mock.someMethod()).thenReturn(200);

// parameterized methods as argument
when(mock.someMethod(anyString())).thenReturn(200);

// throw an exception
when(mock.someMethod("some argument")).thenThrow(new RuntimeException());
```
* **_spy()/@Spy_** - It is used for partial mocking. It means that the real methods are invoked but still can be verified and stubbed.
```java
// Declare
@Spy
StockManagement spyStockManagement;

// Use
spyStockManagement = spy(new StockManagement());
doReturn("Testing spy ...").when(spyStockManagement).getLocatorCode(VALID_ISBN);
assertEquals("Testing spy ...", spyStockManagement.getLocatorCode(VALID_ISBN));
```
* There are few more methods and annotations available, such as @InjectMocks, verify()
* Note that if a project contains private methods to test, then we can't use Mockito as it does not mock private methods. Mockito assumes that private methods don't exist from the viewpoint of testing.
### Further Reading
* [Writing Great Unit Tests](https://stormpath.com/blog/7-tips-writing-unit-tests-java)
* [Advanced Mockito](https://www.vogella.com/tutorials/Mockito/article.html)
* [PowerMock](https://powermock.github.io/)
* [TestNg](https://testng.org/doc/)
---
## Logging
Log4J is most often used to create an instance of the Logger interface from the LogManager and then call the methods on this interface. Assume there is a MyClass.java, inside which we need to create an instance of Logger interface by using either of the following ways:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Inside the class
public static final Logger log = LogManager.getLogger(MyClass.class) 
public static final Logger log = LoggerFactory.getLogger(MyClass.class)
```
Then, we can use any of the in-built log-levels in any method of MyClass.java, as follows:
```java
// ...
log.trace("the built-in TRACE level");
log.debug("the built-in DEBUG level");
log.notice("a custom level: a NOTICE message");
log.info("the built-in INFO level");
log.warn("the built-in WARN level");
log.error("the built-in ERROR level");
log.fatal("the built-in FATAL level");
// ...
```
Following are the in-built log-levels in highest to the lowest order of logging:
|Log-Level|Description|
|---------|-----------|
|Trace|Logs the fine-grained information. This is a high (most detailed) level of logging|
|Debug|Logs the information necessary for debugging|
|Info|Used for logging the status messages or any desirable field value|
|Warn|Used for logging potentially unexpected/dangerous situations|
|Error|Used for logging Exception and minor Error events|
|Fatal|Used for logging very severe Error events that might lead the application to collapse|
##### Log level configuration - application.properties 
```properties
logging.level.org.springframework.web: DEBUG
logging.level.org.hibernate: ERROR
```
* [Logging](https://www.slf4j.org/manual.html)
---
## Run app
* ```mvn clean install -DskipTests```
---
## MySQL Database
* Create portfolio_db schema and run the following query to create the user  
  ```create user 'sa'@'localhost' identified by 'sa';```  
  ```grant all on portfolio_db.* to 'sa'@'localhost';```
---
## Keywords
* IOC - Inversion of Control
* Dependency Injection
* **_Application Context_**
  * Spring's application context is just a giant data structure that holds all application component instances. It can be queried to gain access to a specified component at runtime, and it's what Spring uses to resolve dependencies
  * Kinda of collection that Spring uses to cache, retrieve and manage components in your application
  * You need to tell Spring which components our application needs and how they depend on one another
---
## Main Annotations
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
---
## H2 Database
* host:port/h2/
---
## Thymeleaf
* Further Reading
  * [The official Thymeleaf tutorial](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#introducing-thymeleaf), which explains the entire framework from first principles.
  * [The official Thymeleaf expression syntax tutorial](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax) - Read this section if all those ${}s aren't making much sense to you.
  * [The official Spring Expression Language docs](https://docs.spring.io/spring-framework/docs/4.3.10.RELEASE/spring-framework-reference/html/expressions.html) - This will tell you how to perform various computations inside of the Thymeleaf expression brackets
##### Tags
|Tag|Example|
|---|-------|
|th:text|```<h1 th:text="${welcomeMessage}">Any text here.</h1>```|
|th:each|```<h1 th:each="msg : ${greetings}" th:text="${msg}">Any text here.</h1>```|
|th:if|```<h1 th:if="${anyCondition}">Hello first time ...</h1>```|
|th:unless|```th:unless="${msg.contains('AnyValue'}"```|
---
## Selenium
* Selenium tips here ...
---
## Postman
* Postman tips here ...
---
## Mapper - MapStruct
* pom.xml (annotationProcessorPaths) must be properly configured. Lombok may cause issues in case not well configured
---
## Swagger
* Create a class SwaggerConfig.java
  * Set "useDefaultResponseMessages" to false in case you want to customize response messages
  * Set the required custom message in the controllers
* application.properties - include the following set
  * spring.mvc.pathmatch.matching-strategy=ant-path-matcher
* http://localhost:8080/swagger-ui/ or http://localhost:8080/swagger-ui/index.html
---
## GraphQL
* GraphQL tips here ...
---
## TODO
* Where to launch exception in case something goes wrong? Controller, service, DAO, ...?
* Take a look on 2.11 as example to see how o use @Query annotation on Repositories
* Swagger bean validation
* Messages returned by controller's methods
* Validate - CompanyServiceImpl
  * Test validate
* Implement profile
* Logs
* Pagination
* Profile
* How to make testing to ignore swagger?
* Create application properties per environment
  * application-test.properties, application-dev.test, etc
* config
  * domain
    * All DAOs
  * AuditConfig
  * AuditorAwareImpl
  * DataConfig
  * DomainConfig
  * SecurityConfig
  * SwaggerConfig
  * WebMvcConfig
  * Parametrize what is possible on application.properties
* Test
  * TestDataConfig
---
