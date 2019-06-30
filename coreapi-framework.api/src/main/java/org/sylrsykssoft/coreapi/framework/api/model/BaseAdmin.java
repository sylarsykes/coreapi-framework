package org.sylrsykssoft.coreapi.framework.api.model;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/***
 * Entity admin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseAdmin extends Base {

	protected Integer adminId;
	protected @NonNull String name;
	protected @Nullable String description;
	
}
