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

	// TOKEN KEY ACCESS //
	public static final String TOKEN_KEY_PERMIT_ALL = "permitAll()";
	public static final String TOKEN_KEY_IS_AUTHENTICATED = "isAuthenticated()";

	// HASROLE ACCESS
	public static final String HASROLE_ROLE_DEFAULT = "hasRole('" + ROLES_DEFAULT_NAME + "')";
	public static final String HASROLE_ROLE_ADMIN = "hasRole('" + ROLE_ADMIN_NAME + "')";
	public static final String HASROLE_ROLE_DEFAULT_OR_ADMIN = "hasAnyRole('" + ROLES_DEFAULT_NAME + "','"
			+ ROLE_ADMIN_NAME + "')";

	// HANDLER SUCCES KEY //
	public static final String HANDLER_SUCCES_KEY = "coreapiframeworksecurity.success";

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
