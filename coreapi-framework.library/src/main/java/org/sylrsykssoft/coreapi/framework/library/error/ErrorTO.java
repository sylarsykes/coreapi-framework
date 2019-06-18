package org.sylrsykssoft.coreapi.framework.library.error;

import java.beans.ConstructorProperties;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class ErrorTO.
 *
 * @author juan.gonzalez.fernandez.jgf
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class ErrorTO implements Serializable {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4675257088995071827L;
	
	/** The status code. */
	private String statusCode;
	
	/** The message. */
	private String message;
	
	/** The exception. */
	private Throwable exception;

	
	/**
	 * Default builder.
	 * 
	 * @return ErrorTOBuilder
	 */
	public static ErrorTOBuilder builder() {
		return new ErrorTOBuilder();
	}

	/**
	 * BaseBuilder.
	 * 
	 * @param base {@link ErrorTO}
	 * @return ErrorTOBuilder
	 */
	public static ErrorTOBuilder builder(final ErrorTO error) {
		return new ErrorTOBuilder(error);
	}

	/**
	 * ErrorTOBuilder.
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 */
	@Component()
	public static class ErrorTOBuilder {

		/**
		 * Default constructor.
		 */
		public ErrorTOBuilder() {
			super();
		}

		/**
		 * AllArgsConstructor
		 * 
		 * @param error {@link Error} object.
		 */
		@ConstructorProperties({ "error"})
		public ErrorTOBuilder(final ErrorTO error) {
			this.statusCode = error.getStatusCode();
			this.message = error.getMessage();
			this.exception = error.getException();
		}

	}
}
