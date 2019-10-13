package org.sylrsykssoft.coreapi.framework.web.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotIdMismatchEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.ErrorTO;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;

/**
 * Controller exceptions
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
@ControllerAdvice
public class CoreApiFrameworkWebErrorExceptionHandler {

	/**
	 * Global exception error handler
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<ErrorTO>
	 */
	@ExceptionHandler(CoreApiFrameworkLibraryException.class)
	public ResponseEntity<ErrorTO> globleExcpetionHandler(final CoreApiFrameworkLibraryException ex, final WebRequest request) {
		final ErrorTO errorDetails = ErrorTO.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.message(ex.getMessage()).exception(ex).timestamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Not found entity exception error handler
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<ErrorTO>
	 */
	@ExceptionHandler(NotFoundEntityException.class)
	public ResponseEntity<ErrorTO> notFoudEntityExcpetionHandler(final NotFoundEntityException ex,
			final WebRequest request) {
		final ErrorTO errorDetails = ErrorTO.builder().statusCode(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(ex.getMessage()).exception(ex).timestamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Not found entity exception error handler
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<ErrorTO>
	 */
	@ExceptionHandler(NotIdMismatchEntityException.class)
	public ResponseEntity<ErrorTO> notIdMismatchEntityExcpetionHandler(final NotIdMismatchEntityException ex,
			final WebRequest request) {
		final ErrorTO errorDetails = ErrorTO.builder().statusCode(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(ex.getMessage()).exception(ex).timestamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
