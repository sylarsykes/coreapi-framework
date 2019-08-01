package org.sylrsykssoft.coreapi.framework.api.model;

import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@MappedSuperclass
@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseEntity extends Base<Long> {

}
