package org.sylrsykssoft.coreapi.framework.api.resource.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.sylrsykssoft.coreapi.framework.api.model.Base;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseResource;

/**
 * Create a instance of mapper.
 *
 * @author juan.gonzalez.fernandez.jgf
 * @param <T> the generic type
 * @param <R> the generic type
 */
@FunctionalInterface
public interface ResourceAssemblerFunction<T extends Base, R extends BaseResource> {

	/**
	 * Get concrete entity to resource mapper.
	 *
	 * @return ResourceAssemblerSupport<T, R>
	 */
	ResourceAssemblerSupport<T, R> getResourceAssembler();
	
}
