package org.sylrsykssoft.coreapi.framework.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotIdMismatchEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
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
@Slf4j
public abstract class BaseAdminController<R extends BaseAdminResource, T extends BaseAdmin> {

	@Autowired
	protected BaseAdminService<T, R> adminService;
	
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
	public R findOne(final Integer id) throws NotFoundEntityException {
		LOGGER.info("BaseAdminController:findOne Find entry with id {}", id);
		final R result = adminService.getOne(id);

		LOGGER.info("BaseAdminController:findOne Found {} entry.", result);
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
	public R findById(final Integer id) throws NotFoundEntityException {
		LOGGER.info("BaseAdminController::findOne Finding a entry with id: {}", id);
		
		final Optional<R> result = adminService.findById(id);
		
		LOGGER.info("BaseAdminController::findOne Result -> {}", result.get());
		
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
	public @ResponseBody R findOneByExample(final R resource) {
		LOGGER.info("BaseAdminController::findOne Finding a entry with id: {}", resource);
		
		Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Optional<R> result = adminService.findByExample(example);
		
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
	public Iterable<R> findAll() throws NotFoundEntityException {
		LOGGER.info("BaseAdminController:findAll Finding all entries");

		final Iterable<R> entities = adminService.findAll();
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
	public Iterable<R> findAllByExample(final R resource) throws NotFoundEntityException {
		LOGGER.info("BaseAdminController:findAll Finding all entries");

		final Example<R> example = Example.of(resource, ExampleMatcher.matchingAll());
		
		final Iterable<R> entities = adminService.findAllByExample(example);
		if (entities == null) {
			throw new NotFoundEntityException();
		}

		LOGGER.info("BaseAdminController:findAll Found {} entries.", entities);

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
		LOGGER.info("BaseAdminController:create Creating a new todo entry by using information: {}", entity);

		final R created = adminService.save(entity);

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
	public R update(final R entity, final Integer id) throws NotIdMismatchEntityException, NotFoundEntityException {
		LOGGER.info("BaseAdminController:update Updating a entry with id: {}", id);

		if (entity.getEntityId() != id) {
			throw new NotIdMismatchEntityException();
		}

		final Optional<R> old = adminService.findById(id);
		if (old == null) {
			throw new NotFoundEntityException();
		}

		final R updated = adminService.save(entity);

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
	public void delete(final Integer id) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminController:delete Deleting a entry with id: {}", id);

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
