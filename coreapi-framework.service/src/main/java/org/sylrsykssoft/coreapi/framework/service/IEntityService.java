package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
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
public interface IEntityService<T extends BaseEntity, R extends BaseEntityResource, N extends Number> {

	/**
	 * Returns the number of entities available.
	 * 
	 * @return long the number of entities
	 */
	long count();

	/**
	 * Saves a given entity. Use the returned instance for further operations as the
	 * save operation might have changed the entity instance completely.
	 * 
	 * @param R entity must not be {@literal null}.
	 * @return the saved entity will never be {@literal null}.
	 */
	R create(R entity);

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
	 * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
	 */
	void deleteAll(Iterable<? extends R> entities) throws NotFoundEntityException;

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
	 */
	void deleteById(N id) throws NotFoundEntityException;

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return boolean {@literal true} if an entity with the given id exists,
	 *         {@literal false} otherwise.
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	boolean existsById(N id);

	/**
	 * Returns all instances of the type.
	 * 
	 * @return List<R> all entities
	 */
	List<R> findAll();

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
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return Optional<R> the entity with the given id or
	 *         {@literal Optional#empty()} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	Optional<R> findById(N id) throws NotFoundEntityException;

	/**
	 * Saves all given entities.
	 * 
	 * @param entities must not be {@literal null}.
	 * @return List<R> the saved entities will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	List<R> saveAll(Iterable<R> entities) throws NotFoundEntityException;

	/**
	 * Saves a given entity. Use the returned instance for further operations as the
	 * save operation might have changed the entity instance completely.
	 * 
	 * @param entity must not be {@literal null}.
	 * @return R the saved entity will never be {@literal null}.
	 */
	R update(R entity) throws NotFoundEntityException;
}
