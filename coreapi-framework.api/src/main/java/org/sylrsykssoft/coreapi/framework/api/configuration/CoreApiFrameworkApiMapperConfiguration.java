package org.sylrsykssoft.coreapi.framework.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.sylrsykssoft.coreapi.framework.api.model.Base;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;

/**
 * The Class WebMapperConfiguration.
 *
 * @author juan.gonzalez.fernandez.jgf
 */
@Configuration
public class CoreApiFrameworkApiMapperConfiguration {

	/**
	 * Base resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<Base, BaseResource> baseResourceModelMapperFunction() {
		return new ModelMapperFunction<Base, BaseResource>();
	}
	
	/**
	 * Base entity resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<BaseEntity, BaseEntityResource> baseEntityResourceModelMapperFunction() {
		return new ModelMapperFunction<BaseEntity, BaseEntityResource>();
	}
	
	/**
	 * Base admin resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<BaseAdmin, BaseAdminResource> baseAdminResourceModelMapperFunction() {
		return new ModelMapperFunction<BaseAdmin, BaseAdminResource>();
	}

}
