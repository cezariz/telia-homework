# Tealia homework Backend API for jsonplaceholder.typicode.com endpoints

* [Requirements](#requirements)
* [About project](#about-project)
* [Prerequisites](#prerequisites)
* [Run](#run)
* [Test](#test)
* [Authentication](#authentication)
* [GraphQL playground](#graphql-playground)
* [Author](author)

## Requirements
    Telia Homework
    1. Use https://jsonplaceholder.typicode.com/ and find out posts, comments, users REST API
    resources
    2. Create a backend service with your favorite framework and provide one GraphQL endpoint to
    query the resources above. Providing GraphQL playground is good enough. You do not need to
    create UI for your backend.
    3. We prefer Koltin and Ktor but you are not limited.
    4. We are looking at your documentation, code quality, commits history, performance and
    optimization.

## About project
- [Package by Feature](https://www.techyourchance.com/popular-package-structures#package-by-feature) project structure
- GraphQl endpoints for querying Users and Posts  recources from [jsonplaceholderApi](https://jsonplaceholder.typicode.com/).
- [GraphQl playground](#graphql-playground).
- [EditorConfig](https://EditorConfig.org) for coding style formating
- Minimal documentation for UserService and PostService, and schema.graphqls
- Basic [authentication](#authentication)
- Sample integration tests for Post endpoint. To run tests see [Test](#test)
- Logs for debugging
- Used [WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html) and [WebClient](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-client)

## Prerequisites
* Java 17
* Maven 3

## Run
    mvn clean spring-boot:run

## Test
    mvn clean test

## GraphQl playground
After running the project you can access the [GraphQL playground](http://localhost:8080/graphiql?path=/graphql).

## Authentication
Basic authentication
`user:letmein`

## Author
[Julius Ramonas ✉️](mailto:julius@ramonas.xyz)
