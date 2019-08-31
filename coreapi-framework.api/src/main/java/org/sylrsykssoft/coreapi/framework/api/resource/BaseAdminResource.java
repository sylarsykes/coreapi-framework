package org.sylrsykssoft.coreapi.framework.api.resource;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseAdminResource extends BaseResource<Integer> {

	@NonNull
	String name;
	@Nullable
	String description;
	@Nullable
	LocalDateTime removedAt;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param name
	 * @param description
	 * @param createdAt
	 * @param updatedAt
	 */
	@Builder(builderMethodName = "baseAdminResourceBuilder")
	@ConstructorProperties({ "entityId", "name", "description", "createdAt", "updatedAt", "removedAt" })
	public BaseAdminResource(final Integer entityId, final String name, final String description, final LocalDateTime createdAt, final LocalDateTime updatedAt, final LocalDateTime removedAt) {
		super(entityId, createdAt, updatedAt);
		this.name = name;
		this.description = description;
		this.removedAt = removedAt;
	}

}
