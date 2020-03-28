package org.sylrsykssoft.coreapi.framework.service.admin.create;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseSaveAllAdminService implementation of services of ISaveAllAdminService.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see ISaveAllAdminService
 * @see IMapperFunction
 * 
 */
public abstract class BaseSaveAllAdminService<T extends BaseAdmin, R extends BaseAdminResource>
		implements ISaveAllAdminService<T, R, Integer>, IMapperFunction<T, R> {

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
	public List<R> saveAll(final Iterable<R> sources) throws NotFoundEntityException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminService::saveAll Save all entities -> {}", sources);

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
