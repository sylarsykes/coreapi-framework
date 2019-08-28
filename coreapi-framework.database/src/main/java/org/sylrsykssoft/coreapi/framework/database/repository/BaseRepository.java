package org.sylrsykssoft.coreapi.framework.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.sylrsykssoft.coreapi.framework.api.model.Base;


/**
 * The Interface BaseRepository.
 * 
 * @param <T> The type of object that extends Base
 * @param <N> The type of the identifier
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
@Transactional
public interface BaseRepository<T extends Base<N>, N extends Number> extends JpaRepository<T, N> {

}
