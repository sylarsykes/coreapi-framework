package org.sylrsykssoft.coreapi.framework.security.oauth.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * DataSource configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
public class CoreApiFrameworkSecurityOAuth2DataSourceConfiguration {

	@Value("classpath:oauth2_schema.sql")
	private Resource oauth2SchemaScript;

	/**
	 * Data source initializer
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(prefix = "spring.jpa.hibernate", name = {
	"ddl-auto" }, matchIfMissing = true, havingValue = "create")
	@Autowired
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		// Load oauth2 schema and default users
		final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(oauth2SchemaScript);

		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator);
		return initializer;
	}
}
