package org.sylrsykssoft.coreapi.framework.audit.configuration;

import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.AUDITORAWARE_COMPONENT_NAME;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Juan
 *
 */
@Configuration
@EntityScan("org.sylrsykssoft.coreapi.framework.audit.domain")
@EnableJpaRepositories(basePackages = {
"org.sylrsykssoft.coreapi.framework.audit.repository" })
public class CoreaApiFrameworkAuditConfiguration {

	@Bean(AUDITORAWARE_COMPONENT_NAME)
	@Scope(value = "prototype")
	@Lazy(value = true)
	public AuditorAware<String> defaultAuditorAwareImpl() {
		return new CoreApiFrameworkAuditAuditorAwareImpl();
	}
}
