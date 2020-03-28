package org.sylrsykssoft.coreapi.framework.service.admin.delete;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;
import org.sylrsykssoft.coreapi.framework.service.admin.find.IExistsByAdminService;

/**
 * BaseDeleteAdminService implementation of services of IDeleteAdminService.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see IDeleteAdminService
 * @see IMapperFunction
 */
public abstract class BaseDeleteAdminService<T extends BaseAdmin, R extends BaseAdminResource>
		implements IDeleteAdminService<T, R, Integer>, IMapperFunction<T, R> {

	@Autowired
	protected IExistsByAdminService<T, R, Integer> existsByService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final R source) throws NotFoundEntityException {
		if (source.getEntityId() == null || !existsByService.existsById(source.getEntityId()))
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

		if (!existsByService.existsById(id))
			throw new NotFoundEntityException();

		getAdminRepository().deleteById(id);
	}

	/**
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminRepository<T> getAdminRepository();

}
