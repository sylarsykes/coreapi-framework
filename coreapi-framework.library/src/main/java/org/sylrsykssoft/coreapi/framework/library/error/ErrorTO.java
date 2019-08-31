package org.sylrsykssoft.coreapi.framework.library.error;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * The Class ErrorTO.
 *
 * @author juan.gonzalez.fernandez.jgf
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString
public class ErrorTO implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4675257088995071827L;

	/** The status code. */
	@NonNull
	String statusCode;

	/** The message. */
	@NonNull
	String message;

	/** The exception. */
	Throwable exception;

}
