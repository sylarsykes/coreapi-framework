package org.sylrsykssoft.coreapi.framework.web.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Webconfiguration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
public class CoreApiFrameworkWebConfiguration {

	/**
	 * RestTemplate configuration
	 * 
	 * @param builder Builder
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate(final RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}
}
