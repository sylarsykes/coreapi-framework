package org.sylrsykssoft.coreapi.framework.web.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.Link;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;
import org.sylrsykssoft.coreapi.framework.web.BaseAdminController;

/**
 * The Class BaseResourceAssembler.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminResourceAssembler<C extends BaseAdminController<R, T>, T extends BaseAdmin, R extends BaseAdminResource>
extends BaseAdminSimpleResourceAssembler<C, T, R> {

	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType" })
	public BaseAdminResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass, final Class<R> resourceType) {
		super(controllerClass, entityClass, resourceType);
	}

	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 * @param parameters the parameters
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType", "parameters" })
	public BaseAdminResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass, final Class<R> resourceType, final Object ...parameters) {
		super(controllerClass, entityClass, resourceType, parameters);
	}

	/** The base entity resource model mapper function. */
	@Override
	public abstract ModelMapperFunction<T, R> getAdminMapperToResourceFunction();

	/**
	 * To resource.
	 *
	 * @param entity the entity
	 * @return the base resource
	 */
	@Override
	public R toResource(final T entity) {
		final R instance = super.toResource(entity);
		final List<Link> links = new ArrayList<>();

		final Link idLink = linkTo(methodOn(controllerClass).findById(instance.getEntityId())).withSelfRel();
		idLink.withType("GET");
		final Link findByNameLink = linkTo(methodOn(controllerClass).findByName(instance.getName())).withRel("findByName");
		findByNameLink.withType("GET");
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
		final Link updateLink = linkTo(methodOn(controllerClass).update(instance.getEntityId(), instance))
				.withRel("update");
		updateLink.withType("POST");

		links.add(idLink);
		links.add(findByNameLink);
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
