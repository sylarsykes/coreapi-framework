package org.sylrsykssoft.coreapi.framework.database.model.listener;

import java.time.LocalDateTime;

import javax.persistence.PreUpdate;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;

/**
 * Base entity entity listener.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class BaseAdminListener {
	/**
	 * Assign updatedAt
	 */
	@SuppressWarnings("exports")
	@PreUpdate
	public void onPreUpdate(final BaseAdmin base) {
		final LocalDateTime dateTime = LocalDateTime.now();
		base.setRemovedAt(dateTime);
	}
}