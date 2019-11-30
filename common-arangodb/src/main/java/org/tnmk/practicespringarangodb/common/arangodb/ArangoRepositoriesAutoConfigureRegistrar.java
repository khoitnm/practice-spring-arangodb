package org.tnmk.practicespringarangodb.common.arangodb;

import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.repository.ArangoRepositoryConfigurationExtension;
import java.lang.annotation.Annotation;
import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

/**
 * NOTE: Don't need to config this file in spring.factories.
 * Copied from https://github.com/arangodb/spring-boot-starter/blob/master/src/main/java/com/arangodb/springframework/boot/autoconfigure/ArangoRepositoriesAutoConfigureRegistrar.java
 * @author Mark Vollmary
 */
public class ArangoRepositoriesAutoConfigureRegistrar extends AbstractRepositoryConfigurationSourceSupport {

	@Override
	protected Class<? extends Annotation> getAnnotation() {
		return EnableArangoRepositories.class;
	}

	@Override
	protected Class<?> getConfiguration() {
		return EnableArangoRepositoriesConfiguration.class;
	}

	@Override
	protected RepositoryConfigurationExtension getRepositoryConfigurationExtension() {
		return new ArangoRepositoryConfigurationExtension();
	}

	@EnableArangoRepositories
	private static class EnableArangoRepositoriesConfiguration {
	}

}