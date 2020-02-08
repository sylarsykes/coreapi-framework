package org.sylrsykssoft.coreapi.framework.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;
import org.sylrsykssoft.coreapi.framework.security.annotation.AuthorizedIfHaveRole;
import org.sylrsykssoft.coreapi.framework.security.annotation.AuthorizedIfHaveRoleAdmin;
import org.sylrsykssoft.coreapi.framework.security.util.AuthenticationFacade;
import org.sylrsykssoft.coreapi.framework.security.util.LoggerUserUtil;
import org.sylrsykssoft.coreapi.framework.service.BaseEntityService;
import org.sylrsykssoft.coreapi.framework.web.configuration.BaseEntityConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Base admin controller
 *
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseEntityController<R extends BaseEntityResource, T extends BaseEntity> {

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected AuthenticationFacade authenticationFacade;

	/**
	 * Create entry.
	 * 
	 * @param entity
	 *            Entity.
	 * 
	 * @return T entity.
	 */
	@ApiOperation(value = "Create an entry", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created entry"),
			@ApiResponse(code = 201, message = "Successfully created entry"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	@AuthorizedIfHaveRoleAdmin
	public R create(
			final @ApiParam(name = "resource", value = "Entry object store in database table", required = true) @Valid @RequestBody R entity) {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::create");
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseEntityController::create Creating a new todo entry by using information: {}", entity);

		final R created = getEntityService().create(entity);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::create Created a new todo entry: {}", created);

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
	@ApiOperation(value = "Delete an entry", httpMethod = "DELETE")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created entry"),
			@ApiResponse(code = 204, message = "Successfully created entry"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "The resource you were trying to reach is not found") })
	@DeleteMapping(path = BaseEntityConstants.CONTROLLER_DELETE_DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@AuthorizedIfHaveRoleAdmin
	public void delete(
			final @ApiParam(name = "id", value = "Entry id value", type = "Integer", required = true) @PathVariable Long id)
					throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::delete");
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::delete Deleting a entry with id: {}", id);

		final Optional<R> old = getEntityService().findById(id);
		if (!old.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseEntityController::delete not find result for id -> {}", id);
			throw new NotFoundEntityException();
		}

		try {
			getEntityService().deleteById(id);
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
	@ApiOperation(value = "View a list of all available entries", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 302, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(produces = { MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	public Iterable<R> findAll() throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findAll");
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findAll Finding all entries");

		final Iterable<R> entities = getEntityService().findAll();
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminController::findAll not find result");
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findAll Found {} entries.", entities);

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
	@ResponseStatus(HttpStatus.FOUND)
	@AuthorizedIfHaveRole
	public Iterable<R> findAllByExample(final @RequestBody R resource) throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findAllByExample");
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseEntityController::findAllByExample Finding all entries for example: {}", resource);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());

		final Iterable<R> entities = getEntityService().findAllByExample(example);
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseEntityController::findAllByExample not find result for example -> {}", resource);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findAllByExample Found {} entries.", entities);

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
	@ResponseStatus(HttpStatus.FOUND)
	@AuthorizedIfHaveRole
	public Iterable<R> findAllByExampleSortable(final @RequestBody R resource,
			final @PathVariable String direction, final @PathVariable List<String> properties) throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findAllByExampleSortable");
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseEntityController::findAllByExampleSortable Finding all entries with example {} with direction {} and properties {}",
				resource, direction, properties);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());

		final Direction sortDirection = Direction.fromString(direction);
		final Sort sort = new Sort(sortDirection, properties);

		final Iterable<R> entities = getEntityService().findAllByExampleSortable(example, sort);
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseEntityController::findAllByExampleSortable not find result for example -> {}", resource);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findAllByExampleSortable Found {} entries.",
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
	@ApiOperation(value = "Find a entry for your id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved entry"),
			@ApiResponse(code = 302, message = "Successfully retrieved try"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(path = BaseEntityConstants.CONTROLLER_GET_FIND_ONE_BY_ID, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.FOUND)
	public R findById(
			final @ApiParam(name = "id", value = "Entry id value", type = "Integer", required = true) @PathVariable Long id)
					throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findById");
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findById Finding a entry with id: {}", id);

		final Optional<R> result = getEntityService().findById(id);

		if (!result.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseEntityController::findById not find result for id -> {}", id);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findById Result -> {}", result.get());

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
	@ApiOperation(value = "Find a entry for resource example", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved entry"),
			@ApiResponse(code = 302, message = "Successfully retrieved try"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(path = BaseEntityConstants.CONTROLLER_GET_FIND_BY_EXAMPLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	@AuthorizedIfHaveRole
	public R findOneByExample(
			final @ApiParam(name = "resource", value = "Resource example", required = true) @RequestBody R resource)
					throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findOneByExample");
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findOneByExample Finding a entry : {}",
				resource);

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());

		final Optional<R> result = getEntityService().findByExample(example);

		if (!result.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseEntityController::findOneByExample not find result for example -> {}", resource);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::findOneByExample Result -> {}", result.get());

		return result.get();
	}

	/**
	 * Getter service implementation
	 * 
	 * @return BaseEntityService<T, R>
	 */
	public abstract BaseEntityService<T, R> getEntityService();

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
	@ApiOperation(value = "Update an entry", httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved entry"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PutMapping(path = BaseEntityConstants.CONTROLLER_PUT_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@AuthorizedIfHaveRoleAdmin
	public R update(
			final @ApiParam(name = "id", value = "Entry id value", type = "Integer", required = true) @PathVariable Long id,
			final @ApiParam(name = "resource", value = "Entry object store in database table", required = true) @Valid @RequestBody R entity)
					throws NotIdMismatchEntityException, NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::update");
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::update Updating a entry with id: {}", id);

		if (entity.getEntityId() != id)
			throw new NotIdMismatchEntityException();

		final Optional<R> old = getEntityService().findById(id);
		if (!old.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseEntityController::update not find result for id -> {}", id);
			throw new NotFoundEntityException();
		}

		final R updated = getEntityService().update(entity);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityController::update Updated the entry: {}", updated);

		return updated;
	}

}
