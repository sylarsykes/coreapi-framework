package org.sylrsykssoft.coreapi.framework.audit.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.sylrsykssoft.coreapi.framework.audit.configuration.properties.CoreaApiFrameworkAuditConfigurationProperties;

/**
 * Global configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
@Configuration
@ConditionalOnProperty(value = "basePath", prefix = "coreapi.framework.audit.rest", matchIfMissing = true)
@EnableConfigurationProperties(CoreaApiFrameworkAuditConfigurationProperties.class)
public class CoreaApiFrameworkAuditConfiguration {

}
