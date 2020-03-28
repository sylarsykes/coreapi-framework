package org.sylrsykssoft.coreapi.framework.service.admin;

import org.springframework.data.domain.Example;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;

/**
 * ICountAdminService count Service.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface ICountAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource, N extends Number> {

	/**
	 * Returns the number of entities available.
	 * 
	 * @return long the number of entities
	 */
	long count();

	/**
	 * Returns the number of instances matching the given {@link Example}.
	 *
	 * @param example the {@link Example} to count instances for. Must not be
	 *                {@literal null}.
	 * @return the number of instances matching the {@link Example}.
	 */
	<S extends T> long count(Example<S> example);
}
