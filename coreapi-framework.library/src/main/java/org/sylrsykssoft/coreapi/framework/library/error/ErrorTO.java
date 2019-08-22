package org.sylrsykssoft.coreapi.framework.library.error;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Class ErrorTO.
 *
 * @author juan.gonzalez.fernandez.jgf
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
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

}
