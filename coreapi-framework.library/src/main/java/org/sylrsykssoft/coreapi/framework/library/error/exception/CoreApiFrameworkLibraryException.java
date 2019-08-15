package org.sylrsykssoft.coreapi.framework.library.error.exception;

import java.beans.ConstructorProperties;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Common application exception
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CoreApiFrameworkLibraryException extends Exception {
	private static final long serialVersionUID = 2363674934985036006L;

	/**
	 * Deafult constructor
	 */
	public CoreApiFrameworkLibraryException() {
		super();
	}

	/**
	 * Message constructor 
	 * 
	 * @param message
	 */
	@ConstructorProperties({ "message"})
	public CoreApiFrameworkLibraryException(final String message) {
		super(message);
	}
	
	/**
	 * AllArgsConstructor
	 * 
	 * @param message
	 * @param cause
	 */
	@ConstructorProperties({ "message", "cause"})
	public CoreApiFrameworkLibraryException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Cause constructor
	 * 
	 * @param cause
	 */
	@ConstructorProperties({ "cause"})
	public CoreApiFrameworkLibraryException(final Throwable cause) {
		super(cause);
	}

}
