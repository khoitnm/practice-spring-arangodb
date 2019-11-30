package org.tnmk.practicespringarangodb.common.arangodb;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * At this moment, Spring Boot ArangoDB is not mature enough, it's still using old Arango & Spring versions.
 * That's why I don't use it. Hence I have to create this class by myself.
 * In the future, when Spring Boot ArangoDB becomes more mature, we may not need this class anymore.
 */
//@Configuration //It's already configured in ArangoDBAutoConfiguration's annotation
@ConfigurationProperties("spring.data.arangodb")
public class ArangoDBDataSource {
    private String host;
    private Integer port;
    private String database;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
