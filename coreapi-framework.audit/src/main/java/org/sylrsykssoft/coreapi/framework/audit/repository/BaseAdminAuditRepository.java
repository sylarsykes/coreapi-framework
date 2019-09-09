package org.sylrsykssoft.coreapi.framework.audit.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminRepository;

/**
 * The Interface BaseAdminAuditRepository.
 * 
 * @param <T> the generic type
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
public interface BaseAdminAuditRepository<T extends BaseAdmin>
		extends RevisionRepository<T, Integer, Integer>, BaseAdminRepository<T> {

}
