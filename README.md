# spring-template

### Dependencies
  * Spring Web
  * Thymeleaf
  * Spring Boot DevTools
  * Spring Security
  * H2 Database
  * MyBatis Framework
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
### Thymeleaf
##### Tags
|Tag|Example|
|---|-------|
|th:text|```<h1 th:text="${welcomeMessage}">Hello home page ...</h1>```|
|th:each|```<h1 th:each="msg : ${greetings}" th:text="${msg}">Hello home page ...</h1>```|
---