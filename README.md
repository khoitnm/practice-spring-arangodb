# Practice with ArangoDB
https://www.arangodb.com/arangodb-events/webinar-arangodb-graphql-spring-boot-starter/
https://github.com/ArangoDB-Community/arangodb-graphql-spring-boot-starter
https://www.arangodb.com/tutorials/tutorial-sync-java-driver/
https://github.com/arangodb/arangodb-java-driver/tree/master/src/test/java/com/arangodb/example
https://www.arangodb.com/docs/stable/deployment-cloud-aws.html
https://www.arangodb.com/tutorials/spring-data/

## Spring Boot ArangoDB:
https://github.com/arangodb/spring-boot-starter/blob/master/pom.xml
This project seems not to be updated frequently which is still using old Spring Boot version.

## Storage Engine
https://www.arangodb.com/docs/stable/architecture-storage-engines.html
Since version 3.4, the default storage engine is `RocksDB`.

## ArangoDB TestContainer
https://github.com/ganchix/testcontainers-java-module-arangodb

# Challenges
1. `arangodb-spring-boot` is not frequently updated and using old dependencies version (2019-12-04).
2. `testcontainers-java-module-arangodb` is not frequently updated and using old dependencies version (2019-12-04). 
    - Not only that, it extends from the parent `org.testcontainers:1.6.0` (old version) which makes us very difficult to override that old dependencies in client projects.
    - And actually, **I still cannot make it work!?**
3. Using `n+1` query for `@Ref` entities (eager loading), which makes that pattern ineffective. (I mentioned this in the README.md and sample code of `pro-01-simple-entity`) 
4. Problem with "Mapping `RETURN merged` result to Java object" (I described in detail in the README.md of `pro-01-simple-entity`)