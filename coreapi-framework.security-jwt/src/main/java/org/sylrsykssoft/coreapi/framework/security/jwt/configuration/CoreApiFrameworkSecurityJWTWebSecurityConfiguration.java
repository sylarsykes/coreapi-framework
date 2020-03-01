package org.sylrsykssoft.coreapi.framework.security.jwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.sylrsykssoft.coreapi.framework.security.jwt.filter.CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter;

/**
 * JWT Web security configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
public class CoreApiFrameworkSecurityJWTWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll().anyRequest().authenticated()
		//		.and()
		//		.formLogin()
		//		.successHandler(loginSuccessHandler)
		//		.permitAll()
		.and().addFilter(new CoreApiFrameworkSecurityJWTUsernamePasswordAuthenticationFilter(authenticationManagerBean()))
				.csrf()
		.disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
