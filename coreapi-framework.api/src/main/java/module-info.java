/**
 * Module info
 *  
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.api {
	exports org.sylrsykssoft.coreapi.framework.api.configuration;
	exports org.sylrsykssoft.coreapi.framework.api.resource;
	exports org.sylrsykssoft.coreapi.framework.api.model;

	requires coreapi.framework.library;
	requires java.desktop;
	requires java.persistence;
	requires lombok;
	requires org.hibernate.orm.core;
	requires spring.beans;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires spring.hateoas;
}