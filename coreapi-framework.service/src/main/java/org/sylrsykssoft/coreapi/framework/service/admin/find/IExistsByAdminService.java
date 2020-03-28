package org.sylrsykssoft.coreapi.framework.service.admin.find;

import org.springframework.data.domain.Example;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;

/**
 * IExistsByAdminService define services for check exists an entity AdminSimple.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface IExistsByAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource, N extends Number> {

	/**
	 * Checks whether the data store contains elements that match the given {@link Example}.
	 *
	 * @param example the {@link Example} to use for the existence check. Must not be {@literal null}.
	 * @return {@literal true} if the data store contains elements that match the given {@link Example}.
	 */
	<S extends T> boolean exists(Example<S> example);

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return boolean {@literal true} if an entity with the given id exists,
	 *         {@literal false} otherwise.
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	boolean existsById(N id);

}
