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
	@SuppressWarnings("rawtypes")
	public ModelMapperFunction<Base, BaseResource> baseMapperToResourceFunction() {
		return new ModelMapperFunction<Base, BaseResource>(Base.class, BaseResource.class);
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
		return new ModelMapperFunction<BaseResource, Base>(BaseResource.class, Base.class);
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
		return new ModelMapperFunction<BaseEntity, BaseEntityResource>(BaseEntity.class, BaseEntityResource.class);
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
		return new ModelMapperFunction<BaseEntityResource, BaseEntity>(BaseEntityResource.class, BaseEntity.class);
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
		return new ModelMapperFunction<BaseAdmin, BaseAdminResource>(BaseAdmin.class, BaseAdminResource.class);
	}
	
	/**
	 * Base admin resource model mapper function.
	 *
	 * @return the model mapper function
	 */
	@Bean
	@Scope(value = "prototype")
	@Lazy(value = true)
	public ModelMapperFunction<BaseAdminResource, BaseAdmin> baseAdminMapperToEntityFunction() {
		return new ModelMapperFunction<BaseAdminResource, BaseAdmin>(BaseAdminResource.class, BaseAdmin.class);
	}

}
