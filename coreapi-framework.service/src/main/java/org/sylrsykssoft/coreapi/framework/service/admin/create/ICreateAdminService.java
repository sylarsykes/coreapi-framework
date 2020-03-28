package org.sylrsykssoft.coreapi.framework.service.admin.create;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;

/**
 * ICreateAdminService define the service for the creation of AdminSimple.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface ICreateAdminService<T extends BaseAdmin, R extends BaseAdminResource, N extends Number> {

	/**
	 * Saves a given entity. Use the returned instance for further operations as the
	 * save operation might have changed the entity instance completely.
	 * 
	 * @param R entity must not be {@literal null}.
	 * @return the saved entity will never be {@literal null}.
	 */
	R create(R entity);
}
