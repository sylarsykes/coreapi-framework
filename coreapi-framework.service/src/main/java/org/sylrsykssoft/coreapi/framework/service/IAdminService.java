package org.sylrsykssoft.coreapi.framework.service;

import java.io.Serializable;
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
 * @param <ID> Class of identificator.
 */
public interface IAdminService<T extends BaseAdmin, R extends BaseAdminResource, ID extends Serializable> {

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	Optional<R> findById(ID id) throws NotFoundEntityException;
	
	/**
	 * Find by name.
	 * 
	 * @param name Value of the attribute name
	 * @return T entity.
	 * @throws NotFoundEntityException if no entity exists for given {@code id}.
	 * @throws IncorrectResultSizeException if there is more than one result.
	 */
	Optional<R> findByName(final String name) throws NotFoundEntityException, IncorrectResultSizeException;
	
	/**
	 * Returns a single entity matching the given {@link Example} or {@literal null} if none was found.
	 *
	 * @param example must not be {@literal null}.
	 * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
	 * @throws NotFoundEntityException if no entity exists for given {@code id}.
	 * @throws IncorrectResultSizeException if the Example yields more than one result.
	 */
	Optional<R> findByExample(Example<R> example) throws NotFoundEntityException, IncorrectResultSizeException;
	
	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	List<R> findAllById(Iterable<ID> ids);
	
	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	List<R> findAll();

	/**
	 * Returns all entities matching the given {@link Example}. In case no match could be found an empty {@link Iterable}
	 * is returned.
	 *
	 * @param example must not be {@literal null}.
	 * @return all entities matching the given {@link Example}.
	 */
	List<R> findAllByExample(Example<R> example);

	/**
	 * Returns all entities matching the given {@link Example} applying the given {@link Sort}. In case no match could be
	 * found an empty {@link Iterable} is returned.
	 *
	 * @param example must not be {@literal null}.
	 * @param sort the {@link Sort} specification to sort the results by, must not be {@literal null}.
	 * @return all entities matching the given {@link Example}.
	 */
	List<R> findAllByExampleSortable(Example<R> example, Sort sort);
	
	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	boolean existsById(ID id);
	
	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	long count();
	
	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 * 
	 * @param entity must not be {@literal null}.
	 * @return the saved entity will never be {@literal null}.
	 */
	R create(R entity);
	
	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 * 
	 * @param entity must not be {@literal null}.
	 * @return the saved entity will never be {@literal null}.
	 */
	R update(R entity) throws NotFoundEntityException;

	/**
	 * Saves all given entities.
	 * 
	 * @param entities must not be {@literal null}.
	 * @return the saved entities will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	List<R> saveAll(Iterable<R> entities) throws NotFoundEntityException;

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
	 */
	void deleteById(ID id) throws NotFoundEntityException;

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	void delete(R entity) throws NotFoundEntityException;

	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
	 */
	void deleteAll(Iterable<? extends R> entities) throws NotFoundEntityException;

	/**
	 * Deletes all entities managed by the repository.
	 */
	void deleteAll();
}
