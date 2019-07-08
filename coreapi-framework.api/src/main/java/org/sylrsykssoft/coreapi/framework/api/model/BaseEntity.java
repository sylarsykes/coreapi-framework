package org.sylrsykssoft.coreapi.framework.api.model;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@MappedSuperclass
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseEntity extends Base<Long> {

}
