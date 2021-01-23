# SMART CHOICE
A website that can compare the price of a product from different resources (Tiki, Lazada, Shopee...)

- System Design
- Software Principle
- Project structure
- How to run application
- API Document

### System Design

![alt text](https://github.com/tintin0122/smart_choice/blob/main/images/smart_choice_flow.jpg?raw=true)

##### Discovery Service
The service is a database populated with information on how to dispatch requests to microservice instances.

##### Gateway Service
The service encapsulates the internal system architecture and provides an API that is tailored to each client. 

##### Product Service
This is core business of Smart Choice system. It call to Crawler service when client search for product by using name and store these searching informations.
When client get detail information of specific product, this service will retrieve from Redis database to avoid make a request to Crawler Service.

##### Crawler Service
This service will responsible for searching product on external resources(Tiki,Lazada,Shopee,...) and store these product in database.

##### Audit Service
logging all client activities.

##### Identity Provider
An identity provider is a service that stores and verifies user identity. (e.g: Keycloak)


### Software Principle
##### CLEAN Architecture
Clean architecture is a software design philosophy that separates the elements of a design into ring levels. An important goal of clean architecture is to provide developers with a way to organize code in such a way that it encapsulates the business logic but keeps it separate from the delivery mechanism. 

The main rule of clean architecture is that code dependencies can only move from the outer levels inward. Code on the inner layers can have no knowledge of functions on the outer layers. The variables, functions and classes (any entities) that exist in the outer layers can not be mentioned in the more inward levels. It is recommended that data formats also stay separate between levels.

![alt text](https://github.com/tintin0122/smart_choice/blob/main/images/smart_choice_architecture.jpg?raw=true)

![alt text](https://github.com/tintin0122/smart_choice/blob/main/images/smart_choice_architecture_2.jpg?raw=true)

##### Separation of concerns
Separation of concerns is a principle used in programming to separate an application into units, with minimal overlapping between the functions of the individual units.

##### KISS: Keep It Simple, Stupid
Keep it simple, stupid (KISS) is a design principle which states that designs and/or systems should be as simple as possible. Wherever possible, unnecessary complexity should be avoided in a system.

##### DRY: Don't Repeat Yourself
This is a principle of software development that aims at reducing the repetition of patterns and code duplication in favor of abstractions and avoiding redundancy.

##### SOLID
The SOLID Principles are five principles of Object-Oriented class design. They are a set of rules and best practices to follow while designing a class structure

* The Single Responsibility Principle (S): A class should do one thing and therefore it should have only a single reason to change.
* The Open-Closed Principle (O): Classes should be open for extension and closed to modification.
* The Liskov Substitution Principle (L): Subclasses should be substitutable for their base classes.
* The Interface Segregation Principle (I): Keeping things separated, and the Interface Segregation Principle is about separating the interfaces.
* The Dependency Inversion Principle (D): Our classes should depend upon interfaces or abstract classes instead of concrete classes and functions.

