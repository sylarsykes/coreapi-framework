package org.sylrsykssoft.coreapi.framework.library.util;

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
	 * @param obj
	 * @return
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
}
