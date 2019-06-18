package org.sylrsykssoft.coreapi.framework.library.mapper;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper entity to resource or resource to entity.
 *
 * @author juan.gonzalez.fernandez.jgf
 * @param <T> the generic type
 * @param <R> the generic type
 */
public class ModelMapperFunction<T, R> implements Function<T, R> {

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * {@inherit}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public R apply(T t) {
		Class<R> resourceClass = (Class<R>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
		return modelMapper.map(t, resourceClass);
	}


}
