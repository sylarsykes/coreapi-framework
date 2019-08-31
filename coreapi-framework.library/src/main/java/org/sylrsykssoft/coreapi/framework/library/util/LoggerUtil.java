package org.sylrsykssoft.coreapi.framework.library.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;
import org.springframework.util.Assert;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * LOGGER util
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Slf4j
@UtilityClass
public class LoggerUtil {

	/**
	 * Log message levels
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 */
	public enum LogMessageLevel {
		DEBUG, ERROR, INFO, TRACE, WARN
	}

	/**
	 * Log a message with the specific {@link Marker} at the {@link LogMessageLevel}
	 * level.
	 *
	 * @param level  the log message level
	 * @param marker The marker specific to this log statement
	 * @param msg    the message string to be logged
	 * 
	 */
	public static void message(final LogMessageLevel level, final Marker marker, final String msg) {

		Assert.notNull(level, "Log level is required");
		Assert.notNull(marker, "Marker is required");
		Assert.notNull(msg, "Message is required");

		if (ObjectUtils.allNotNull(level, marker, msg) && StringUtils.isNotBlank(msg)) {
			switch (level) {
			case DEBUG:
				LOGGER.debug(marker, msg);
				break;
			case ERROR:
				LOGGER.error(marker, msg);
				break;
			case TRACE:
				LOGGER.trace(marker, msg);
				break;
			case WARN:
				LOGGER.warn(marker, msg);
				break;
			case INFO:
			default:
				LOGGER.info(marker, msg);
				break;
			}
		}


	}

	/**
	 * This method is similar to {@link #message(String, Object...)} method except
	 * that the marker data is also taken into consideration.
	 *
	 * @param marker    the marker data specific to this log statement
	 * @param format    the format string
	 * @param arguments a list of 3 or more arguments
	 */
	public static void message(final LogMessageLevel level, final Marker marker, final String msg,
			final Object... arguments) {

		Assert.notNull(level, "Log level is required");
		Assert.notNull(marker, "Marker is required");
		Assert.notNull(msg, "Message is required");

		if (ObjectUtils.allNotNull(level, marker, msg) && StringUtils.isNotBlank(msg)) {
			switch (level) {
			case DEBUG:
				LOGGER.debug(marker, msg, arguments);
				break;
			case ERROR:
				LOGGER.error(marker, msg, arguments);
				break;
			case TRACE:
				LOGGER.trace(marker, msg, arguments);
				break;
			case WARN:
				LOGGER.warn(marker, msg, arguments);
				break;
			case INFO:
			default:
				LOGGER.info(marker, msg, arguments);
				break;
			}
		}


	}

	/**
	 * This method is similar to {@link #info(String, Throwable)} method except that
	 * the marker data is also taken into consideration.
	 *
	 * @param marker the marker data for this log statement
	 * @param msg    the message accompanying the exception
	 * @param t      the exception (throwable) to log
	 * 
	 */
	public static void message(final LogMessageLevel level, final Marker marker, final String msg, final Throwable t) {

		Assert.notNull(level, "Log level is required");
		Assert.notNull(msg, "Message is required");
		Assert.notNull(t, "Throwable object is required");

		if (ObjectUtils.allNotNull(marker, msg, t) && StringUtils.isNotBlank(msg)) {
			switch (level) {
			case DEBUG:
				LOGGER.debug(marker, msg, t);
				break;
			case ERROR:
				LOGGER.error(marker, msg, t);
				break;
			case TRACE:
				LOGGER.trace(marker, msg, t);
				break;
			case WARN:
				LOGGER.warn(marker, msg, t);
				break;
			case INFO:
			default:
				LOGGER.info(marker, msg, t);
				break;
			}
		}
	}

	/**
	 * Log a message at the {@link LogMessageLevel} level.
	 *
	 * @param level the log message level
	 * @param msg   the message string to be logged
	 * 
	 */
	public static void message(final LogMessageLevel level, final String msg) {

		Assert.notNull(level, "Log level is required");
		Assert.notNull(msg, "Message is required");

		if (ObjectUtils.allNotNull(level, msg) && StringUtils.isNotBlank(msg)) {
			switch (level) {
			case DEBUG:
				LOGGER.debug(msg);
				break;
			case ERROR:
				LOGGER.error(msg);
				break;
			case TRACE:
				LOGGER.trace(msg);
				break;
			case WARN:
				LOGGER.warn(msg);
				break;
			case INFO:
			default:
				LOGGER.info(msg);
				break;
			}
		}

	}

	/**
	 * Log a message at the {@link LogMessageLevel} level according to the specified
	 * format and arguments.
	 * 
	 * @param level     the log message level
	 * @param msg       the message string to be logged
	 * @param arguments a list of 3 or more arguments
	 * 
	 */
	public static void message(final LogMessageLevel level, final String msg, final Object... arguments) {

		Assert.notNull(level, "Log level is required");
		Assert.notNull(msg, "Message is required");

		if (ObjectUtils.allNotNull(level, msg) && StringUtils.isNotBlank(msg)) {
			switch (level) {
			case DEBUG:
				LOGGER.debug(msg, arguments);
				break;
			case ERROR:
				LOGGER.error(msg, arguments);
				break;
			case TRACE:
				LOGGER.trace(msg, arguments);
				break;
			case WARN:
				LOGGER.warn(msg, arguments);
				break;
			case INFO:
			default:
				LOGGER.info(msg, arguments);
				break;
			}
		}
	}

	/**
	 * Log an exception (throwable) at the {@link LogMessageLevel} level with an
	 * accompanying message.
	 * 
	 * @param level the log message level
	 * @param msg   the message accompanying the exception
	 * @param t     the exception (throwable) to log
	 */
	public static void message(final LogMessageLevel level, final String msg, final Throwable t) {

		Assert.notNull(level, "Log level is required");
		Assert.notNull(msg, "Message is required");
		Assert.notNull(t, "Throwable object is required");

		if (ObjectUtils.allNotNull(level, msg, t) && StringUtils.isNotBlank(msg)) {
			switch (level) {
			case DEBUG:
				LOGGER.debug(msg, t);
				break;
			case ERROR:
				LOGGER.error(msg, t);
				break;
			case TRACE:
				LOGGER.trace(msg, t);
				break;
			case WARN:
				LOGGER.warn(msg, t);
				break;
			case INFO:
			default:
				LOGGER.info(msg, t);
				break;
			}
		}
	}
}
