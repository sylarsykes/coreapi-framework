package org.sylrsykssoft.coreapi.framework.api.resource;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseAdminSimpleResource extends BaseResource<Integer> {

	@NonNull
	String name;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param name
	 * @param description
	 * @param createdAt
	 * @param updatedAt
	 */
	@Builder(builderMethodName = "baseAdminSimpleResourceBuilder")
	@ConstructorProperties({ "entityId", "name", "createdAt", "updatedAt", })
	public BaseAdminSimpleResource(final Integer entityId, final String name, final LocalDateTime createdAt,
			final LocalDateTime updatedAt) {
		super(entityId, createdAt, updatedAt);

		this.name = name;
	}

}
