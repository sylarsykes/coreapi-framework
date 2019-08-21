package org.sylrsykssoft.coreapi.framework.mail.exception;

import java.beans.ConstructorProperties;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MailException
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CoreApiFrameworkMailException extends MailException {

	private static final long serialVersionUID = -3022561879408363143L;

	/**
	 * Message constructor
	 * 
	 * @param message
	 */
	@ConstructorProperties({ "message"})
	public CoreApiFrameworkMailException(final String message) {
		super(message);
	}

	/**
	 * AllArgsConstructor
	 * 
	 * @param message
	 * @param cause
	 */
	@ConstructorProperties({ "message", "cause"})
	public CoreApiFrameworkMailException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Cause constructor
	 * 
	 * @param cause
	 */
	@ConstructorProperties({ "cause" })
	public CoreApiFrameworkMailException(final Throwable cause) {
		this(cause.getMessage(), cause);
	}

}
