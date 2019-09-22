package org.sylrsykssoft.coreapi.framework.api.resource;

import java.beans.ConstructorProperties;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * DTO BaseAdmin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseAdminResource extends BaseAdminSimpleResource {

	@Nullable
	String description;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param name
	 */
	@Builder(builderMethodName = "baseAdminResourceBuilder")
	@ConstructorProperties({ "entityId", "name", "description" })
	public BaseAdminResource(final Integer entityId, final String name, final String description) {
		super(entityId, name);

		this.description = description;
	}

}
