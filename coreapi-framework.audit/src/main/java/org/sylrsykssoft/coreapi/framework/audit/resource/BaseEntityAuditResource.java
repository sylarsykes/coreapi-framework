package org.sylrsykssoft.coreapi.framework.audit.resource;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseEntityAuditResource extends BaseEntityResource {

	Integer version;
	String createdBy;
	LocalDateTime createdDate;
	String lastModifiedBy;
	LocalDateTime lastModifiedDate;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param name
	 * @param description
	 * @param createdAt
	 * @param updatedAt
	 */
	@Builder(builderMethodName = "baseEntityAuditResourceBuilder")
	@ConstructorProperties({ "entityId", "name", "version", "createdBy", "createdDate", "lastModifiedBy",
			"lastModifiedDate" })
	public BaseEntityAuditResource(final Long entityId, final Integer version, final String createdBy,
			final LocalDateTime createdDate, final String lastModifiedBy, final LocalDateTime lastModifiedDate) {
		super(entityId);

		this.version = version;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

}
