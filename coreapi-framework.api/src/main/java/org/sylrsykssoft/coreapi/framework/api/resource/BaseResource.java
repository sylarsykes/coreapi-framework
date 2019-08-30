package org.sylrsykssoft.coreapi.framework.api.resource;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * DTO Base.
 * 
 * @param <N> The type of the identifier
 * @author juan.gonzalez.fernandez.jgf
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString(includeFieldNames = true)
public class BaseResource<N extends Number> extends ResourceSupport {

	protected N entityId;
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
	public BaseResource(final N entityId, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
		this.entityId = entityId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
