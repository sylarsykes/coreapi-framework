package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminSimpleRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseAdminSimpleService service.
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminSimpleService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource>
implements IAdminSimpleService<T, R, Integer>, IMapperFunction<T, R> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return getAdminRepository().count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R create(final R entity) {
		final T source = getAdminRepository().save(mapperToEntity().apply(entity));

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::create Result {}.", source);

		// Convert entity to resource
		return mapperToResource().toResource(source);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final R source) throws NotFoundEntityException {
		if (source.getEntityId() != null && !existsById(source.getEntityId()))
			throw new NotFoundEntityException();

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::delete Entity {} to delete.", source);

		final T entity = mapperToEntity().apply(source);
		getAdminRepository().delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::deleteAll.");

		getAdminRepository().deleteAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll(final Iterable<? extends R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false).map(mapperToEntity()::apply)
				.collect(Collectors.toList());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::deleteAll Delete all entities {}.", entities);

		getAdminRepository().deleteAll(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(final Integer id) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::deleteById Delete entity with id {}.", id);

		if (existsById(id)) {
			getAdminRepository().deleteById(id);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsById(final Integer id) {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::existsById Exists entity with id {}.", id);

		return getAdminRepository().existsById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAll() {
		final List<T> sources = getAdminRepository().findAll();

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleService::findAll Found {} entries.", sources);

		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(final Integer id) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleService::findById Finding a entry with id: {}", id);

		final Optional<T> source = getAdminRepository().findById(id);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleService::findById Result -> {}", source);

		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().toResource(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByName(final String name) throws NotFoundEntityException, IncorrectResultSizeException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleService::findByName Finding a entry with name: {}",
				name);

		final Optional<T> source = getAdminRepository().findByName(name);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleService::findByName Result -> {}", source);

		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().toResource(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminSimpleRepository<T> getAdminRepository();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R update(final R entity) throws NotFoundEntityException {
		if (entity.getEntityId() == null)
			throw new NotFoundEntityException();

		final T source = getAdminRepository().save(mapperToEntity().apply(entity));

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::update Result -> {}", source);

		// Convert entity to resource
		return mapperToResource().toResource(source);
	}

}
