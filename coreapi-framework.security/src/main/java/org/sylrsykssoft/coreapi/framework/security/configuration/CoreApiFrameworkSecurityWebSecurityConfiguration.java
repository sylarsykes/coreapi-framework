package org.sylrsykssoft.coreapi.framework.security.configuration;

import static org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.ROLES_DEFAULT_NAME;
import static org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.ROLE_ADMIN_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.sylrsykssoft.coreapi.framework.security.handler.LoginSuccessHandler;

/**
 * Web security configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
public class CoreApiFrameworkSecurityWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${coreapi.framework.security.globalusers.user.name}")
	private String defaultUsername;

	@Value("${coreapi.framework.security.globalusers.user.password}")
	private String defaultUserPassword;

	@Value("${coreapi.framework.security.globalusers.admin.name}")
	private String defaultAdminUsername;

	@Value("${coreapi.framework.security.globalusers.admin.password}")
	private String defaultAdminUserPassword;

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

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
		.antMatchers("/tokens/**").permitAll().anyRequest().authenticated().and().formLogin()
		.successHandler(loginSuccessHandler).permitAll().and()
		.csrf().disable();
	}

	/**
	 * Global users
	 * 
	 * @param auth
	 * 
	 * @throws Exception
	 */
	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(defaultUsername).password(passwordEncoder.encode(defaultUserPassword))
		.roles(ROLES_DEFAULT_NAME).and().withUser(defaultAdminUsername)
		.password(passwordEncoder.encode(defaultAdminUserPassword)).roles(ROLE_ADMIN_NAME, ROLES_DEFAULT_NAME);

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

}
