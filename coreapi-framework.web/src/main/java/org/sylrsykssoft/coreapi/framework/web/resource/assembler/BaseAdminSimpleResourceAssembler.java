package org.sylrsykssoft.coreapi.framework.web.resource.assembler;

import java.beans.ConstructorProperties;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;
import org.sylrsykssoft.coreapi.framework.web.BaseAdminSimpleController;

/**
 * The Class BaseResourceAssembler.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminSimpleResourceAssembler<C extends BaseAdminSimpleController<R, T>, T extends BaseAdminSimple, R extends BaseAdminSimpleResource>
		extends ResourceAssemblerSupport<T, R> {

	protected Class<C> controllerClass;
	/** The entity class. */
	protected Class<T> entityClass;

	/** The parameters. */
	protected Object[] parameters;

	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType" })
	public BaseAdminSimpleResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass, final Class<R> resourceType) {
		super(entityClass, resourceType);
		this.controllerClass = controllerClass;
		this.entityClass = entityClass;
		parameters = new Object[0];
	}

	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 * @param parameters the parameters
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType", "parameters" })
	public BaseAdminSimpleResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass, final Class<R> resourceType, final Object ...parameters) {
		super(entityClass, resourceType);
		this.controllerClass = controllerClass;
		this.entityClass = entityClass;
		this.parameters = parameters;
	}

	/** The base entity resource model mapper function. */
	public abstract ModelMapperFunction<T, R> getAdminMapperToResourceFunction();

	/**
	 * To resource.
	 *
	 * @param entity the entity
	 * @return the base resource
	 */
	@Override
	public R toResource(final T entity) {
		final R instance = getAdminMapperToResourceFunction().apply(entity);

		return instance;
	}

}
