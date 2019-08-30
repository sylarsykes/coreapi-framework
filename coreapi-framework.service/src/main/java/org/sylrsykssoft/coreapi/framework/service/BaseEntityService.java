package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseEntityRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseService service.
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseEntityService<T extends BaseEntity, R extends BaseEntityResource> implements IEntityService<T, R, Long>, IMapperFunction<T, R> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return getEntityRepository().count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R create(final R entity) throws NotFoundEntityException {
		final T source = getEntityRepository().save(mapperToEntity().apply(entity));

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::create Result {}.", source);

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

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::delete Entity {} to delete.", source);

		final T entity = mapperToEntity().apply(source);
		getEntityRepository().delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::deleteAll.");

		getEntityRepository().deleteAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll(final Iterable<? extends R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
				.collect(Collectors.toList());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::deleteAll Delete all entities {}.", entities);

		getEntityRepository().deleteAll(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(final Long id) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::deleteById Delete entity with id {}.", id);

		if (existsById(id)) {
			getEntityRepository().deleteById(id);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsById(final Long id) {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::existsById Exists entity with id {}.", id);

		return getEntityRepository().existsById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAll() {
		final List<T> sources = getEntityRepository().findAll();

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findAll Found {} entries.", sources);

		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllByExample(final Example<R> example) {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());

		final List<T> sources = getEntityRepository().findAll(exampleToFind);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findAllByExample Found {} entries.", sources);

		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllByExampleSortable(final Example<R> example, final Sort sort) {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());

		final List<T> sources = getEntityRepository().findAll(exampleToFind, sort);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findAllByExampleSortable Found {} entries.",
				sources);

		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllById(final Iterable<Long> ids) {
		final List<T> sources = getEntityRepository().findAllById(ids);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findAllById Found {} entries.", sources);

		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByExample(final Example<R> example) throws NotFoundEntityException, IncorrectResultSizeException {
		final T entity = mapperToEntity().apply(example.getProbe());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findByExample Finding a entry with: {}", entity);

		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());

		final Optional<T> source = getEntityRepository().findOne(exampleToFind);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findByExample Result -> {}", source);

		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().toResource(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(final Long id) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findById Finding a entry with id: {}", id);

		final Optional<T> source = getEntityRepository().findById(id);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::findById Result -> {}", source);

		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().toResource(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * Getter repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseEntityRepository<T> getEntityRepository();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> saveAll(final Iterable<R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
				.collect(Collectors.toList());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::saveAll Result -> {}", entities);

		// Convert entity to resource
		return StreamSupport.stream(entities.spliterator(), false)
				.map(mapperToResource()::toResource)
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R update(final R entity) throws NotFoundEntityException {
		if (entity.getEntityId() == null)
			throw new NotFoundEntityException();

		final T source = getEntityRepository().save(mapperToEntity().apply(entity));

		LoggerUtil.message(LogMessageLevel.INFO, "BaseEntityService::update Result -> {}", source);

		// Convert entity to resource
		return mapperToResource().toResource(source);
	}

}
