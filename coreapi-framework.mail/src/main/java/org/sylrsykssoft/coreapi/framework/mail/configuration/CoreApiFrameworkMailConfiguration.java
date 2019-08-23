package org.sylrsykssoft.coreapi.framework.mail.configuration;

import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.FREEMAKER_TEMPLATE_BEAN_NAME;
import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.FREEMAKER_TEMPLATE_FOLDER_PATH;
import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.MAIL_ASYNC_EXECUTOR;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * Global configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
@EnableAsync
public class CoreApiFrameworkMailConfiguration {

	/**
	 * Async executor
	 * 
	 * @return Executor
	 */
	@Bean(name = MAIL_ASYNC_EXECUTOR)
	public Executor asyncExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("CoreApiFrameworkMailAsynchThread-");
		executor.initialize();
		return executor;
	}

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
