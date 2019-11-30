package org.tnmk.practicespringarangodb.common.arangodb;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.config.ArangoConfiguration;
import com.arangodb.springframework.core.ArangoOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnClass(ArangoDB.class)
@ConditionalOnMissingBean(ArangoOperations.class)
@EnableConfigurationProperties(ArangoDBDataSource.class)
@Import(ArangoRepositoriesAutoConfigureRegistrar.class)
public class ArangoDBAutoConfiguration implements ArangoConfiguration {
    private final ArangoDBDataSource arangoDBDataSource;

    public ArangoDBAutoConfiguration(ArangoDBDataSource arangoDBDataSource) {
        super();
        this.arangoDBDataSource = arangoDBDataSource;
    }

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host(arangoDBDataSource.getHost(), arangoDBDataSource.getPort())
                .user(arangoDBDataSource.getUsername())
                .password(arangoDBDataSource.getPassword()
                );
    }

    @Override
    public String database() {
        return arangoDBDataSource.getDatabase();
    }
}