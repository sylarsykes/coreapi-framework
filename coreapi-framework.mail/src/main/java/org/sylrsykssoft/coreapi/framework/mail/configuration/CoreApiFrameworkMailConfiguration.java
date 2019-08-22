package org.sylrsykssoft.coreapi.framework.mail.configuration;

import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.FREEMAKER_TEMPLATE_BEAN_NAME;
import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.FREEMAKER_TEMPLATE_FOLDER_PATH;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * Global configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
public class CoreApiFrameworkMailConfiguration {

	/**
	 * Template bean
	 * 
	 * @param resourceLoader
	 * 
	 * @return FreeMarkerConfigurationFactoryBean
	 */
	@Bean(name = FREEMAKER_TEMPLATE_BEAN_NAME)
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(final ResourceLoader resourceLoader) {
		final FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath(FREEMAKER_TEMPLATE_FOLDER_PATH);
		return bean;
	}
}
