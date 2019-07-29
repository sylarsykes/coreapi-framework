package org.sylrsykssoft.coreapi.framework.web.resource.assembler;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;
import org.sylrsykssoft.coreapi.framework.web.BaseEntityController;


/**
 * The Class BaseResourceAssembler.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseEntityResourceAssembler<C extends BaseEntityController, T extends BaseEntity, R extends BaseEntityResource> extends ResourceAssemblerSupport<T, R> {

	protected Class<C> controllerClass;
	/** The entity class. */
	protected Class<T> entityClass;
	
	/** The parameters. */
	protected Object[] parameters;
	
	/** The base entity resource model mapper function. */
	public abstract ModelMapperFunction<T, R> getEntityMapperToResourceFunction();
	
	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType" })
	public BaseEntityResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass, final Class<R> resourceType) {
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
	public BaseEntityResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass, final Class<R> resourceType, final Object ...parameters) {
		super(entityClass, resourceType);
		this.controllerClass = controllerClass;
		this.entityClass = entityClass;
		this.parameters = parameters;
	}
	
	/**
	 * To resource.
	 *
	 * @param entity the entity
	 * @return the base resource
	 */
	@SuppressWarnings("unchecked")
	@Override
	public R toResource(final T entity) {
		final R instance = getEntityMapperToResourceFunction().apply(entity);
		final List<Link> links = new ArrayList<Link>();
		
		final Link idLink = linkTo(methodOn(controllerClass).findById(instance.getEntityId())).withSelfRel();
		idLink.withType("GET");
		final Link findOneByExampleLink = linkTo(methodOn(controllerClass).findOneByExample(instance)).withRel("findOneByExample");
		findOneByExampleLink.withType("POST");
		final Link findAllLink = linkTo(methodOn(controllerClass).findAll()).withRel("findAll");
		findAllLink.withType("GET");
		final Link findAllByExampleLink = linkTo(methodOn(controllerClass).findAllByExample(instance)).withRel("findAllByExample");
		findAllByExampleLink.withType("POST");
		final Link findAllByExampleSortableLink = linkTo(
				methodOn(controllerClass).findAllByExampleSortable(instance, "asc", Arrays.asList("name"))).withRel("findAllByExampleSortable");
		findAllByExampleSortableLink.withType("POST");
		final Link createLink = linkTo(methodOn(controllerClass).create(instance)).withRel("create");
		createLink.withType("POST");
		final Link updateLink = linkTo(methodOn(controllerClass).update(instance, instance.getEntityId())).withRel("update");
		updateLink.withType("POST");
		
		links.add(idLink);
		links.add(findOneByExampleLink);
		links.add(findAllLink);
		links.add(findAllByExampleLink);
		links.add(findAllByExampleSortableLink);
		links.add(createLink);
		links.add(updateLink);
		
		instance.add(links);
		
		return instance;
	}

}
