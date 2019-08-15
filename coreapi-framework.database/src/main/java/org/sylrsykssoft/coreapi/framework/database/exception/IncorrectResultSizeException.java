package org.sylrsykssoft.coreapi.framework.database.exception;

import java.beans.ConstructorProperties;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Not id mismatch entity exception
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public final class IncorrectResultSizeException extends RuntimeException {

	private static final long serialVersionUID = -4561187852569440223L;

	/**
	 * Deafult constructor
	 */
	public IncorrectResultSizeException() {
		super();
	}

	/**
	 * Message constructor 
	 * 
	 * @param message
	 */
	@ConstructorProperties({ "message"})
	public IncorrectResultSizeException(final String message) {
		super(message);
	}

	/**
	 * AllArgsConstructor
	 * 
	 * @param message
	 * @param cause
	 */
	@ConstructorProperties({ "message", "cause"})
	public IncorrectResultSizeException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Cause constructor
	 * 
	 * @param cause
	 */
	@ConstructorProperties({ "cause"})
	public IncorrectResultSizeException(final Throwable cause) {
		super(cause);
	}
}
