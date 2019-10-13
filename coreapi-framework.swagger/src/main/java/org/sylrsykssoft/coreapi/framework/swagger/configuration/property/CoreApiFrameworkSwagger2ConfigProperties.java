package org.sylrsykssoft.coreapi.framework.swagger.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Swagger2 configuration properties
 * 
 * Add @EnableConfigurationProperties(CoreApiFrameworkSwagger2ConfigProperties.class)
 * in @Configuration class
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
@ConfigurationProperties(prefix = "coreapi.framework.swagger")
public class CoreApiFrameworkSwagger2ConfigProperties {

	String apiVersion;
	String enabled = "false";
	String title;
	String description;
	String contactName;
	String contactUri;
	String contactEmail;
	String license;
	String licenseUri;
	String useDefaultResponseMessages;
	String enableUrlTemplating;
	String deepLinking;
	String defaultModelsExpandDepth;
	String defaultModelExpandDepth;
	String displayOperationId;
	String displayRequestDuration;
	String filter;
	String maxDisplayedTags;
	String showExtensions;

}
