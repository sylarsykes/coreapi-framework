package org.sylrsykssoft.coreapi.framework.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotIdMismatchEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;
import org.sylrsykssoft.coreapi.framework.service.BaseAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * Base admin controller
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <R> Resource class
 * @param <T> Admin class
 */
@Slf4j()
public class BaseAdminController<R extends BaseAdminResource, T extends BaseAdmin> {

	@Autowired()
	protected BaseAdminService<T, R> adminService;
	
	@Autowired
	protected ModelMapperFunction<T, R> modelMapperFunction;

	
	/**
	 * Find all entries.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@GetMapping("")
	public Iterable<R> findAll() throws NotFoundEntityException {
		LOGGER.info("BaseController:findAll Finding all entries");

		final Iterable<R> entities = adminService.findAll();
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseController:findAll Found {} entries.", entities);

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
	@GetMapping("/{id}")
	public R findOne(@PathVariable final Integer id) throws NotFoundEntityException {
		LOGGER.info("BaseController:findOne Find entry with id {}", id);
		final Optional<R> entity = adminService.findById(id);

		LOGGER.info("BaseController:findOne Found {} entry.", entity);
		return entity.get();
	}

	/**
	 * Create entry.
	 * 
	 * @param entity
	 *            Entity.
	 * 
	 * @return T entity.
	 */
	@PutMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public R create(@RequestBody final R entity) {
		LOGGER.info("BaseController:create Creating a new todo entry by using information: {}", entity);

		final R created = adminService.save(entity);

		LOGGER.info("BaseController:create Created a new todo entry: {}", created);

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
	@PutMapping("/{id}")
	public R update(@RequestBody final R entity, @PathVariable final Integer id) throws NotIdMismatchEntityException, NotFoundEntityException {
		LOGGER.info("BaseController:update Updating a entry with id: {}", id);

		if (entity.getEntityId() != id) {
			throw new NotIdMismatchEntityException();
		}

		final Optional<R> old = adminService.findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		final R updated = adminService.save(entity);

		LOGGER.info("BaseController:update Updated the entry: {}", updated);

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
	@DeleteMapping("/{id}")
	public void delete(@PathVariable final Integer id) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseController:delete Deleting a entry with id: {}", id);

		final Optional<R> old = adminService.findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		try {
			adminService.deleteById(id);
		} catch (Exception e) {
			throw new CoreApiFrameworkLibraryException(e);
		}
	}

}
