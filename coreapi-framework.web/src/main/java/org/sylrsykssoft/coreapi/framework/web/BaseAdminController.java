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
import org.sylrsykssoft.coreapi.framework.security.annotation.AuthorizedIfHaveRole;
import org.sylrsykssoft.coreapi.framework.security.annotation.AuthorizedIfHaveRoleAdmin;
import org.sylrsykssoft.coreapi.framework.security.util.LoggerUserUtil;
import org.sylrsykssoft.coreapi.framework.service.BaseAdminService;
import org.sylrsykssoft.coreapi.framework.web.configuration.BaseAdminConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;

/**
 * Base admin controller
 * 
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminController<R extends BaseAdminResource, T extends BaseAdmin>
		extends BaseAdminSimpleController<R, T> {

	/**
	 * Create entry.
	 * 
	 * @param entity Entity.
	 * 
	 * @return T entity.
	 */
	@ApiOperation(value = "Create an entry", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created entry"),
			@ApiResponse(code = 201, message = "Successfully created entry"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaTypes.HAL_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	@AuthorizedIfHaveRoleAdmin
	public R create(
			final @ApiParam(name = "resource", value = "Entry object store in database table", required = true, examples = @io.swagger.annotations.Example(value = @ExampleProperty(value = "{'name': 'Entity name', 'description': 'Entity description'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))

			@Valid @RequestBody R entity) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::create");
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminController::create Creating a new todo entry by using information: {}", entity);

		final R created = getAdminService().create(entity);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::create Created a new todo entry: {}", created);

		return created;
	}

	/**
	 * Delete entry.
	 * 
	 * @param id Id.
	 * 
	 * @throws NotFoundEntityException
	 * @throws AppException
	 */
	@ApiOperation(value = "Delete an entry", httpMethod = "DELETE")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created entry"),
			@ApiResponse(code = 204, message = "Successfully created entry"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "The resource you were trying to reach is not found") })
	@DeleteMapping(path = BaseAdminConstants.CONTROLLER_DELETE_DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@AuthorizedIfHaveRoleAdmin
	public void delete(
			final @ApiParam(name = "id", value = "Entry id value", type = "Integer", required = true, example = "1") @PathVariable Integer id)
					throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::delete");
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
	 * Find all entries by example.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@ApiOperation(value = "View a list of all available entries for resource example", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 302, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(path = BaseAdminConstants.CONTROLLER_POST_FIND_ALL_BY_EXAMPLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	@AuthorizedIfHaveRole
	public Iterable<R> findAllByExample(
			final @ApiParam(name = "resource", value = "Resource example", required = true, examples = @io.swagger.annotations.Example(value = @ExampleProperty(value = "{'name': 'Entity name', 'description': 'Entity description'}", mediaType = MediaType.APPLICATION_JSON_VALUE))) @RequestBody R resource)
					throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findAllByExample");
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
	 * @param resource   MusicalGenreResource object
	 * @param direction  Sorting direction values "asc" or "desc"
	 * @param properties List of properties
	 * 
	 * @return List<MusicalGenreResource> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@ApiOperation(value = "View a list of all available entries", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 302, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(path = BaseAdminConstants.CONTROLLER_POST_FIND_ALL_BY_EXAMPLE_SORTABLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	@AuthorizedIfHaveRole
	public Iterable<R> findAllByExampleSortable(
			final @ApiParam(name = "resource", value = "Entry object store in database table", required = true, examples = @io.swagger.annotations.Example(value = @ExampleProperty(value = "{'name': 'Entity name', 'description': 'Entity description'}", mediaType = MediaType.APPLICATION_JSON_VALUE))) @RequestBody R resource,
			final @ApiParam(name = "direction", value = "Direction for sort", type = "String", allowableValues = "ASC, DESC", required = true, example = "ASC") @PathVariable String direction,
			final @ApiParam(name = "properties", value = "List with property names", type = "List", required = true) @PathVariable List<String> properties)
					throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findAllByExampleSortable");
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
	 * Find by example
	 * 
	 * @param resource Entity to find.
	 * 
	 * @return T entity.
	 * 
	 * @throws NotFoundEntityException
	 */
	@ApiOperation(value = "Find a entry for resource example", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved entry"),
			@ApiResponse(code = 302, message = "Successfully retrieved try"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(path = BaseAdminConstants.CONTROLLER_GET_FIND_BY_EXAMPLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	@AuthorizedIfHaveRole
	public R findOneByExample(
			final @ApiParam(name = "resource", value = "Resource example", required = true, examples = @io.swagger.annotations.Example(value = @ExampleProperty(value = "{'name': 'Entity name', 'description': 'Entity description'}", mediaType = MediaType.APPLICATION_JSON_VALUE))) @RequestBody R resource)
					throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::findOneByExample");
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
	@Override
	public abstract BaseAdminService<T, R> getAdminService();

	/**
	 * Update entity.
	 * 
	 * @param entity Entity.
	 * @param id     Id.
	 * 
	 * @return T entity.
	 * 
	 * @throws NotIdMismatchEntityException
	 * @throws NotFoundEntityException
	 */
	@ApiOperation(value = "Update an entry", httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved entry"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PutMapping(path = BaseAdminConstants.CONTROLLER_PUT_UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@AuthorizedIfHaveRoleAdmin
	public R update(
			final @ApiParam(name = "id", value = "Entry id value", type = "Integer", required = true, example = "1") @PathVariable Integer id,
			final @ApiParam(name = "resource", value = "Entry object store in database table", required = true, examples = @io.swagger.annotations.Example(value = @ExampleProperty(value = "{'name': 'Entity name', 'description': 'Entity description'}", mediaType = MediaType.APPLICATION_JSON_VALUE))) @Valid @RequestBody R entity)
					throws NotIdMismatchEntityException, NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseAdminSimpleController::update");
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
