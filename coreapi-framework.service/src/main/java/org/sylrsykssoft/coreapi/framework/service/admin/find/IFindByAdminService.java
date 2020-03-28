package org.sylrsykssoft.coreapi.framework.service.admin.find;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;

/**
 * IFindByAdminService define services for find a entity AdminSimple.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface IFindByAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource, N extends Number> {

	/**
	 * Returns a single entity matching the given {@link Example} or {@literal null}
	 * if none was found.
	 *
	 * @param example must not be {@literal null}.
	 * @return Optional<R> a single entity matching the given {@link Example} or
	 *         {@link Optional#empty()} if none was found.
	 * @throws NotFoundEntityException      if no entity exists for given
	 *                                      {@code id}.
	 * @throws IncorrectResultSizeException if the Example yields more than one
	 *                                      result.
	 */
	Optional<R> findByExample(Example<R> example) throws NotFoundEntityException, IncorrectResultSizeException;

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
