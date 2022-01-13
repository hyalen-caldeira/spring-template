# spring-template

* Dependencies
  * Spring Web
  * Thymeleaf
  * Spring Boot DevTools
  * Spring Security
  * H2 Database
  * MyBatis Framework

* Keywords
  * Ioc
  * Dependency Injection
  * **Application Context**
    * Spring's application context is just a giant data structure that holds all application component instances. It can be queried to gain access to a specified component at runtime, and it's what Spring uses to resolve dependencies
    * Kinda of collection that Spring uses to cache, retrieve and manage components in your application
    * You need to tell Spring which components our application needs and how they depend on one another

* Annotations
  * **@SpringBootApplication** - In a generated project starter, you're always given a main application class with the **@SpringBootApplication** annotation. This annotation is actually equivalent to three other annotations from Spring: **@Configuration**, **@EnableAutoConfiguration**, and @ComponentScan. The **@SpringBootApplication** configures Spring's component scanning and auto-configuration.
    * **@Configuration** - It tells Spring that the annotated class includes component definitions for Spring to process. These take the form of **@Bean-annotated** method whose return values are included as components in the application context. These methods can also take parameters, which act like the dependencies of the components returned by the methods.
    * **@EnableAutoConfiguration** (by name or type) - It tells Spring that it's okay to try to match dependencies to components automatically. Usually, that means dependencies are filled based on type, so in the example above, we have the compoundMessage method which depends on the basicMessage implicitly - the only String bean that Spring is aware of when constructing the compoundMessage is the basicMessage, so it uses that.
    * **@ComponentScan**
  * **@Primary**
  * **@Qualifier**