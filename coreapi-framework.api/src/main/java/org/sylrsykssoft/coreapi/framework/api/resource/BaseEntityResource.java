package org.sylrsykssoft.coreapi.framework.api.resource;

import java.beans.ConstructorProperties;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTO BaseEntity.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
@ApiModel(value = "BaseEntityResource", description = "Base entity resource.", parent = BaseResource.class)
public class BaseEntityResource extends BaseResource<Long> {

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param createdAt
	 * @param updatedAt
	 */
	@Builder(builderMethodName = "baseEntityResourceBuilder")
	@ConstructorProperties({ "entityId" })
	public BaseEntityResource(final Long entityId) {
		super(entityId);
	}

}
