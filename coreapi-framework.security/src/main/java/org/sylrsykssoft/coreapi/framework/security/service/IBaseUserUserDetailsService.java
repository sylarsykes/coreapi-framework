package org.sylrsykssoft.coreapi.framework.security.service;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.sylrsykssoft.coreapi.framework.security.domain.BaseUser;
import org.sylrsykssoft.coreapi.framework.security.repository.BaseUserRepository;
import org.sylrsykssoft.coreapi.framework.security.resource.BaseUserResource;

/**
 * IBaseUserUserDetailsService
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> BaseUser class
 * @param <R> BaseUserResource class
 * @param <N> Id type class
 */
public interface IBaseUserUserDetailsService<T extends BaseUser<N>, R extends BaseUserResource<N>, N extends Number>
extends UserDetailsService {

	/**
	 * Get all users
	 * 
	 * @return Iterable<R>
	 */
	Collection<R> findAll();

	/**
	 * Get user repository
	 * 
	 * @return BaseUserRepository<T, N>
	 */
	BaseUserRepository<T, N> getUserRepository();

	/**
	 * Check if user is enabled.
	 * 
	 * @param username Username
	 * @return boolean true is enabled false no
	 * @throws UsernameNotFoundException
	 */
	default boolean isEnabled(final String username) throws UsernameNotFoundException {
		final UserDetails user = this.loadUserByUsername(username);

		return user.isEnabled();
	}

	/**
	 * Find all user by name
	 * @param name The name of user
	 * @return Collection<T>
	 */
	Collection<R> loadByName(String name) throws UsernameNotFoundException;

	/**
	 * Locates the user based on the username. In the actual implementation, the
	 * search may possibly be case sensitive, or case insensitive depending on how
	 * the implementation instance is configured. In this case, the
	 * <code>UserDetails</code> object that comes back may have a username that is
	 * of a different case than what was actually requested..
	 *
	 * @param username the username identifying the user whose data is required.
	 *
	 * @return a fully populated user record (never <code>null</code>)
	 *
	 * @throws UsernameNotFoundException if the user could not be found or the user
	 *                                   has no GrantedAuthority
	 */
	UserDetails loadUserByUsername(String username, boolean enabled) throws UsernameNotFoundException;

}
