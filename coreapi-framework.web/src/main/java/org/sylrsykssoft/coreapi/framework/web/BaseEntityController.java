package org.sylrsykssoft.coreapi.framework.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotIdMismatchEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
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
@Slf4j
public abstract class BaseEntityController<R extends BaseEntityResource, T extends BaseEntity> {

	@Autowired
	protected BaseEntityService<T, R> entityService;

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
	public R findOne(final Long id) throws NotFoundEntityException {
		LOGGER.info("BaseController:findOne Find entry with id {}", id);
		final R result = entityService.getOne(id);

		LOGGER.info("BaseController:findOne Found {} entry.", result);
		return result;
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
	public @ResponseBody R findById(final Long id) throws NotFoundEntityException {
		LOGGER.info("BaseController::findOne Finding a entry with id: {}", id);
		
		final Optional<R> result = entityService.findById(id);
		
		LOGGER.info("BaseController::findOne Result -> {}", result.get());
		
		return result.get();
	}
	
	/**
	 * Find all entries.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
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
	 * Find all entries by example.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	public Iterable<R> findAllByExample(final Example<R> example) throws NotFoundEntityException {
		LOGGER.info("BaseController:findAll Finding all entries");

		final Iterable<R> entities = entityService.findAllByExample(example);
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseController:findAll Found {} entries.", entities);

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
	public R create(final R entity) {
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
	public R update(final R entity, final Long id) throws NotIdMismatchEntityException, NotFoundEntityException {
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
	public void delete(final Long id) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseController:delete Deleting a entry with id: {}", id);

		final Optional<R> old = entityService.findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		try {
			entityService.deleteById(id);
		} catch (Exception e) {
			throw new CoreApiFrameworkLibraryException(e);
		}
	}

	
}
