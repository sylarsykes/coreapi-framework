package org.sylrsykssoft.coreapi.framework.audit.domain.listener;

import org.hibernate.envers.RevisionListener;
import org.sylrsykssoft.coreapi.framework.audit.domain.AuditRevision;

/**
 * @author Juan
 *
 */
public class AuditRevisionListener implements RevisionListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newRevision(final Object revisionEntity) {
		final AuditRevision auditRevision = (AuditRevision) revisionEntity;

		auditRevision.setUsername("musbands.admin");

	}

}
