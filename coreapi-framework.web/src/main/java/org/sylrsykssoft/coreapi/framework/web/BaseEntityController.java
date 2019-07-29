package org.sylrsykssoft.coreapi.framework.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotIdMismatchEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
import org.sylrsykssoft.coreapi.framework.service.BaseEntityService;
import org.sylrsykssoft.coreapi.framework.web.configuration.BaseEntityConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Base admin controller
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <R> Resource class
 * @param <T> Admin class
 */
@Slf4j
public abstract class BaseEntityController<R extends BaseEntityResource, T extends BaseEntity> {

	/**
	 * Getter service implementation
	 * 
	 * @return BaseEntityService<T, R>
	 */
	public abstract BaseEntityService<T, R> getEntityService();
	
	/**
	 * Find one entry.
	 * 
	 * @param id
	 *            Id
	 * 
	 * @return T entry.
	 * 
	 * @throws NotFoundEntityException
	 */
	@GetMapping(path = BaseEntityConstants.CONTROLLER_GET_FIND_ONE_BY_ID, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public R findById(final @PathVariable Long id) throws NotFoundEntityException {
		LOGGER.info("BaseEntityController::findOne Finding a entry with id: {}", id);
		
		final Optional<R> result = getEntityService().findById(id);
		
		LOGGER.info("BaseEntityController::findOne Result -> {}", result.get());
		
		return result.get();
	}
	
	/**
	 * Find by example
	 * 
	 * @param resource
	 *            Entity to find.
	 * 
	 * @return T entity.
	 * 
	 * @throws NotFoundEntityException
	 */
	@PostMapping(path = BaseEntityConstants.CONTROLLER_GET_FIND_BY_EXAMPLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public R findOneByExample(final @RequestBody R resource) {
		LOGGER.info("BaseEntityController::findOne Finding a entry with id: {}", resource);
		
		Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Optional<R> result = getEntityService().findByExample(example);
		
		LOGGER.info("BaseEntityController::findOne Result -> {}", result.get());
		
		return result.get();
	}
	
	/**
	 * Find all entries.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@GetMapping
	public Iterable<R> findAll() throws NotFoundEntityException {
		LOGGER.info("BaseEntityController:findAll Finding all entries");

		final Iterable<R> entities = getEntityService().findAll();
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseEntityController:findAll Found {} entries.", entities);

		return entities;
	}

	/**
	 * Find all entries by example.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@PostMapping(path = BaseEntityConstants.CONTROLLER_POST_FIND_ALL_BY_EXAMPLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Iterable<R> findAllByExample(final @RequestBody R resource) throws NotFoundEntityException {
		LOGGER.info("BaseEntityController:findAll Finding all entries");

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Iterable<R> entities = getEntityService().findAllByExample(example);
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseEntityController:findAll Found {} entries.", entities);

		return entities;
	}
	
	/**
	 * Find all entries by example.
	 * 
	 * @param resource MusicalGenreResource object
	 * @param direction Sorting direction values "asc" or "desc"
	 * @param properties List of properties
	 * 
	 * @return List<MusicalGenreResource> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@PostMapping(path = BaseEntityConstants.CONTROLLER_POST_FIND_ALL_BY_EXAMPLE_SORTABLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Iterable<R> findAllByExampleSortable(final @RequestBody R resource, 
			final @PathVariable String direction, final @PathVariable List<String> properties) throws NotFoundEntityException {
		LOGGER.info("BaseEntityController:findAllByExampleSortable Finding all entries with example {} with direction {} and properties {}", resource, direction, properties);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Direction sortDirection = Direction.fromString(direction);
		final Sort sort = new Sort(sortDirection, properties);
		
		final Iterable<R> entities = getEntityService().findAllByExampleSortable(example, sort);
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseEntityController:findAllByExampleSortable Found {} entries.", entities);

		return entities;
	}
	
	/**
	 * Create entry.
	 * 
	 * @param entity
	 *            Entity.
	 * 
	 * @return T entity.
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public R create(final @Valid @RequestBody R entity) {
		LOGGER.info("BaseEntityController:create Creating a new todo entry by using information: {}", entity);

		final R created = getEntityService().save(entity);

		LOGGER.info("BaseEntityController:create Created a new todo entry: {}", created);

		return created;
	}

	/**
	 * Update entity.
	 * 
	 * @param entity
	 *            Entity.
	 * @param id
	 *            Id.
	 * 
	 * @return T entity.
	 * 
	 * @throws NotIdMismatchEntityException
	 * @throws NotFoundEntityException
	 */
	@PutMapping(path = BaseEntityConstants.CONTROLLER_PUT_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public R update(final @Valid @RequestBody R entity, final @PathVariable Long id) throws NotIdMismatchEntityException, NotFoundEntityException {
		LOGGER.info("BaseEntityController:update Updating a entry with id: {}", id);

		if (entity.getEntityId() != id) {
			throw new NotIdMismatchEntityException();
		}

		final Optional<R> old = getEntityService().findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		final R updated = getEntityService().save(entity);

		LOGGER.info("BaseEntityController:update Updated the entry: {}", updated);

		return updated;
	}

	/**
	 * Delete entry.
	 * 
	 * @param id
	 *            Id.
	 * 
	 * @throws NotFoundEntityException
	 * @throws AppException
	 */
	@DeleteMapping(path = BaseEntityConstants.CONTROLLER_DELETE_DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(final @PathVariable Long id) throws NotFoundEntityException, CoreApiFrameworkLibraryException {	
		LOGGER.info("BaseEntityController:delete Deleting a entry with id: {}", id);

		final Optional<R> old = getEntityService().findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		try {
			getEntityService().deleteById(id);
		} catch (Exception e) {
			throw new CoreApiFrameworkLibraryException(e);
		}
	}
	
}
