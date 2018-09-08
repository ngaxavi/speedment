<img align="center" src="https://github.com/speedment/speedment-resources/blob/master/src/main/resources/wiki/frontpage/Duke-Spire.png?raw=true." alt="Spire the Hare" title="Spire" width="900px">

Java Stream ORM
====================================================

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.speedment/runtime/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.speedment/runtime)
[![Javadocs](http://www.javadoc.io/badge/com.speedment/runtime-deploy.svg)](http://www.javadoc.io/doc/com.speedment/runtime-deploy)
[![Build Status](https://travis-ci.org/speedment/speedment.svg?branch=develop-3.0)](https://travis-ci.org/speedment/speedment)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)](https://raw.githubusercontent.com/speedment/speedment/master/LICENSE)
[![Join the chat at https://gitter.im/speedment/speedment](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/speedment/speedment?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Speedment is an open source Java Stream ORM toolkit and runtime. The toolkit analyzes the metadata of an existing SQL database and automatically creates a Java representation of the data model. This powerful ORM enables you to create scalable and efficient Java applications using **standard Java** streams with no need to type SQL or use any new API. 

Speedment was originally developed by researchers and engineers based in Palo Alto with the purpose to simplify and streamline the development of Java database applications by leveraging the Java Stream API. 

Speedment is licensed under the business-friendly Apache 2 license. Contribution from users is encouraged. Please feel free to request new features, suggest improvements and file bug reports. Read more about contributing [here](https://github.com/speedment/speedment/blob/master/CONTRIBUTING.md). 

<img src="https://github.com/speedment/speedment-resources/blob/master/src/main/resources/wiki/frontpage/Spire-Quick-Start.png?raw=true" alt="Spire the Hare" title="Spire" align="right" width="20%" />

## Quick Start

Assuming you have Maven installed and a relational database available, you can start using Speedment in a minute:

* [Start a New Speedment Maven Project](https://github.com/speedment/speedment/wiki/Start-a-New-Speedment-Maven-Project)
* [Connect to Your Database](https://github.com/speedment/speedment/wiki/Connect-to-Your-Database)

## Expressing SQL as Java Streams

There is a remarkable resemblance between Java streams and SQL as summarized in the simplified table. This means there is no need for manually writing SQL-queries any more. You can remain in a pure Java world!

<img align="left" src="https://github.com/speedment/speedment-resources/blob/master/src/main/resources/wiki/frontpage/SQL-Stream.png?raw=true." alt="Streams to SQL" width="400px">

#### Example
Search in a film database for a film with a length greater than 120 minutes:
```java
// Searches are optimized in the background!
Optional<Film> longFilm = films.stream()
    .filter(Film.LENGTH.greaterThan(120))
    .findAny();
``` 

Results in the following SQL query:
```sql
SELECT 
    `film_id`,`title`,`description`,`release_year`,
    `language_id`,`original_language_id`,`rental_duration`,`rental_rate`,
    `length`,`replacement_cost`,`rating`,`special_features`,
    `last_update` 
FROM 
     `sakila`.`film
WHERE
    (`length` > 120)
```
<br>

## Features
Speedment is equipped with the features listed below and more. 

### View Database Tables as Standard Java Streams

<img src="https://github.com/speedment/speedment-resources/blob/master/src/main/resources/wiki/frontpage/stream-api.png?raw=true" alt="Stream API" title="Stream API" align="right" width="25%" />

* **Pure Java** - Stream API instead of SQL eliminates the need of a query language<br>
* **Dynamic Joins** - Ability to perform joins as Java streams on the application side<br>
* **Parallel Streams** - Workload can automatically be divided over several threads<br>
<br>

### Short and Concise Type Safe Code 

<img src="https://github.com/speedment/speedment-resources/blob/master/src/main/resources/wiki/frontpage/type-safety.png?raw=true" alt="Type Safety" title="Type Safety" align="right" width="25%" />

* **Code Generation** - Automatic Java representation of the latest state of your database eliminates boilerplate code and the need of manually writing Java Entity classes while minimizing the risk for bugs.<br>
* **Null Protection** - Minimizes the risk involved with database null values by wrapping to Java Optionals<br>
* **Enum Integration** - Mapping of String columns to Java Enums increases memory efficiency and type safety<br>

<img src="https://github.com/speedment/speedment-resources/blob/master/src/main/resources/wiki/frontpage/lazy-evaluation.png?raw=true" alt="Lazy Evaluation" title="Lazy Evaluation" align="right" width="25%" />

### Lazy Evaluation for Increased Performance

* **Streams are Lazy** - Content from the database is pulled as elements are needed and consumed<br>
* **Pipeline Introspection** - Optimized performance by short circuiting of stream operations<br>

## Tutorials
The tutorials are divided into three sections. The basics are covered in the first section without any expected prior knowledge of Speedment. This builds a foundation of knowledge needed to fully benefit from the following tutorials.

#### Basics
* [Tutorial 1 - Hello Speedment](https://github.com/speedment/speedment/wiki/Tutorial:-Hello-Speedment)
* [Tutorial 2 - A First Stream from Speedment](https://github.com/speedment/speedment/wiki/Tutorial:-A-First-Stream-from-Speedment)

#### Sample applications
* [Tutorial 3 - Speedment Spring Boot Integration; REST assured - it is easy](https://github.com/speedment/speedment/wiki/Tutorial:-Speedment-Spring-Boot-Integration)
* [Tutorial 4 - Speedment filters based on Json Web Tokens](https://github.com/speedment/speedment/wiki/Tutorial:-Speedment-Stream-Filters-Using-JWT-Data)
* [Tutorial 5 - Build a Social Network](https://github.com/speedment/speedment/wiki/Tutorial:-Build-a-Social-Network)
* [Tutorial 6 - Log errors in a database](https://github.com/speedment/speedment/wiki/Tutorial:-Log-errors-in-a-database)
* [Tutorial 7 - Use Speedment with Java EE](https://github.com/speedment/speedment/wiki/Tutorial:-Use-Speedment-with-Java-EE)
* [Tutorial 8 - Create Event Sourced Systems](https://github.com/speedment/speedment/wiki/Tutorial:-Create-an-Event-Sourced-System)

#### Extending Speedment
* [Tutorial 9 - Writing your own extensions](https://github.com/speedment/speedment/wiki/Tutorial:-Writing-your-own-extensions)
* [Tutorial 10 - Plug-in a Custom TypeMapper](https://github.com/speedment/speedment/wiki/Tutorial:-Plug-in-a-Custom-TypeMapper)

## Resources 
* **Documentation** - Read the [Speedment User Guide](https://speedment.github.io/speedment-doc/introduction.html).
* **JavaDocs** - Latest [Speedment JavaDocs](http://www.javadoc.io/doc/com.speedment/runtime-deploy/3.1.0). 
* **Examples** - There are 15 detailed examples [here](https://github.com/speedment/speedment/tree/master/example-parent) and more can be found in the User Guide provided above. 
* **Gitter Chatroom** - Reach out to the Speedment developers and other community members via [the Gitter chatroom](https://gitter.im/speedment/speedment). 
* **Creating a Pull Request** - Pull requests and improvement suggestions from the community are gladly accepted. Find more information [here](https://github.com/speedment/speedment/blob/master/CONTRIBUTING.md).

## Requirements
Speedment requires `Java 8` or later. Make sure your IDE is configured to use JDK 8 (version 1.8.0_40 or newer).

Speedment Open Source comes with support for the following databases out-of-the-box:
* MySQL
* MariaDB
* PostgreSQL

For Enterprise database connectors see [www.speedment.com](www.speedment.com/pricing). 

## Licenses
* **Speedment Open Source** - This site covers the Speedment Open Source project available under the 
[Apache 2 license](http://www.apache.org/licenses/LICENSE-2.0). 
* **Speedment Enterprise** - The enterprise product with support for commercial databases (i.e. Oracle, MS SQL Server, DB2, AS400) and in-JVM-memory acceleration can be found at [www.speedment.com](http://speedment.com/).

## Copyright

Copyright (c) 2014-2018, Speedment, Inc. All Rights Reserved.
Visit [www.speedment.com](http://www.speedment.com/) for more info.

[![Analytics](https://ga-beacon.appspot.com/UA-64937309-1/speedment/main)](https://github.com/igrigorik/ga-beacon)

[Github activity visualized](https://www.youtube.com/watch?v=Rmc_3lLZQpM)
