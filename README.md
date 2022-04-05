# sample-multi-threading

#### Includes:

````
* Ways to create threads in Java
* Create thread pool executors with pre-defined methods
* CallableFuture which represents an asynchronous computation, whose value is available via a Future object.
* CompletableFutureExample which represents some of the method usages
* Mainclass : CustomAsyncTaskProcessor as defined in build.gradle to demonstrate the use of multi-part file to be uploaded to save Car data in H2 database using CompletableFuture
Used Mockaroo to create dummy data.
````

#### Prerequisite:

````
Java 8
IntelliJ IDEA
Gradle
````

#### Database:

````
In-memory H2 connection
Access H2 console: http://localhost:8080/h2-console with driverClass: org.h2.Driver and jdbcUrl: jdbc:h2:mem:testThread
````

#### Build:

````
gradle clean build
````

#### Run Application:

````
gradle bootRun
````