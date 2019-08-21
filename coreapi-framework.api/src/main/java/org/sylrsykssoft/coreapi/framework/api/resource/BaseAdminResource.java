package org.sylrsykssoft.coreapi.framework.api.resource;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * DTO BaseAdmin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseAdminResource extends BaseResource<Integer> {

	protected @NonNull String name;
	protected @NonNull String description;
	protected @Nullable LocalDateTime removedAt;

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
		this.entityId = entityId;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.removedAt = removedAt;
	}

}
