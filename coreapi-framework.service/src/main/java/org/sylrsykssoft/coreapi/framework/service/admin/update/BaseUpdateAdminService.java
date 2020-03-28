package org.sylrsykssoft.coreapi.framework.service.admin.update;

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
 * BaseUpdateAdminService implementation of services of IUpdateAdminService
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see IUpdateAdminService
 * @see IMapperFunction
 */
public abstract class BaseUpdateAdminService<T extends BaseAdmin, R extends BaseAdminResource>
		implements IUpdateAdminService<T, R, Integer>, IMapperFunction<T, R> {

	@Autowired
	private IExistsByAdminService<T, R, Integer> existsByAdminService;

	/**
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminRepository<T> getAdminRepository();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R update(final R entity) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseUpdateAdminService::update Entity for update {}.", entity);

		if (entity.getEntityId() == null || !existsByAdminService.existsById(entity.getEntityId()))
			throw new NotFoundEntityException();

		final T source = getAdminRepository().save(mapperToEntity().apply(entity));

		LoggerUtil.message(LogMessageLevel.INFO, "BaseUpdateAdminService::update Result {}.", source);

		// Convert entity to resource
		return mapperToResource().toResource(source);
	}

}
