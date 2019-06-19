package org.sylrsykssoft.coreapi.framework.api.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO BaseEntity.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data()
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseEntityResource extends BaseResource {

	protected Long entityId;
}
