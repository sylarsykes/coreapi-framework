package org.sylrsykssoft.coreapi.framework.security.jwt.configuration;

/**
 * CoreApiFrameworkSecurityConstants
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public final class CoreApiFrameworkSecurityJWTConstants {

	public enum AuthorityType {
		ROLE_ADMIN,
		ROLE_MANAGER,
		ROLE_USER
	}

	// ROLES //
	public static final String ROLES_DEFAULT_NAME = "USER";

	public static final String ROLE_ADMIN_NAME = "ADMIN";
	// TOKEN KEY ACCESS //
	public static final String TOKEN_KEY_PERMIT_ALL = "permitAll()";

	public static final String TOKEN_KEY_IS_AUTHENTICATED = "isAuthenticated()";
	// HASROLE ACCESS
	public static final String HASROLE_ROLE_DEFAULT = "hasRole('" + ROLES_DEFAULT_NAME + "')";
	public static final String HASROLE_ROLE_ADMIN = "hasRole('" + ROLE_ADMIN_NAME + "')";

	public static final String HASROLE_ROLE_DEFAULT_OR_ADMIN = "hasAnyRole('" + ROLES_DEFAULT_NAME + "','"
			+ ROLE_ADMIN_NAME + "')";

	// ROUTES //
	public static final String LOGIN_ROUTE = "/api/login";

	// SECRET KEY //
	public static final String SIMPLE_SECRET_KEY = "coreapiframework.security.jswt.simple-secret-key";

	// PRIVATE //
	/**
	 * The caller references the constants using
	 * <tt>FunctionMemberConstants.EMPTY_STRING</tt>, and so on. Thus, the caller
	 * should be prevented from constructing objects of this class, by declaring
	 * this private constructor.
	 */
	private CoreApiFrameworkSecurityJWTConstants() {
		throw new AssertionError();
	}

}
