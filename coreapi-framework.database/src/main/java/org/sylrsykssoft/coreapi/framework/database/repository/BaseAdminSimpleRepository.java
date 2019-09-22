package org.sylrsykssoft.coreapi.framework.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;

/**
 * The Interface BaseAdminRepository.
 * 
 * @param <T> the generic type
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
public interface BaseAdminSimpleRepository<T extends BaseAdminSimple> extends Repository<T, Integer> {

	/**
	 * Returns the number of entities available.
	 *
	 * @return the number of entities
	 */
	long count();

	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	List<T> findAll();

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none
	 *         found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
	Optional<T> findById(Integer id);

	/**
	 * Find by name.
	 * 
	 * @param name Value of the attribute name
	 * 
	 * @return T entity.
	 */
	@Query("SELECT e FROM #{#entityName} e WHERE e.name = :name")
	Optional<T> findByName(@Param("name") String name);

}
