package org.sylrsykssoft.coreapi.framework.audit.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/***
 * Entity admin
 * 
 * @see org.sylrsykssoft.coreapi.framework.api.model.
 * @author juan.gonzalez.fernandez.jgf
 */
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseAdminSimpleAudit extends BaseAdminSimple {

	@Version
	Integer version;

	@Column(name = "created_by", nullable = false, insertable = true, updatable = false, length = 60)
	@CreatedBy
	String createdBy;

	@Column(name = "created_date", nullable = false, insertable = true, updatable = false)
	@CreatedDate
	LocalDateTime createdDate;

	@Column(name = "last_modify_by", nullable = true, insertable = false, updatable = true, length = 60)
	@LastModifiedBy
	String lastModifiedBy;

	@Column(name = "last_modified_date", nullable = true, insertable = false, updatable = true)
	@Nullable
	@LastModifiedDate
	LocalDateTime lastModifiedDate;
}
