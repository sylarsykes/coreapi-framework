package org.sylrsykssoft.coreapi.framework.service.admin.update;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;

/**
 * IUpdateAdminService define the service for updating of AdminSimple
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface IUpdateAdminService<T extends BaseAdmin, R extends BaseAdminResource, N extends Number> {

	/**
	 * Saves a given entity. Use the returned instance for further operations as the
	 * save operation might have changed the entity instance completely.
	 * 
	 * @param entity must not be {@literal null}.
	 * @return R the saved entity will never be {@literal null}.
	 */
	R update(R entity) throws NotFoundEntityException;
}
