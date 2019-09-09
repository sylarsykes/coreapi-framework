package org.sylrsykssoft.coreapi.framework.audit.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseEntityRepository;

/**
 * The Interface BaseEntityAuditRepository.
 * 
 * @param <T> the generic type
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
public interface BaseEntityAuditRepository<T extends BaseEntity>
		extends RevisionRepository<T, Long, Integer>, BaseEntityRepository<T> {
}
