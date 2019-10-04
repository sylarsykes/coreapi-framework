package org.sylrsykssoft.coreapi.framework.web.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.sylrsykssoft.coreapi.framework.web.configuration.property.CoreApiFrameworkWebConfigurationLocaleProperties;

/**
 * Locale configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
@EnableConfigurationProperties(CoreApiFrameworkWebConfigurationLocaleProperties.class)
public class CoreApiFrameworkWebLocaleConfiguration implements WebMvcConfigurer {

	@Autowired
	private CoreApiFrameworkWebConfigurationLocaleProperties configLocaleProperties;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/**
	 * Bean locale change interceptor
	 * 
	 * @return LocaleChangeInterceptor
	 */
	@Bean("localeChangeInterceptor")
	@ConditionalOnProperty(prefix = "coreapi.framework.web.locale", name = "localeParamName", matchIfMissing = true)
	public LocaleChangeInterceptor localeChangeInterceptor() {
		final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName(configLocaleProperties.getLocaleParamName());

		return localeChangeInterceptor;
	}

	/**
	 * Bean locale resolver
	 * 
	 * @return LocaleResolver
	 */
	@Bean("localeResolver")
	@ConditionalOnProperty(prefix = "coreapi.framework.web.locale", name = { "defaultLanguage",
	"defaultCountry" }, matchIfMissing = true)
	public LocaleResolver localeResolver() {
		final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(
				new Locale(configLocaleProperties.getDefaultLanguage(), configLocaleProperties.getDefaultCountry()));

		return localeResolver;
	}

	/**
	 * Bean message source
	 * 
	 * @return MessageSource
	 */
	@Bean
	@ConditionalOnProperty(prefix = "coreapi.framework.web.locale", name = { "localeFolder",
	"localeFilename" }, matchIfMissing = true)
	@ConditionalOnBean(name = { "localeResolver", "localeChangeInterceptor" })
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename(
				"classpath:" + configLocaleProperties.getLocaleFolder() + configLocaleProperties.getLocaleFilename());
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
