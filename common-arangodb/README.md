This code is basically very similar to Spring Boot ArangoDB: https://github.com/arangodb/spring-boot-starter/blob/master/pom.xml
The reason I don't use Spring Boot ArangoDB directly is that it is not mature and maintained frequently enough, it's still using old Arango & Spring versions.
In the future, when Spring Boot ArangoDB becomes more mature, we may not need this module anymore.

Or I may just use Spring Boot ArangoDB and override its dependencies?