package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
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
public interface IAdminService<T extends BaseAdmin, R extends BaseAdminResource, N extends Number>
extends IAdminSimpleService<T, R, N> {

	/**
	 * Returns all entities matching the given {@link Example}. In case no match
	 * could be found an empty {@link Iterable} is returned.
	 *
	 * @param example must not be {@literal null}.
	 * @return List<R> all entities matching the given {@link Example}.
	 */
	List<R> findAllByExample(Example<R> example);

	/**
	 * Returns all entities matching the given {@link Example} applying the given
	 * {@link Sort}. In case no match could be found an empty {@link Iterable} is
	 * returned.
	 *
	 * @param example must not be {@literal null}.
	 * @param sort    the {@link Sort} specification to sort the results by, must
	 *                not be {@literal null}.
	 * @return List<R> all entities matching the given {@link Example}.
	 */
	List<R> findAllByExampleSortable(Example<R> example, Sort sort);

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return List<R>
	 */
	List<R> findAllById(Iterable<N> ids);

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
	 * Saves all given entities.
	 * 
	 * @param entities must not be {@literal null}.
	 * @return List<R> the saved entities will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	List<R> saveAll(Iterable<R> entities) throws NotFoundEntityException;

}
