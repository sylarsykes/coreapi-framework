/**
 * Module info
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.service {
	exports org.sylrsykssoft.coreapi.framework.service;
	exports org.sylrsykssoft.coreapi.framework.service.admin;
	exports org.sylrsykssoft.coreapi.framework.service.admin.create;
	exports org.sylrsykssoft.coreapi.framework.service.admin.delete;
	exports org.sylrsykssoft.coreapi.framework.service.admin.find;
	exports org.sylrsykssoft.coreapi.framework.service.admin.update;

	requires coreapi.framework.library;
	requires coreapi.framework.api;
	requires coreapi.framework.database;

	requires lombok;
	requires transitive spring.data.commons;
	requires spring.data.jpa;
	requires spring.hateoas;
	requires spring.beans;
}