package org.sylrsykssoft.coreapi.framework.audit.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Configuration properties for Spring Data REST.
 *
 * @author juan.gonzalez.fernandez.jgf
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ConfigurationProperties(prefix = "coreapi.framework.audit.rest")
public class CoreaApiFrameworkAuditConfigurationProperties {

	String basePath;

}
