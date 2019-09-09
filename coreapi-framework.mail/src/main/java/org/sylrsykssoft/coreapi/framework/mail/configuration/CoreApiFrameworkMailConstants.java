package org.sylrsykssoft.coreapi.framework.mail.configuration;

/**
 * BaseAdminConstants
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class CoreApiFrameworkMailConstants {

	// TEMPLATE //
	public static final String FREEMAKER_TEMPLATE_BEAN_NAME = "coreApiFrameworkMailTemplateBean";
	public static final String FREEMAKER_TEMPLATE_FOLDER_PATH = "classpath:/mail/templates/";
	public static final String FREEMAKER_TEMPLATE_DEFAULT_TEMPLATE = "email.ftl";

	// EXECUTOR
	public static final String MAIL_ASYNC_EXECUTOR = "coreApiFrameworkMailAsyncExecutor";

	/**
	 * The caller references the constants using
	 * <tt>FunctionMemberConstants.EMPTY_STRING</tt>, and so on. Thus, the caller
	 * should be prevented from constructing objects of this class, by declaring
	 * this private constructor.
	 */
	private CoreApiFrameworkMailConstants() {
		throw new AssertionError();
	}

}
