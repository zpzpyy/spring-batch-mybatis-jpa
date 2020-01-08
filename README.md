# spring-batch-mybatis-jpa example

## Synopsis

* springboot base batch process example
* Multi dataSource -> Java Config Setting example (Default,Jpa,Batch)
* springboot + spring-batch + gradle + mariaDb

## Development environment

* STS 3.9.9
* Java 1.8
* SpringBoot 2.X
* Gradle 5.6.X
* mariaDB 10.2.X

## Use library

* Spring-boot 2.2.X
* Spring-core 5.2.X
* Spring-batch-core 4.2.X
* Mariadb-java-client 2.3.X
* Mybatis-spring 2.0.X
* spring-boot-starter-data-jpa 2.2.X
....

## Function list

* DB Read   -> FILE writer  (MyBatisCursorItemReader)
* FILE Read -> DB writer    (MyBatisBatchItemWriter)
* DB Read   -> FILE writer  (JpaPagingItemReader)
* FILE Read -> DB writer    (JpaItemWriter)

## Process

* Scheduler -> Job -> Step -> Reader -> Process -> Writer


## PerformanceTest

<br/><br/>
<img src="https://user-images.githubusercontent.com/37128830/71947536-875b6200-3210-11ea-8ed4-f69630e2e9a2.png">
