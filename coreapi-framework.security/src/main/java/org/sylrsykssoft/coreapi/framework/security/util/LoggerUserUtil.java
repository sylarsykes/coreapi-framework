package org.sylrsykssoft.coreapi.framework.security.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * LOGGER user util
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class LoggerUserUtil {

	/**
	 * Log user autenticated
	 * 
	 * @param level
	 * @param authentication
	 * @param msg
	 */
	public static void log(final LogMessageLevel level, final Authentication authentication,
			final String methodName) {
		if (authentication != null) {
			final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			final List<String> roles = authorities.stream().map(authority -> authority.getAuthority())
					.collect(Collectors.toList());

			LoggerUtil.message(level, "The authenticated user {} is executing the function {}",
					authentication.getName(), roles.stream().map(x -> x).collect(Collectors.joining(", ")));

			LoggerUtil.message(level, "The authenticated user {} is executing the function {}",
					authentication.getName(),
					methodName);
		}
	}
}
