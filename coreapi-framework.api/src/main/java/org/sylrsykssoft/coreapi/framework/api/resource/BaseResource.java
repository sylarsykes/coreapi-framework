package org.sylrsykssoft.coreapi.framework.api.resource;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO Base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseResource<ID> extends ResourceSupport {

	protected ID entityId;
	protected LocalDateTime createdAt;
	protected @Nullable LocalDateTime updatedAt;
}
