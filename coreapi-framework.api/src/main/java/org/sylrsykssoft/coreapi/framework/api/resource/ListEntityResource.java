package org.sylrsykssoft.coreapi.framework.api.resource;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple list of BaseEntityResource
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Getter
@Setter
public class ListEntityResource<R extends BaseEntityResource> {
	private List<R> adminResources;
}
