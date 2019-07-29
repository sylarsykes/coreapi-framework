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
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotIdMismatchEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
import org.sylrsykssoft.coreapi.framework.service.BaseAdminService;
import org.sylrsykssoft.coreapi.framework.web.configuration.BaseAdminConstants;

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
public abstract class BaseAdminController<R extends BaseAdminResource, T extends BaseAdmin> {

	/**
	 * Getter admin service implementation
	 * 
	 * @return BaseAdminService<T, R>
	 */
	public abstract BaseAdminService<T, R> getAdminService(); 
	
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
	@GetMapping(path = BaseAdminConstants.CONTROLLER_GET_FIND_ONE_BY_ID, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public R findById(final @PathVariable Integer id) throws NotFoundEntityException {
		LOGGER.info("BaseAdminController::findOne Finding a entry with id: {}", id);
		
		final Optional<R> result = getAdminService().findById(id);
		
		LOGGER.info("BaseAdminController::findOne Result -> {}", result.get());
		
		return result.get();
	}
	
	/**
	 * Find by name.
	 * 
	 * @param name Value of attribute name.
	 * 
	 * @example /admin/musicalGenres/name/{name}]
	 * 
	 * @return T entity.
	 * 
	 * @throws NotFoundEntityException
	 */
	@GetMapping(path = BaseAdminConstants.CONTROLLER_GET_FIND_ONE_BY_NAME, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public R findByName(final @PathVariable String name) throws NotFoundEntityException {
		LOGGER.info("BaseAdminController::findByName Finding a entry with name: {}", name);
		
		final Optional<R> result = getAdminService().findByName(name);
		
		LOGGER.info("BaseAdminController::findByName Result -> {}", result.get());
		
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
	@PostMapping(path = BaseAdminConstants.CONTROLLER_GET_FIND_BY_EXAMPLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public R findOneByExample(final @RequestBody R resource) {
		LOGGER.info("BaseAdminController::findOne Finding a entry with id: {}", resource);
		
		Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Optional<R> result = getAdminService().findByExample(example);
		
		LOGGER.info("BaseAdminController::findOne Result -> {}", result.get());
		
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
		LOGGER.info("BaseAdminController:findAll Finding all entries");

		final Iterable<R> entities = getAdminService().findAll();
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseAdminController:findAll Found {} entries.", entities);

		return entities;
	}

	/**
	 * Find all entries by example.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@PostMapping(path = BaseAdminConstants.CONTROLLER_POST_FIND_ALL_BY_EXAMPLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Iterable<R> findAllByExample(final @RequestBody R resource) throws NotFoundEntityException {
		LOGGER.info("BaseAdminController:findAll Finding all entries");

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Iterable<R> entities = getAdminService().findAllByExample(example);
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseAdminController:findAll Found {} entries.", entities);

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
	@PostMapping(path = BaseAdminConstants.CONTROLLER_POST_FIND_ALL_BY_EXAMPLE_SORTABLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Iterable<R> findAllByExampleSortable(final @RequestBody R resource, 
			final @PathVariable String direction, final @PathVariable List<String> properties) throws NotFoundEntityException {
		LOGGER.info("BaseAdminController:findAllByExampleSortable Finding all entries with example {} with direction {} and properties {}", resource, direction, properties);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Direction sortDirection = Direction.fromString(direction);
		final Sort sort = new Sort(sortDirection, properties);
		
		final Iterable<R> entities = getAdminService().findAllByExampleSortable(example, sort);
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseAdminController:findAllByExampleSortable Found {} entries.", entities);

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
		LOGGER.info("BaseAdminController:create Creating a new todo entry by using information: {}", entity);

		final R created = getAdminService().save(entity);

		LOGGER.info("BaseAdminController:create Created a new todo entry: {}", created);

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
	@PutMapping(path = BaseAdminConstants.CONTROLLER_PUT_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public R update(final @Valid @RequestBody R entity, final @PathVariable Integer id) throws NotIdMismatchEntityException, NotFoundEntityException {
		LOGGER.info("BaseAdminController:update Updating a entry with id: {}", id);

		if (entity.getEntityId() != id) {
			throw new NotIdMismatchEntityException();
		}

		final Optional<R> old = getAdminService().findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		final R updated = getAdminService().save(entity);

		LOGGER.info("BaseAdminController:update Updated the entry: {}", updated);

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
	@DeleteMapping(path = BaseAdminConstants.CONTROLLER_DELETE_DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(final @PathVariable Integer id) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminController:delete Deleting a entry with id: {}", id);

		final Optional<R> old = getAdminService().findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		try {
			getAdminService().deleteById(id);
		} catch (Exception e) {
			throw new CoreApiFrameworkLibraryException(e);
		}
	}

}
