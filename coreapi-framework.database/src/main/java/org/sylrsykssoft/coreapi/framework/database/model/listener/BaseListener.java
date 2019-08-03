package org.sylrsykssoft.coreapi.framework.database.model.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.sylrsykssoft.coreapi.framework.api.model.Base;

/**
 * Base entity entity listener.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class BaseListener {
	/**
	 * Assign createdAt
	 */
	@PrePersist
	@SuppressWarnings("rawtypes")
	void onPrePersist(final Base base) {
		final LocalDateTime dateTime = LocalDateTime.now();
		base.setCreatedAt(dateTime);
	}

	/**
	 * Assign updatedAt
	 */
	@PreUpdate
	@SuppressWarnings("rawtypes")
	void onPreUpdate(final Base base) {
		final LocalDateTime dateTime = LocalDateTime.now();
		base.setUpdatedAt(dateTime);
	}
}