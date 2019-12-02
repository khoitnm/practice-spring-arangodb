## TestContainers:
https://libraries.io/nuget/TestContainers.Container.Database.ArangoDb
https://github.com/testcontainers/testcontainers-java

https://github.com/ganchix/testcontainers-java-module-arangodb (this is convenient, but it's not updated)
        I cannot use `testcontainers-java-module-arangodb` because it uses the old `TestContainers` version and was not updated in more than a whole year
        And it's inherited from the old version parent pom, hence I cannot override their dependencies.
        So I have to copy its code and override their dependencies.

## Trouble shooting
Somehow, when starting the Docker manually from my docker compose (`deployment/arangodb/docker-compose.yml`), it can connect to DB inside that Docker container.
However, when using TestContainers, my test cases cannot connect to that ArangoDB Docker container.

**Still need to investigate the root cause!!!**