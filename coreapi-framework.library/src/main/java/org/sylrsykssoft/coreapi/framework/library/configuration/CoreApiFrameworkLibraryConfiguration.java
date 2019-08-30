package org.sylrsykssoft.coreapi.framework.library.configuration;

import org.modelmapper.ModelMapper;
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
	 * Model mapper bean
	 * @return
	 */
	@Bean
	@Scope("prototype")
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
