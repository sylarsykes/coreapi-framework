package org.sylrsykssoft.coreapi.framework.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;

/**
 * The Interface BaseAdminSimpleRepository.
 * 
 * @param <T> the generic type
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
public interface BaseAdminSimpleRepository<T extends BaseAdminSimple> extends JpaRepository<T, Integer> {

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
