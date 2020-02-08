package org.sylrsykssoft.coreapi.framework.security.util;

import java.util.List;

import org.springframework.security.core.Authentication;

/**
 * Authentication facade
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface IAuthenticationFacade {

	/**
	 * Get user authenticated
	 * 
	 * @return Authentication
	 */
	Authentication getAuthentication();

	/**
	 * Get roles for authenticated user
	 * 
	 * @return
	 */
	List<String> getRoles();

	/**
	 * Check if user has role
	 * 
	 * @param role
	 * 
	 * @return boolean
	 */
	boolean hasRole(String role);
}
