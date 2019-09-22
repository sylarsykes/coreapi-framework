package org.sylrsykssoft.coreapi.framework.web;

import java.util.Optional;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;
import org.sylrsykssoft.coreapi.framework.service.BaseAdminSimpleService;
import org.sylrsykssoft.coreapi.framework.web.configuration.BaseAdminConstants;

/**
 * Base admin controller
 * 
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminSimpleController<R extends BaseAdminSimpleResource, T extends BaseAdminSimple> {

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
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleController::findAll Finding all entries");

		final Iterable<R> entities = getAdminService().findAll();
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminSimpleController::findAll not find result");
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleController::findAll Found {} entries.", entities);

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
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleController::findById Finding a entry with id: {}", id);

		final Optional<R> result = getAdminService().findById(id);

		if (!result.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminSimpleController::findById not find result for id -> {}",
					id);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleController::findById Result -> {}", result.get());
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
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleController::findByName Finding a entry with name: {}",
				name);

		final Optional<R> result = getAdminService().findByName(name);

		if (!result.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminSimpleController::findByName not find result for name -> {}",
					name);
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleController::findByName Result -> {}", result.get());

		return result.get();
	}

	/**
	 * Getter admin service implementation
	 * 
	 * @return BaseAdminService<T, R>
	 */
	public abstract BaseAdminSimpleService<T, R> getAdminService();

}
