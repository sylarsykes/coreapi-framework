package org.sylrsykssoft.coreapi.framework.web.configuration;

/**
 * BaseAdminConstants
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class BaseEntityConstants {

	// REPOSITORY
	public static final int MAX_LENGTH_NAME = 255;

	// CONTROLLER BASE PATH //
	public static final String CONTROLLER_REQUEST_MAPPING_BASE_PATH = "${spring.data.rest.base-path}";

	// CONTROLLER PATH //
	public static final String CONTROLLER_GET_FIND_BY_ID = "/findOne/id";
	public static final String CONTROLLER_GET_FIND_ONE_BY_ID = CONTROLLER_GET_FIND_BY_ID + "/{id}";
	public static final String CONTROLLER_GET_FIND_BY_NAME_MAPPING = "/findOne/name";
	public static final String CONTROLLER_GET_FIND_ONE_BY_NAME_MAPPING = CONTROLLER_GET_FIND_BY_NAME_MAPPING + "/{name}";
	public static final String CONTROLLER_GET_FIND_BY_EXAMPLE = "/findOne/example";
	public static final String CONTROLLER_POST_FIND_ALL_BY_EXAMPLE = "/findAll/example";
	public static final String CONTROLLER_POST_FIND_ALL_BY_EXAMPLE_SORTABLE = "/findAll/example/sort";
	public static final String CONTROLLER_PUT_UPDATE = "/{id}";
	public static final String CONTROLLER_DELETE_DELETE = "/{id}";

	/**
	 * The caller references the constants using
	 * <tt>FunctionMemberConstants.EMPTY_STRING</tt>, and so on. Thus, the caller
	 * should be prevented from constructing objects of this class, by declaring
	 * this private constructor.
	 */
	private BaseEntityConstants() {
		throw new AssertionError();
	}

}
