package org.sylrsykssoft.coreapi.framework.api.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.beans.ConstructorProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;


/**
 * The Class BaseResourceAssembler.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public class BaseEntityResourceAssembler extends ResourceAssemblerSupport<BaseEntity, BaseEntityResource> {

	private Class<BaseEntity> entityClass;
	private Object[] parameters;
	
	@Autowired
	@Qualifier("baseEntityResourceModelMapperFunction")
	private ModelMapperFunction<BaseEntity, BaseEntityResource> baseEntityResourceModelMapperFunction;

	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 */
	@ConstructorProperties({ "controllerClass", "resourceType" })
	public BaseEntityResourceAssembler(Class<BaseEntity> controllerClass, Class<BaseEntityResource> resourceType) {
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
	public BaseEntityResourceAssembler(Class<BaseEntity> controllerClass, Class<BaseEntityResource> resourceType, Object... parameters) {
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
	public BaseEntityResource toResource(BaseEntity entity) {
		final BaseEntityResource instance = baseEntityResourceModelMapperFunction.apply(entity);
		instance.add(linkTo(entityClass, parameters).slash(entity.getEntityId()).withSelfRel());
		
		return instance;
	}

}
