package org.sylrsykssoft.coreapi.framework.api.resource;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO Base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseResource<ID> extends ResourceSupport {

	protected ID entityId;
	protected LocalDateTime createdAt;
	protected @Nullable LocalDateTime updatedAt;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param createdAt
	 * @param updatedAt
	 */
	@Builder(builderMethodName = "baseResourceBuilder")
	public BaseResource(final ID entityId, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
		this.entityId = entityId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
