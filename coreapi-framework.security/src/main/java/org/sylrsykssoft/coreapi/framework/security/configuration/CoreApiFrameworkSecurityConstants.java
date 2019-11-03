package org.sylrsykssoft.coreapi.framework.security.configuration;

/**
 * CoreApiFrameworkSecurityConstants
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public final class CoreApiFrameworkSecurityConstants {

	// ROLES //
	public static final String ROLES_DEFAULT_NAME = "USER";
	public static final String ROLE_ADMIN_NAME = "ADMIN";

	// TOKEM KEY ACCESS //
	public static final String TOKEN_KEY_PERMIT_ALL = "permitAll()";
	public static final String TOKEN_KEY_IS_AUTHENTICATED = "isAuthenticated()";

	// PRIVATE //
	/**
	 * The caller references the constants using
	 * <tt>FunctionMemberConstants.EMPTY_STRING</tt>, and so on. Thus, the caller
	 * should be prevented from constructing objects of this class, by declaring
	 * this private constructor.
	 */
	private CoreApiFrameworkSecurityConstants() {
		throw new AssertionError();
	}

}
