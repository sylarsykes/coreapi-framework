package org.sylrsykssoft.coreapi.framework.security.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.sylrsykssoft.coreapi.framework.security.domain.BaseUser;

/**
 * The Interface BaseAdminSimpleRepository.
 * 
 * @see https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 * 
 * @param <T> the generic type
 * @param <N> the id type
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
public interface BaseUserRepository<T extends BaseUser<N>, N extends Number> extends JpaRepository<T, N> {

	/**
	 * Find a collection of user by name user
	 * 
	 * @param name String name user
	 * 
	 * @return Collection<BaseUser<N>>
	 */
	Collection<T> findByName(String name);

	/**
	 * Find user by username
	 * 
	 * @param username String username
	 * 
	 * @return BaseUser<N>
	 */
	T findByUsername(String username);

	/**
	 * Find user by username and enabled field
	 * 
	 * @param username String username
	 * @param enabled  boolean enabled
	 * 
	 * @return BaseUser<N>
	 */
	T findByUsernameAndEnabled(String username, boolean enabled);

}
