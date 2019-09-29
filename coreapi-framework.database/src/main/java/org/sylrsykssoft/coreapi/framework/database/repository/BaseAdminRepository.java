package org.sylrsykssoft.coreapi.framework.database.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;

/**
 * The Interface BaseAdminRepository.
 * 
 * @param <T> the generic type
 * @author juan.gonzalez.fernandez.jgf
 */
@NoRepositoryBean
public interface BaseAdminRepository<T extends BaseAdmin>
extends BaseAdminSimpleRepository<T> {

}
