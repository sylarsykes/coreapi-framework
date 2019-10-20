package org.sylrsykssoft.coreapi.framework.audit.resource;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "BaseEntityAuditResource", description = "Base entity resource.", parent = BaseEntityResource.class)
public class BaseEntityAuditResource extends BaseEntityResource {

	@ApiModelProperty(name = "version", value = "The resource version value", dataType = "Integer", required = true)
	Integer version;

	@ApiModelProperty(name = "createdBy", value = "The user that created value", dataType = "String", required = true)
	String createdBy;

	@ApiModelProperty(name = "createdDate", value = "The date created value", dataType = "LocalDateTime", required = true)
	LocalDateTime createdDate;

	@ApiModelProperty(name = "lastModifiedBy", value = "The user that updated value", dataType = "String", required = true)
	String lastModifiedBy;

	@ApiModelProperty(name = "lastModifiedDate", value = "The date updated value", dataType = "LocalDateTime", required = true)
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
