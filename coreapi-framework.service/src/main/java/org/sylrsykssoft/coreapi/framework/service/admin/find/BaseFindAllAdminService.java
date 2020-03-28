package org.sylrsykssoft.coreapi.framework.service.admin.find;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminSimpleRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseFindAllAdminService implementation of IFindAllAdminService
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Entity type class
 * @param <R> Resource type class
 * 
 * @see IFindAllAdminService
 * @see IMapperFunction
 */
public abstract class BaseFindAllAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource>
		implements IFindAllAdminService<T, R, Integer>, IMapperFunction<T, R> {

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
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminSimpleRepository<T> getAdminRepository();

}
