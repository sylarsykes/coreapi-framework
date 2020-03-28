package org.sylrsykssoft.coreapi.framework.service.admin.create;

import java.util.List;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;

/**
 * ISaveAllAdminService save all AdminSimple service.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface ISaveAllAdminService<T extends BaseAdmin, R extends BaseAdminResource, N extends Number> {

	/**
	 * Saves all given entities.
	 * 
	 * @param entities must not be {@literal null}.
	 * @return List<R> the saved entities will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	List<R> saveAll(Iterable<R> entities) throws NotFoundEntityException;

}
