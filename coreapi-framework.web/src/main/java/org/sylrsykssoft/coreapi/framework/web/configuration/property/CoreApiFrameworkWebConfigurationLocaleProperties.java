package org.sylrsykssoft.coreapi.framework.web.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Configuration properties for audit module.
 *
 * @author juan.gonzalez.fernandez.jgf
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ConfigurationProperties(prefix = "coreapi.framework.web.locale")
public class CoreApiFrameworkWebConfigurationLocaleProperties {

	String defaultLanguage;
	String defaultCountry;
	String localeParamName;
	String localeFolder;
	String localeFilename;

}
