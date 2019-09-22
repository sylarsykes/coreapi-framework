package org.sylrsykssoft.coreapi.framework.database.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;

/**
 * The Interface BaseEntityRepository.
 *
 * @param <T> the generic type
 */
@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends BaseRepository<T, Long> {

}
