package org.sylrsykssoft.coreapi.framework.library.mapper;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * Mapper entity to resource or resource to entity.
 *
 * @author juan.gonzalez.fernandez.jgf
 * @param <T> the generic type
 * @param <R> the generic type
 */
@Slf4j
public class ModelMapperFunction<T, R> implements Function<T, R> {

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;
	
	private Class<T> sourceClass;
	private Class<R> targetClass;
	
	@SuppressWarnings("rawtypes")
	private List<PropertyMap> mappings;
	
	/**
	 * AllArgsContructor
	 * 
	 * @param sourceClass
	 * @param targetClass
	 */
	@ConstructorProperties({ "sourceClass", "targetClass" })
	public ModelMapperFunction(final Class<T> sourceClass, final Class<R> targetClass) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}
	
	/**
	 * AllArgsContructor
	 * 
	 * @param sourceClass
	 * @param targetClass
	 */
	@SuppressWarnings({ "rawtypes", "exports" })
	@ConstructorProperties({ "sourceClass", "targetClass", "mappings" })
	public ModelMapperFunction(final Class<T> sourceClass, final Class<R> targetClass, final List<PropertyMap> mappings) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
		this.mappings = mappings;
	}
	
	/**
	 * {@inherit}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public R apply(T source) {
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		if (CollectionUtils.isNotEmpty(this.mappings)) {
			for (PropertyMap propertyMap : mappings) {
				modelMapper.addMappings(propertyMap);
			}
		}
		
//		try {
//			// test that all fields are mapped
//			modelMapper.validate();
//		} catch (ValidationException e) {
//			LOGGER.warn("ModelMapperFunction::validate Validating a entry: {} and exception {}", source, e);
//		}
		
		return modelMapper.map(source, targetClass);
	}


}
