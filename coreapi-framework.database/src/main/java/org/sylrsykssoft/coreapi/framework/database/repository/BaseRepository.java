package org.sylrsykssoft.coreapi.framework.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.sylrsykssoft.coreapi.framework.api.model.Base;


/**
 * The Interface BaseRepository.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 */
@NoRepositoryBean
@Transactional
public interface BaseRepository<T extends Base<ID>, ID extends Number> extends JpaRepository<T, ID> {

}
