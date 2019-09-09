package org.sylrsykssoft.coreapi.framework.audit.configuration;

import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.AUDITORAWARE_DEFAULT_CURRENT_EDITOR_NAME;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * Default auditor aware implementation
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class CoreApiFrameworkAuditAuditorAwareImpl implements AuditorAware<String> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(AUDITORAWARE_DEFAULT_CURRENT_EDITOR_NAME);
	}

}
