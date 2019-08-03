package org.sylrsykssoft.coreapi.framework.database.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;

/**
 * The Interface BaseEntityRepository.
 *
 * @param <T> the generic type
 */
@NoRepositoryBean
@Transactional
public interface BaseEntityRepository<T extends BaseEntity> extends BaseRepository<T, Long> {

}
