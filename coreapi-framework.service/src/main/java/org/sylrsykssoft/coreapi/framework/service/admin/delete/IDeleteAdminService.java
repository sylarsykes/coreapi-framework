package org.sylrsykssoft.coreapi.framework.service.admin.delete;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;

/**
 * IDeleteAdminService define the service for the delete of AdminSimple.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface IDeleteAdminService<T extends BaseAdmin, R extends BaseAdminResource, N extends Number> {

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	void delete(R entity) throws NotFoundEntityException;

	/**
	 * Deletes all entities managed by the repository.
	 */
	void deleteAll();

	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException in case the given {@link Iterable} is
	 *                                  {@literal null}.
	 */
	void deleteAll(Iterable<? extends R> entities) throws NotFoundEntityException;

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@code id} is
	 *                                  {@literal null}
	 */
	void deleteById(N id) throws NotFoundEntityException;
}
