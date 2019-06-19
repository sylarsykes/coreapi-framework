package org.sylrsykssoft.coreapi.framework.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;


/**
 * The Interface BaseAdminRepository.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> the generic type
 */
@NoRepositoryBean
@Transactional
public interface BaseAdminRepository<T extends BaseAdmin> extends JpaRepository<T, Integer> {
	
	/**
	 * Find by name.
	 * 
	 * @param name Value of the attribute name
	 * 
	 * @return T entity.
	 */
	Optional<T> findByName(final String name);
}