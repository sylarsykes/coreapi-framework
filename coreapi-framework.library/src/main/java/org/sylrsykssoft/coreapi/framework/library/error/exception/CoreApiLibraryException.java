package org.sylrsykssoft.coreapi.framework.library.error.exception;

import java.beans.ConstructorProperties;

/**
 * Common application exception
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public final class CoreApiLibraryException extends Exception {
	private static final long serialVersionUID = 2363674934985036006L;

	/**
	 * Deafult constructor
	 */
	public CoreApiLibraryException() {
		super();
	}

	/**
	 * Message constructor 
	 * 
	 * @param message
	 */
	@ConstructorProperties({ "message"})
	public CoreApiLibraryException(final String message) {
		super(message);
	}
	
	/**
	 * Cause constructor
	 * 
	 * @param cause
	 */
	@ConstructorProperties({ "cause"})
	public CoreApiLibraryException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * AllArgsConstructor
	 * 
	 * @param message
	 * @param cause
	 */
	@ConstructorProperties({ "message", "cause"})
	public CoreApiLibraryException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
