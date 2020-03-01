package org.sylrsykssoft.coreapi.framework.library.util;

import java.io.InputStream;

import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

/**
 * JsonStringUtil.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
@UtilityClass
public class JsonStringUtil {

	/**
	 * Json to string
	 * 
	 * @param obj Object to transform in json
	 * @return String
	 * @throws CoreApiFrameworkLibraryException
	 */
	public static String asJsonString(final Object obj) throws CoreApiFrameworkLibraryException {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (final Exception e) {
			throw new CoreApiFrameworkLibraryException(e);
		}
	}

	/**
	 * Json to object
	 * 
	 * @param <T>       Object type
	 * @param json      Json to transform in object
	 * @param valueType Class type for object
	 * @return Object
	 * @throws CoreApiFrameworkLibraryException
	 */
	public static <T extends Object> T asObjectJsonInputStream(final InputStream json, final Class<T> valueType)
			throws CoreApiFrameworkLibraryException {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final T jsonContent = mapper.readValue(json, valueType);
			return jsonContent;
		} catch (final Exception e) {
			throw new CoreApiFrameworkLibraryException(e);
		}
	}
}
