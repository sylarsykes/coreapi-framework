package org.sylrsykssoft.coreapi.framework.security.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Authentication facade
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	/**
	 * Extract roles of authentication object
	 * 
	 * @param authentication
	 * @return
	 */
	protected List<String> extractRoles(final Authentication authentication) {
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		final List<String> roles = authorities.stream().map(authority -> authority.getAuthority())
				.collect(Collectors.toList());
		return roles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getRoles() {
		final SecurityContext securityContext = SecurityContextHolder.getContext();

		if (securityContext != null) {
			final Authentication authentication = this.getAuthentication();

			if (authentication != null) {
				final List<String> roles = extractRoles(authentication);

				return sortRoles(roles);
			}
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRole(final String role) {
		final List<String> roles = this.getRoles();

		if (roles != null && roles.contains(role))
			return true;

		return false;
	}

	/**
	 * Sort roles list
	 * 
	 * @param roles Roles list
	 * 
	 * @return List<String>
	 * 
	 */
	protected List<String> sortRoles(final List<String> roles) {
		return roles.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
	}

}
