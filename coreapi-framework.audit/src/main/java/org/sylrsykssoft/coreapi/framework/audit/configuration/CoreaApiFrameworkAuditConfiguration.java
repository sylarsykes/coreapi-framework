package org.sylrsykssoft.coreapi.framework.audit.configuration;

import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.AUDITORAWARE_COMPONENT_NAME;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.sylrsykssoft.coreapi.framework.audit.configuration.properties.CoreaApiFrameworkAuditConfigurationProperties;

/**
 * Global configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
@Configuration
@EntityScan("org.sylrsykssoft.coreapi.framework.audit.domain")
@EnableJpaRepositories(basePackages = {
"org.sylrsykssoft.coreapi.framework.audit.repository" })
@EnableJpaAuditing(auditorAwareRef = AUDITORAWARE_COMPONENT_NAME)
@ConditionalOnProperty(value = "basePath", prefix = "coreapi.framework.audit.rest", matchIfMissing = true)
@EnableConfigurationProperties(CoreaApiFrameworkAuditConfigurationProperties.class)
public class CoreaApiFrameworkAuditConfiguration {

	/**
	 * AuditAware bean
	 * 
	 * @return AuditorAware
	 */
	@Bean(AUDITORAWARE_COMPONENT_NAME)
	@Scope(value = "prototype")
	@Lazy(value = true)
	public AuditorAware<String> defaultAuditorAwareImpl() {
		return new CoreApiFrameworkAuditAuditorAwareImpl();
	}
}
