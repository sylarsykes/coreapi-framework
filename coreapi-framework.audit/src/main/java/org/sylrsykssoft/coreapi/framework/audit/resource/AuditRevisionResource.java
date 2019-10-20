package org.sylrsykssoft.coreapi.framework.audit.resource;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Audit revision.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
@ApiModel(value = "AuditRevisionResource", description = "Audit revision resource.")
public class AuditRevisionResource implements Serializable {

	private static final long serialVersionUID = 1049095879642363065L;

	@ApiModelProperty(name = "id", value = "The rrevision audit id value", dataType = "int", required = true)
	int id;

	@ApiModelProperty(name = "timestanp", value = "The timestanp audit value", dataType = "long", required = true)
	long timestamp;

	@ApiModelProperty(name = "username", value = "The username audit value", dataType = "String", required = true)
	@NonNull
	String username;

}
