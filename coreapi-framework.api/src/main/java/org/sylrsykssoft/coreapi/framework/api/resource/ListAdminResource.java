package org.sylrsykssoft.coreapi.framework.api.resource;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple list of BaseAdminResource
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Getter
@Setter
public class ListAdminResource<R extends BaseAdminResource> {
	private List<R> adminResources;
}
