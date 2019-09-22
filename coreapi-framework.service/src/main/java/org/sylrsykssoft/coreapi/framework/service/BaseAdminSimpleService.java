package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminSimpleRepository;
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
	public List<R> findAll() {
		final List<T> sources = getAdminRepository().findAll();

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findAll Found {} entries.", sources);

		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(final Integer id) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findById Finding a entry with id: {}", id);

		final Optional<T> source = getAdminRepository().findById(id);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findById Result -> {}", source);

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
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findByName Finding a entry with name: {}", name);

		final Optional<T> source = getAdminRepository().findByName(name);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::findByName Result -> {}", source);

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

}
