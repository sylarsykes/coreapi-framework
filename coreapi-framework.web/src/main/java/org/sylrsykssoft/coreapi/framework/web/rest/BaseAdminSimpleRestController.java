package org.sylrsykssoft.coreapi.framework.web.rest;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;

/**
 * Base admin simple rest template controller
 * 
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminSimpleRestController<R extends BaseAdminSimpleResource, T extends BaseAdminSimple> {

	/**
	 * Get SimpleRestController bean
	 * 
	 * @return BaseAdminSimpleRestTemplateController<R, T>
	 */
	public abstract BaseAdminSimpleRestTemplateController<R, T> getRestTemplateController();
}
