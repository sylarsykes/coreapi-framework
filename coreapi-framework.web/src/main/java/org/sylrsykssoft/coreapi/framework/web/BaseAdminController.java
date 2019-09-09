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
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;
import org.sylrsykssoft.coreapi.framework.service.BaseAdminService;
import org.sylrsykssoft.coreapi.framework.web.configuration.BaseAdminConstants;

/**
 * Base admin controller
 * 
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminController<R extends BaseAdminResource, T extends BaseAdmin> {

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
	public R create(final @Valid @RequestBody R entity)
			throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminController::create Creating a new todo entry by using information: {}", entity);

		final R created = getAdminService().create(entity);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::create Created a new todo entry: {}", created);

		return created;
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(final @PathVariable Integer id) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::delete Deleting a entry with id: {}", id);

		final Optional<R> old = getAdminService().findById(id);
		if (!old.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminController::delete not find result for id -> {}", id);
			throw new NotFoundEntityException();
		}

		try {
			getAdminService().deleteById(id);
		} catch (final Exception e) {
			throw new CoreApiFrameworkLibraryException(e);
		}
	}

	/**
	 * Find all entries.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@GetMapping(produces = { MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	public Iterable<R> findAll() throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findAll Finding all entries");

		final Iterable<R> entities = getAdminService().findAll();
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminController::findAll not find result");
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findAll Found {} entries.", entities);

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
	@ResponseStatus(HttpStatus.FOUND)
	public Iterable<R> findAllByExample(final @RequestBody R resource) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminController::findAllByExample Finding all entries for example: {}", resource);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());

		final Iterable<R> entities = getAdminService().findAllByExample(example);
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminController::findAllByExample not find result for example -> {}", resource);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findAllByExample Found {} entries.", entities);

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
	@ResponseStatus(HttpStatus.FOUND)
	public Iterable<R> findAllByExampleSortable(final @RequestBody R resource,
			final @PathVariable String direction, final @PathVariable List<String> properties) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminController::findAllByExampleSortable Finding all entries with example {} with direction {} and properties {}",
				resource, direction, properties);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());

		final Direction sortDirection = Direction.fromString(direction);
		final Sort sort = new Sort(sortDirection, properties);

		final Iterable<R> entities = getAdminService().findAllByExampleSortable(example, sort);
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminController::findAllByExampleSortable not find result for example -> {}", resource);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findAllByExampleSortable Found {} entries.",
				entities);

		return entities;
	}

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
	@ResponseStatus(HttpStatus.FOUND)
	public R findById(final @PathVariable Integer id) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findById Finding a entry with id: {}", id);

		final Optional<R> result = getAdminService().findById(id);

		if (!result.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminController::findById not find result for id -> {}", id);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findById Result -> {}", result.get());
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
	@ResponseStatus(HttpStatus.FOUND)
	public R findByName(final @PathVariable String name) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findByName Finding a entry with name: {}", name);

		final Optional<R> result = getAdminService().findByName(name);

		if (!result.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminController::findByName not find result for name -> {}",
					name);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findByName Result -> {}", result.get());

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
	@ResponseStatus(HttpStatus.FOUND)
	public R findOneByExample(final @RequestBody R resource) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findOneByExample Finding a entry with: {}",
				resource);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());

		final Optional<R> result = getAdminService().findByExample(example);

		if (!result.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminController::findOneByExample not find result for example -> {}", resource);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findOneByExample Result -> {}", result.get());

		return result.get();
	}

	/**
	 * Getter admin service implementation
	 * 
	 * @return BaseAdminService<T, R>
	 */
	public abstract BaseAdminService<T, R> getAdminService();

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
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::update Updating a entry with id: {}", id);

		if (entity.getEntityId() != id)
			throw new NotIdMismatchEntityException();

		final Optional<R> old = getAdminService().findById(id);
		if (!old.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminController::update not find result for id -> {}", id);
			throw new NotFoundEntityException();
		}

		final R updated = getAdminService().update(entity);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::update Updated the entry: {}", updated);

		return updated;
	}

}
