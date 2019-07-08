package org.sylrsykssoft.coreapi.framework.api.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO BaseEntity.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseEntityResource extends BaseResource<Long> {

}
