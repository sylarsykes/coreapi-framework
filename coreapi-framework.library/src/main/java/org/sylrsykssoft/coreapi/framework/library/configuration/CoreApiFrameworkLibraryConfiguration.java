package org.sylrsykssoft.coreapi.framework.library.configuration;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * The Class CoreApiLibraryConfiguration.
 *
 * @author juan.gonzalez.fernandez.jgf
 */
@Configuration
public class CoreApiFrameworkLibraryConfiguration {

	/**
	 * Logger bean
	 * 
	 * @param injectionPoint
	 * @return
	 */
	@Bean
	@Scope("prototype")
	public Logger logger(final InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
	}
	
	/**
	 * Model mapper bean
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
