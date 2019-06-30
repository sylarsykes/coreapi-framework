package org.sylrsykssoft.coreapi.framework.api.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data()
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter()
@Getter()
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseEntity extends Base {

	protected Long entityId;
	
}
