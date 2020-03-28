package org.sylrsykssoft.coreapi.framework.service.admin;

import org.springframework.data.domain.Example;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminSimpleRepository;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseCountAdminService implementation of services of ICountAdminService
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see ICountAdminService
 * 
 */
public abstract class BaseCountAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource>
		implements ICountAdminService<T, R, Integer> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseCountAdminService::count Count number of entities.");

		return getAdminRepository().count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S extends T> long count(final Example<S> example) {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseCountAdminService::count Count number of entities by example {}.",
				example);

		return getAdminRepository().count(example);
	}

	/**
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminSimpleRepository<T> getAdminRepository();

}
