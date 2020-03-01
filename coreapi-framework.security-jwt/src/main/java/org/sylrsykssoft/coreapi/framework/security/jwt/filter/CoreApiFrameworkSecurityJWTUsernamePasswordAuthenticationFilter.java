package org.sylrsykssoft.coreapi.framework.security.jwt.filter;

import static org.sylrsykssoft.coreapi.framework.security.jwt.configuration.CoreApiFrameworkSecurityJWTConstants.LOGIN_ROUTE;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
import org.sylrsykssoft.coreapi.framework.library.util.JsonStringUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;
import org.sylrsykssoft.coreapi.framework.security.resource.BaseUserResource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

/**
 * JWT Web security configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public class CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	/**
	 * @param authenticationManager
	 */
	public CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter(final AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_ROUTE, HttpMethod.POST.name()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
			throws AuthenticationException {

		// Obtain data from (form_data)
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		if (username != null && password != null) {
			LoggerUtil.message(LogMessageLevel.INFO,
					"CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter::attemptAuthentication User authenticated with username {} and password {}",
					username, password);
			// If the data is sent in MediaType.APPLICATION_JSON_VALUE
		} else {
			User user = null;

			try {
				user = JsonStringUtil.asObjectJsonInputStream(request.getInputStream(), User.class);

				username = user.getUsername();
				password = user.getPassword();

				LoggerUtil.message(LogMessageLevel.INFO,
						"CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter::attemptAuthentication User authenticated with username {} and password {}",
						username, password);
			} catch (final CoreApiFrameworkLibraryException e) {
				LoggerUtil.message(LogMessageLevel.WARN,
						"CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter::attemptAuthentication User format is not valid");
			} catch (final IOException e) {
				LoggerUtil.message(LogMessageLevel.WARN,
						"CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter::attemptAuthentication User format inputStream is not valid");
			}
		}

		username = username.trim();

		final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
				password);

		return authenticationManager.authenticate(authRequest);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain, final Authentication authResult) throws IOException, ServletException {

		// Creates a spec-compliant secure-random key:
		final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		final String base64Key = Encoders.BASE64.encode(key.getEncoded());

		// Set duration
		final Date createdDate = new Date();
		// Expired in 2 hour
		final Date expirationDate = new Date(System.currentTimeMillis() + 36000000 * 2);

		final User springSecurityUser = (User) authResult.getPrincipal();
		final BaseUserResource<Number> user = BaseUserResource.baseUserResourceBuilder()
				.username(springSecurityUser.getUsername()).accountNonExpired(springSecurityUser.isAccountNonExpired())
				.accountNonLocked(springSecurityUser.isAccountNonLocked())
				.credentialsNonExpired(springSecurityUser.isCredentialsNonExpired())
				.enabled(springSecurityUser.isEnabled()).build();

		if (!user.isEnabled()) {
			LoggerUtil.message(LogMessageLevel.WARN, "CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter::successfulAuthentication User with username {} is not enabled", user.getUsername());
			throw new ServletException(String.format(
					"CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter::successfulAuthentication User with username %s is not enabled",
					user.getUsername()));
		}

		// Claims (extra information for token)
		final Claims claims = Jwts.claims();
		claims.put("authorities", JsonStringUtil.asJsonString(springSecurityUser.getAuthorities()));

		@SuppressWarnings("deprecation")
		final String token = Jwts.builder()
		// Username user
		.setSubject(user.getUsername())
		// Add claims
		.setClaims(claims)
		// Set duration token
		.setIssuedAt(createdDate).setExpiration(expirationDate)
		// Sign token
		.signWith(SignatureAlgorithm.HS512, base64Key)
		.compact();

		// Save token
		response.addHeader("Authorization", "Barer " + token);

		final Map<String, Object> body = new HashMap<>();
		body.put("token", token);
		body.put("user", user);
		body.put("user authorities", springSecurityUser.getAuthorities());
		body.put("message",
				String.format("The user with username %s has been seuccesfully logged.", user.getUsername()));

		response.getWriter().write(JsonStringUtil.asJsonString(body));
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		//		super.successfulAuthentication(request, response, chain, authResult);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void unsuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException failed) throws IOException, ServletException {

		final Map<String, Object> body = new HashMap<>();
		body.put("message", "authentication error username or password incorrect.");
		body.put("error", failed);

		response.getWriter().write(JsonStringUtil.asJsonString(body));
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

//		super.unsuccessfulAuthentication(request, response, failed);
	}

}
