package org.sylrsykssoft.coreapi.framework.api.configuration;

/**
 * CoreApiFrameworkApiAddressConstants
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class CoreApiFrameworkApiAddressConstants {

	// REPOSITORY //
	public static final String ADDRESS_REPOSITORY_TABLE_NAME = "address";
	public static final String ADDRESS_REPOSITORY_ENTITY_NAME = "address";
	public static final String ONE_ADDRESS_REPOSITORY_TABLE_NAME = "one_address";
	public static final String ONE_ADDRESS_REPOSITORY_ENTITY_NAME = "oneAddress";
	public static final String MULTIPLE_ADDRESS_REPOSITORY_TABLE_NAME = "multiple_address";
	public static final String MULTIPLE_ADDRESS_REPOSITORY_ENTITY_NAME = "multipleAddress";

	// RESOURCE //
	public static final String ADDRESS_RESOURCE_BUILDER_NAME = "addressResourceBuilder";
	public static final String ADDRESS_RESOURCE_ASSEMBLER_NAME = "addressResourceAssembler";
	public static final String ONE_ADDRESS_RESOURCE_BUILDER_NAME = "oneAddressResourceBuilder";
	public static final String ONE_ADDRESS_RESOURCE_ASSEMBLER_NAME = "oneAddressResourceAssembler";
	public static final String MULTIPLE_ADDRESS_RESOURCE_BUILDER_NAME = "multipleAddressResourceBuilder";
	public static final String MULTIPLE_ADDRESS_RESOURCE_ASSEMBLER_NAME = "multipleAddressResourceAssembler";

	// PRIVATE //
	/**
	 * The caller references the constants using
	 * <tt>FunctionMemberConstants.EMPTY_STRING</tt>, and so on. Thus, the caller
	 * should be prevented from constructing objects of this class, by declaring
	 * this private constructor.
	 */
	private CoreApiFrameworkApiAddressConstants() {
		throw new AssertionError();
	}
}
