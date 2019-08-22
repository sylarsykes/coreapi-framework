package org.sylrsykssoft.coreapi.framework.api.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Entity translatable
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseTranslate {

	protected @NonNull String locale;

}
