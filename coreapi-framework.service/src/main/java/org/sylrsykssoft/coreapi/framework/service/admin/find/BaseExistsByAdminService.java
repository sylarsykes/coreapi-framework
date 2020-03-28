package org.sylrsykssoft.coreapi.framework.service.admin.find;

import org.springframework.data.domain.Example;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminSimpleRepository;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseExistsByAdminService implementation of IExistsByAdminService
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see IExistsByAdminService
 */
public abstract class BaseExistsByAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource>
		implements IExistsByAdminService<T, R, Integer> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S extends T> boolean exists(final Example<S> example) {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::existsById Exists entity with example {}.", example);

		return getAdminRepository().exists(example);
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
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminSimpleRepository<T> getAdminRepository();

}
