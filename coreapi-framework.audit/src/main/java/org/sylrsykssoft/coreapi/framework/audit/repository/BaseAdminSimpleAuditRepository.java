package org.sylrsykssoft.coreapi.framework.audit.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminSimpleRepository;

/**
 * The Interface BaseAdminSimpleAuditRepository.
 * 
 * @param <T> the generic type
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
public interface BaseAdminSimpleAuditRepository<T extends BaseAdminSimple>
		extends RevisionRepository<T, Integer, Integer>, BaseAdminSimpleRepository<T> {

}
