package org.sylrsykssoft.coreapi.framework.security.resource.assembler;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;
import org.sylrsykssoft.coreapi.framework.security.controller.BaseUserController;
import org.sylrsykssoft.coreapi.framework.security.domain.BaseUser;
import org.sylrsykssoft.coreapi.framework.security.resource.BaseUserResource;

public abstract class BaseUserResourceAssembler<C extends BaseUserController<R, T, N>, T extends BaseUser<N>, R extends BaseUserResource<N>, N extends Number>
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
	 * @param resourceType    the resource type
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType" })
	public BaseUserResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass,
			final Class<R> resourceType) {
		super(entityClass, resourceType);
		this.controllerClass = controllerClass;
		this.entityClass = entityClass;
		parameters = new Object[0];
	}

	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType    the resource type
	 * @param parameters      the parameters
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType", "parameters" })
	public BaseUserResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass,
			final Class<R> resourceType, final Object... parameters) {
		super(entityClass, resourceType);
		this.controllerClass = controllerClass;
		this.entityClass = entityClass;
		this.parameters = parameters;
	}

	/** The base entity resource model mapper function. */
	public abstract ModelMapperFunction<T, R> getUserEntityMapperToResourceFunction();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R toResource(final T entity) {
		final R instance = getUserEntityMapperToResourceFunction().apply(entity);
		final List<Link> links = new ArrayList<>();

		instance.add(links);

		return instance;
	}
}
