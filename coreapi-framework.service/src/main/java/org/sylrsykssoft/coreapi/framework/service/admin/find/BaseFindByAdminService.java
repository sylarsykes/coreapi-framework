package org.sylrsykssoft.coreapi.framework.service.admin.find;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminSimpleRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseFindByAdminService implementation of IFindByAdminService.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see IFindByAdminService
 * @see IMapperFunction
 */
public abstract class BaseFindByAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource>
		implements IFindByAdminService<T, R, Integer>, IMapperFunction<T, R> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByExample(final Example<R> example)
			throws NotFoundEntityException, IncorrectResultSizeException {

		final T entity = mapperToEntity().apply(example.getProbe());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindByAdminService::findByExample Finding a entry with: {}",
				entity);

		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());

		final Optional<T> source = getAdminRepository().findOne(exampleToFind);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindByAdminService::findByExample Result -> {}", source);

		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().toResource(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(final Integer id) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindByAdminService::findById Finding a entry with id: {}", id);

		final Optional<T> source = getAdminRepository().findById(id);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindByAdminService::findById Result -> {}", source);

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
		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindByAdminService::findByName Finding a entry with name: {}",
				name);

		final Optional<T> source = getAdminRepository().findByName(name);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindByAdminService::findByName Result -> {}", source);

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
