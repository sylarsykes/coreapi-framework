package org.sylrsykssoft.coreapi.framework.database.exception;

import java.beans.ConstructorProperties;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Not found entity exception
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public final class NotFoundEntityException extends RuntimeException {
	private static final long serialVersionUID = 2363674934985036006L;

	/**
	 * Deafult constructor
	 */
	public NotFoundEntityException() {
		super();
	}

	/**
	 * Message constructor 
	 * 
	 * @param message
	 */
	@ConstructorProperties({ "message"})
	public NotFoundEntityException(final String message) {
		super(message);
	}
	
	/**
	 * AllArgsConstructor
	 * 
	 * @param message
	 * @param cause
	 */
	@ConstructorProperties({ "message", "cause"})
	public NotFoundEntityException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Cause constructor
	 * 
	 * @param cause
	 */
	@ConstructorProperties({ "cause"})
	public NotFoundEntityException(final Throwable cause) {
		super(cause);
	}

}
