package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;

/**
 * Service.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface IAdminSimpleService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource, N extends Number> {

	/**
	 * Returns the number of entities available.
	 * 
	 * @return long the number of entities
	 */
	long count();

	/**
	 * Returns all instances of the type.
	 * 
	 * @return List<R> all entities
	 */
	List<R> findAll();

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return Optional<R> the entity with the given id or
	 *         {@literal Optional#empty()} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	Optional<R> findById(N id) throws NotFoundEntityException;

	/**
	 * Find by name.
	 * 
	 * @param name Value of the attribute name
	 * @return Optional<R>
	 * @throws NotFoundEntityException      if no entity exists for given
	 *                                      {@code id}.
	 * @throws IncorrectResultSizeException if there is more than one result.
	 */
	Optional<R> findByName(final String name) throws NotFoundEntityException, IncorrectResultSizeException;

}
