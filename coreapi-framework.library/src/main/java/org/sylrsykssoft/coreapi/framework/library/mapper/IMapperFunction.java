package org.sylrsykssoft.coreapi.framework.library.mapper;

/**
 * Create a instance of mapper
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface IMapperFunction<T, R> {

	/**
	 * Get concrete entity to resource mapper
	 * 
	 * @return ModelMapperFunction<T, R>
	 */
	ModelMapperFunction<T, R> mapperToResource();
	
	/**
	 * Get concrete resource to entity mapper
	 * 
	 * @return ModelMapperFunction<R, T>
	 */
	ModelMapperFunction<R, T> mapperToEntity();
}
