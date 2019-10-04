package org.sylrsykssoft.coreapi.framework.audit.configuration;

/**
 * BaseAdminConstants
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class BaseAdminAuditConstants {

	// REPOSITORY
	public static final int MAX_LENGTH_NAME = 256;

	// AUDITORAWARE
	public static final String AUDITORAWARE_COMPONENT_NAME = "defaultAuditorAwareImpl";
	public static final String AUDITORAWARE_DEFAULT_CURRENT_EDITOR_NAME = "musbands.admin";

	// CONTROLLER BASE PATH //
	public static final String CONTROLLER_AUDIT_REQUEST_MAPPING_BASE_PATH = "${coreapi.framework.audit.rest.base-path}";

	// CONTROLLER PATH //
	public static final String CONTROLLER_GET_FINDREVISION_BY_ID_REVISIONNUMBER = "/findRevision/id/{id}/revisionNumber/{revisionNumber}";
	public static final String CONTROLLER_GET_FIND_LAST_CHANGE_REVISION_BY_ID = "/findLastChangeRevision/id/{id}";
	public static final String CONTROLLER_GET_FIND_ALL_REVISIONS = "/findAllRevisions";
	public static final String CONTROLLER_POST_FIND_ALL_BY_PAGEABLE = "/findAllRevisions/pageable";

	/**
	 * The caller references the constants using
	 * <tt>FunctionMemberConstants.EMPTY_STRING</tt>, and so on. Thus, the caller
	 * should be prevented from constructing objects of this class, by declaring
	 * this private constructor.
	 */
	private BaseAdminAuditConstants() {
		throw new AssertionError();
	}

}
