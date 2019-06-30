package org.sylrsykssoft.coreapi.framework.api.resource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO BaseEntity.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter()
@Getter()
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseEntityResource extends BaseResource {

	protected Long entityId;
}
