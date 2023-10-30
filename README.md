#Best Matched Restaurants
---
A Java API with endpoint  that searches and returns a list of up to five Restaurants based on the specified paraments:
 - Restaurant Name
 - Customer Rating (1 star ~ 5 stars)
 - Distance (1 mile ~ 10 miles)
 - Price ($10 ~ $50 average spend)
 - Cuisine (Chinese, American, Thai, etc.)

For users best experience, the API can be tested using OpenAPI Swagger UI.

---
### Running Project
You must have installed in your system the following requirements:

 - Java Development Kit 21
 - Apache Maven 3.9.5

Unzip the project, and using a terminal access the folder:

`/best-matched-restaurants-wr`

Run the following command to initialize the project:

`mvn spring-boot:run`

Best Match Restaurant API will be initialized and start to run on your machine's `localhost:8080` port by default.

Then access it's OpenAPI interface in the following URL:

[OpenAPI](http://localhost:8080/swagger-ui/index.html)

And try yourself.

---
### Testing Project

To run the project's JUnit unit tests, run the following prompt command:

`mvn test`

---

### Notes
---
Considering the time limitation, some desired developments weren't implemented or weren't implemented in the best away:

- UI using some front-end frameworks such as React or Angular
- Include more tests, especially on the Services layer 
- The CSV reading process may be improved
- Filter methods could be more generic
- Restaurant and Cuisine classes relation was simplified, in a complete CRUD this would be a problem

---

### References

  [Spring Initializr](https://start.spring.io/)

  [Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)

  [OpenCSV Documentation](https://www.baeldung.com/opencsv)

  [Java – Read a file from resources folder](https://mkyong.com/java/java-read-a-file-from-resources-folder/)
  
  [Sorting a Stream by Multiple Fields in Java](https://howtodoinjava.com/java8/sort-stream-multiple-fields/)

  [Exception Handling in Spring Boot REST API]([https://](https://springframework.guru/exception-handling-in-spring-boot-rest-api/))

  [Construindo uma API RESTful com Java e Spring Framework ]([https://](https://mari-azevedo.medium.com/construindo-uma-api-restful-com-java-e-spring-framework-46b74371d107))

