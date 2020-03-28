package org.sylrsykssoft.coreapi.framework.service.admin.create;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseCreateAdminService implementation of services of ICreateAdminService.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see ICreateAdminService
 * @see IMapperFunction
 */
public abstract class BaseCreateAdminService<T extends BaseAdmin, R extends BaseAdminResource>
		implements ICreateAdminService<T, R, Integer>, IMapperFunction<T, R> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R create(final R entity) {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::create Entity for create {}.", entity);

		final T source = getAdminRepository().save(mapperToEntity().apply(entity));

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::create Result {}.", source);

		// Convert entity to resource
		return mapperToResource().toResource(source);
	}

	/**
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminRepository<T> getAdminRepository();

}
