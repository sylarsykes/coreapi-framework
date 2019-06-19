package org.sylrsykssoft.coreapi.framework.database.exception;

import java.beans.ConstructorProperties;

/**
 * Not id mismatch entity exception
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
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
	 * Cause constructor
	 * 
	 * @param cause
	 */
	@ConstructorProperties({ "cause"})
	public NotIdMismatchEntityException(final Throwable cause) {
		super(cause);
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
}
