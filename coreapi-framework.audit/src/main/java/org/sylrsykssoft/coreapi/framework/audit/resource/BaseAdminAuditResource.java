package org.sylrsykssoft.coreapi.framework.audit.resource;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/***
 * Entity admin
 * 
 * @see org.sylrsykssoft.coreapi.framework.api.model.
 * @author juan.gonzalez.fernandez.jgf
 */
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseAdminAuditResource extends BaseAdminResource {

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
	@Builder(builderMethodName = "baseAdminAuditResourceBuilder")
	@ConstructorProperties({ "entityId", "name", "description", "version", "createdBy", "createdDate", "lastModifiedBy",
	"lastModifiedDate" })
	public BaseAdminAuditResource(final Integer entityId, final String name, final String description,
			final Integer version, final String createdBy,
			final LocalDateTime createdDate, final String lastModifiedBy, final LocalDateTime lastModifiedDate) {
		super(entityId, name, description);

		this.version = version;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;

	}

}
