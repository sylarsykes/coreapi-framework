package org.sylrsykssoft.coreapi.framework.service.admin.find;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;

/**
 * IFindAllAdminService define find services.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <N> Class of identifier.
 */
public interface IFindAllAdminService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource, N extends Number> {

	/**
	 * Returns all instances of the type.
	 * 
	 * @return List<R> all entities
	 */
	List<R> findAll();

	/**
	 * Returns all entities matching the given {@link Example}. In case no match
	 * could be found an empty {@link Iterable} is returned.
	 *
	 * @param example must not be {@literal null}.
	 * @return List<R> all entities matching the given {@link Example}.
	 */
	List<R> findAllByExample(Example<R> example);

	/**
	 * Returns all entities matching the given {@link Example} applying the given
	 * {@link Sort}. In case no match could be found an empty {@link Iterable} is
	 * returned.
	 *
	 * @param example must not be {@literal null}.
	 * @param sort    the {@link Sort} specification to sort the results by, must
	 *                not be {@literal null}.
	 * @return List<R> all entities matching the given {@link Example}.
	 */
	List<R> findAllByExampleSortable(Example<R> example, Sort sort);

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return List<R>
	 */
	List<R> findAllById(Iterable<N> ids);
}
