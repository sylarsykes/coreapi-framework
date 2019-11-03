package org.sylrsykssoft.coreapi.framework.security.oauth.configuration;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.TOKEN_KEY_IS_AUTHENTICATED;
import static org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.TOKEN_KEY_PERMIT_ALL;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * OAuth2 Configuration
 * 
 * https://github.com/Baeldung/spring-security-oauth/blob/master/oauth-authorization-server/src/main/java/com/baeldung/config/OAuth2AuthorizationServerConfig.java
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
public class CoreaApiFrameworkSecurityOAuth2Configuration extends AuthorizationServerConfigurerAdapter {

	/**
	 * Custom token enhancer
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 */
	public class CustomTokenEnhancer implements TokenEnhancer {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken,
				final OAuth2Authentication authentication) {
			final Map<String, Object> additionalInfo = new HashMap<>();
			additionalInfo.put("organization", authentication.getName() + randomAlphabetic(4));
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;
		}
	}

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
		endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
		.authenticationManager(authenticationManager);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess(TOKEN_KEY_PERMIT_ALL).checkTokenAccess(TOKEN_KEY_IS_AUTHENTICATED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	/**
	 * Password encoder
	 * 
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Token enhancer
	 * 
	 * @return TokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	/**
	 * Default token services
	 * 
	 * @return DefaultTokenServices
	 */
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	/**
	 * Token store
	 * 
	 * @return TokenStore
	 */
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

}
