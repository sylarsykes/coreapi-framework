package org.sylrsykssoft.coreapi.framework.library.mapper;

import java.beans.ConstructorProperties;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

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

	private final Class<T> sourceClass;
	private final Class<R> targetClass;

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
	 * {@inherit}
	 */
	@Override
	public R apply(final T source) {
		LoggerUtil.message(LogMessageLevel.INFO, "ModelMapperFunction::apply Mapper a source: {}", source);

		final R target = modelMapper.map(source, targetClass);

		LoggerUtil.message(LogMessageLevel.INFO, "ModelMapperFunction::apply Result -> {}", target);

		return target;
	}


}
