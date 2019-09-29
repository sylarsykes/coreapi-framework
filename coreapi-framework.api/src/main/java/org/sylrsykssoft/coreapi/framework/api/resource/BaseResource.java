package org.sylrsykssoft.coreapi.framework.api.resource;

import java.beans.ConstructorProperties;

import org.springframework.hateoas.ResourceSupport;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * DTO Base.
 * 
 * @param <N> The type of the identifier
 * @author juan.gonzalez.fernandez.jgf
 */
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString(includeFieldNames = true)
public class BaseResource<N extends Number> extends ResourceSupport {

	N entityId;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 */
	@Builder(builderMethodName = "baseResourceBuilder")
	@ConstructorProperties({ "entityId" })
	public BaseResource(final N entityId) {
		this.entityId = entityId;
	}
	
	/**
	 * Returns if the {@code Persistable} is new or was persisted already.
	 *
	 * @return if {@literal true} the object is new.
	 */
	public boolean isNew() {
		return null == getEntityId();
	}
}
