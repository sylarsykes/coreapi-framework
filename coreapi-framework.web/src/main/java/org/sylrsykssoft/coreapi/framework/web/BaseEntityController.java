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
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotIdMismatchEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiLibraryException;
import org.sylrsykssoft.coreapi.framework.library.mapper.ModelMapperFunction;
import org.sylrsykssoft.coreapi.framework.service.BaseEntityService;

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
public class BaseEntityController<R extends BaseEntityResource, T extends BaseEntity> {

	@Autowired()
	protected BaseEntityService<T, R> entityService;
	
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

		final Iterable<R> entities = entityService.findAll();
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
	public Optional<R> findOne(@PathVariable final Long id) throws NotFoundEntityException {
		LOGGER.info("BaseController:findOne Find entry with id {}", id);
		final Optional<R> entity = entityService.findById(id);

		if (entity == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseController:findOne Found {} entry.", entity);
		return entity;
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

		final R created = entityService.save(entity);

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
	public R update(@RequestBody final R entity, @PathVariable final Long id)
			throws NotIdMismatchEntityException, NotFoundEntityException {
		LOGGER.info("BaseController:update Updating a entry with id: {}", id);

		if (entity.getEntityId() != id) {
			throw new NotIdMismatchEntityException();
		}

		final Optional<R> old = entityService.findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		final R updated = entityService.save(entity);

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
	public void delete(@PathVariable final Long id) throws NotFoundEntityException, CoreApiLibraryException {
		LOGGER.info("BaseController:delete Deleting a entry with id: {}", id);

		final Optional<R> old = entityService.findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		try {
			entityService.deleteById(id);
		} catch (Exception e) {
			throw new CoreApiLibraryException(e);
		}
	}

	
}
