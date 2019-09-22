package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseAdminService service.
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminService<T extends BaseAdmin, R extends BaseAdminResource>
		extends BaseAdminSimpleService<T, R>
		implements IAdminService<T, R, Integer>, IAdminSimpleService<T, R, Integer>, IMapperFunction<T, R> {

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
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
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
	public List<R> findAllByExample(final Example<R> example) {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());

		final List<T> sources = getAdminRepository().findAll(exampleToFind);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findAllByExample Found {} entries.", sources);

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

		final List<T> sources = getAdminRepository().findAll(exampleToFind, sort);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findAllByExampleSortable Found {} entries.",
				sources);

		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllById(final Iterable<Integer> ids) {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findAllById Finding all ids: {}", ids);

		final List<T> sources = getAdminRepository().findAllById(ids);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findAllById Found {} entries.", sources);

		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByExample(final Example<R> example) throws NotFoundEntityException, IncorrectResultSizeException {
		final T entity = mapperToEntity().apply(example.getProbe());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findByExample Finding a entry with: {}", entity);

		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());

		final Optional<T> source = getAdminRepository().findOne(exampleToFind);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findByExample Result -> {}", source);

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
	@Override
	public abstract BaseAdminRepository<T> getAdminRepository();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> saveAll(final Iterable<R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
				.collect(Collectors.toList());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::saveAll Result -> {}", entities);

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

		final T source = getAdminRepository().save(mapperToEntity().apply(entity));

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::update Result -> {}", source);

		// Convert entity to resource
		return mapperToResource().toResource(source);
	}

}
