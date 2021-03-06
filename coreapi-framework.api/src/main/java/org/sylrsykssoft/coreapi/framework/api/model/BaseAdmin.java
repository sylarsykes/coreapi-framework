package org.sylrsykssoft.coreapi.framework.api.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.Nullable;

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
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseAdmin extends BaseAdminSimple {

	public static final int MAX_LENGTH_DESCRIPTION = 10000;

	@Column(name = "description", nullable = true, columnDefinition = "TEXT", length = MAX_LENGTH_DESCRIPTION)
	@Nullable
	String description;

}
