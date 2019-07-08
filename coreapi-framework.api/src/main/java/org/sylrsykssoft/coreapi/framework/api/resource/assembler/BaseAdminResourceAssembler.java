package org.sylrsykssoft.coreapi.framework.api.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.beans.ConstructorProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;


/**
 * The Class BaseResourceAssembler.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public class BaseAdminResourceAssembler extends ResourceAssemblerSupport<BaseAdmin, BaseAdminResource> {

	/** The entity class. */
	private Class<BaseAdmin> entityClass;
	
	/** The parameters. */
	private Object[] parameters;
	
	/** The base entity resource model mapper function. */
	@Autowired
	@Qualifier("baseEntityResourceModelMapperFunction")
	private ModelMapperFunction<BaseAdmin, BaseAdminResource> baseEntityResourceModelMapperFunction;
	
	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 */
	@ConstructorProperties({ "controllerClass", "resourceType" })
	public BaseAdminResourceAssembler(Class<BaseAdmin> controllerClass, Class<BaseAdminResource> resourceType) {
		super(controllerClass, resourceType);
		entityClass = controllerClass;
		parameters = new Object[0];
	}
	
	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 * @param parameters the parameters
	 */
	@ConstructorProperties({ "controllerClass", "resourceType", "parameters" })
	public BaseAdminResourceAssembler(Class<BaseAdmin> controllerClass, Class<BaseAdminResource> resourceType, final Object ...parameters) {
		super(controllerClass, resourceType);
		entityClass = controllerClass;
		this.parameters = parameters;
	}
	
	/**
	 * To resource.
	 *
	 * @param entity the entity
	 * @return the base resource
	 */
	@Override
	public BaseAdminResource toResource(BaseAdmin entity) {
		final BaseAdminResource instance = baseEntityResourceModelMapperFunction.apply(entity);
		instance.add(linkTo(entityClass, parameters).slash(entity.getEntityId()).withSelfRel());
		
		return instance;
	}

}
