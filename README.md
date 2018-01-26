Introduction
============
This is a sample application to demonstrate Spring Boot, JPA and Axon Framework.

The Todo application makes use of the following design patterns:
- Domain Driven Design
- CQRS
- Event Sourcing

Building
========
> mvn package

Running
=======
> mvn spring-boot:run

Implementation
==============
Implementation notes:
- The event store is backed by a JPA implementation which comes with Axon
- The query model is backed by JPA

Documentation
=============
* Axon Framework - http://www.axonframework.org/
* Spring Boot - http://projects.spring.io/spring-boot/
* Spring Framework - http://projects.spring.io/spring-framework/
