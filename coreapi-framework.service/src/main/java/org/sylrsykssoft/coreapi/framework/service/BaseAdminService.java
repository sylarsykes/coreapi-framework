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
		implements IAdminSimpleService<T, R, Integer>, IAdminService<T, R, Integer>, IMapperFunction<T, R> {

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

}
