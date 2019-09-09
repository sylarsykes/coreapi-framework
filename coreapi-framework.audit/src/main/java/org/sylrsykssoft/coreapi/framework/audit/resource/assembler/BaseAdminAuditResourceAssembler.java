package org.sylrsykssoft.coreapi.framework.audit.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.sylrsykssoft.coreapi.framework.audit.controller.BaseAdminAuditController;
import org.sylrsykssoft.coreapi.framework.audit.domain.BaseAdminAudit;
import org.sylrsykssoft.coreapi.framework.audit.resource.BaseAdminAuditResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;
import org.sylrsykssoft.coreapi.framework.web.resource.assembler.BaseAdminResourceAssembler;

/**
 * The Class BaseResourceAssembler.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminAuditResourceAssembler<C extends BaseAdminAuditController<R, T>, T extends BaseAdminAudit, R extends BaseAdminAuditResource>
extends BaseAdminResourceAssembler<C, T, R> {

	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 */
	@ConstructorProperties({ "controllerClass", "entityClass", "resourceType" })
	public BaseAdminAuditResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass,
			final Class<R> resourceType) {
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
	public BaseAdminAuditResourceAssembler(final Class<C> controllerClass, final Class<T> entityClass,
			final Class<R> resourceType, final Object... parameters) {
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

		final Link findLastChangeRevisionLink = linkTo(
				methodOn(controllerClass).findLastChangeRevision(instance.getEntityId()))
				.withRel("findLastChangeRevision");
		findLastChangeRevisionLink.withType("GET");
		final Link findRevisionLink = linkTo(
				methodOn(controllerClass).findRevision(instance.getEntityId(), instance.getVersion()))
				.withRel("findRevision");
		findRevisionLink.withType("GET");
		//		final Link findRevisionsLink = linkTo(methodOn(controllerClass).findRevisions(instance.getEntityId()))
		//				.withRel("findRevisions");
		//		findRevisionsLink.withType("GET");
		//		final Link findOneByExampleLink = linkTo(methodOn(controllerClass).findOneByExample(instance)).withRel("findOneByExample");
		//		findOneByExampleLink.withType("POST");

		links.add(findLastChangeRevisionLink);
		links.add(findRevisionLink);
		//		links.add(findRevisionsLink);
		//		links.add(findOneByExampleLink);

		instance.add(links);

		return instance;
	}

}
