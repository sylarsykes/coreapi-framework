package org.sylrsykssoft.coreapi.framework.api.resource.assembler;

import java.beans.ConstructorProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.sylrsykssoft.coreapi.framework.api.model.Base;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseResource;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;


/**
 * The Class BaseResourceAssembler.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
public class BaseResourceAssembler extends ResourceAssemblerSupport<Base, BaseResource> {

	@Autowired
	@Qualifier("baseResourceModelMapperFunction")
	private ModelMapperFunction<Base, BaseResource> baseResourceModelMapperFunction;
	
	/**
	 * Instantiates a new base resource assembler.
	 *
	 * @param controllerClass the controller class
	 * @param resourceType the resource type
	 */
	@ConstructorProperties({ "controllerClass", "resourceType" })
	public BaseResourceAssembler(Class<Base> controllerClass, Class<BaseResource> resourceType) {
		super(controllerClass, resourceType);
	}

	/**
	 * To resource.
	 *
	 * @param entity the entity
	 * @return the base resource
	 */
	@Override
	public BaseResource toResource(Base entity) {
		return baseResourceModelMapperFunction.apply(entity);
	}

}
