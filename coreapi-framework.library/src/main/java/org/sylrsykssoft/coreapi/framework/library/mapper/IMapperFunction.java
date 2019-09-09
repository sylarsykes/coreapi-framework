package org.sylrsykssoft.coreapi.framework.library.mapper;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Create a instance of mapper
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface IMapperFunction<T, R extends ResourceSupport> {


	/**
	 * Get concrete resource to entity mapper
	 * 
	 * @return ModelMapperFunction<R, T>
	 */
	ModelMapperFunction<R, T> mapperToEntity();

	/**
	 * Get concrete entity to resource mapper
	 * 
	 * @return ModelMapperFunction<T, R>
	 */
	ResourceAssemblerSupport<T, R> mapperToResource();
}
