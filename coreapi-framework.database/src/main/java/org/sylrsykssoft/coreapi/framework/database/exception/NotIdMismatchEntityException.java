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
public final class NotIdMismatchEntityException extends RuntimeException {

	private static final long serialVersionUID = 6753504484000665575L;

	/**
	 * Deafult constructor
	 */
	public NotIdMismatchEntityException() {
		super();
	}

	/**
	 * Message constructor 
	 * 
	 * @param message
	 */
	@ConstructorProperties({ "message"})
	public NotIdMismatchEntityException(final String message) {
		super(message);
	}

	/**
	 * AllArgsConstructor
	 * 
	 * @param message
	 * @param cause
	 */
	@ConstructorProperties({ "message", "cause"})
	public NotIdMismatchEntityException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Cause constructor
	 * 
	 * @param cause
	 */
	@ConstructorProperties({ "cause"})
	public NotIdMismatchEntityException(final Throwable cause) {
		super(cause);
	}
}
