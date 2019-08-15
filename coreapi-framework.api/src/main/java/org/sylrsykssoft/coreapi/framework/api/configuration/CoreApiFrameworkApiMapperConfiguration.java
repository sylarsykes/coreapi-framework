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
	 * Base admin resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<BaseAdminResource, BaseAdmin> baseAdminMapperToEntityFunction() {
		return new ModelMapperFunction<>(BaseAdminResource.class, BaseAdmin.class);
	}
	
	/**
	 * Base admin resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<BaseAdmin, BaseAdminResource> baseAdminMapperToResourceFunction() {
		return new ModelMapperFunction<>(BaseAdmin.class, BaseAdminResource.class);
	}
	
	/**
	 * Base entity resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<BaseEntityResource, BaseEntity> baseEntityMapperToEntityFunction() {
		return new ModelMapperFunction<>(BaseEntityResource.class, BaseEntity.class);
	}
	
	/**
	 * Base entity resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<BaseEntity, BaseEntityResource> baseEntityMapperToResourceFunction() {
		return new ModelMapperFunction<>(BaseEntity.class, BaseEntityResource.class);
	}
	
	/**
	 * Base resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	@SuppressWarnings("rawtypes")
	public ModelMapperFunction<BaseResource, Base> baseMapperToEntityFunction() {
		return new ModelMapperFunction<>(BaseResource.class, Base.class);
	}
	
	/**
	 * Base resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	@SuppressWarnings("rawtypes")
	public ModelMapperFunction<Base, BaseResource> baseMapperToResourceFunction() {
		return new ModelMapperFunction<>(Base.class, BaseResource.class);
	}

}
