/**
 * Module info
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.api {
	exports org.sylrsykssoft.coreapi.framework.api.configuration;
	exports org.sylrsykssoft.coreapi.framework.api.model;
	exports org.sylrsykssoft.coreapi.framework.api.resource;

	requires java.desktop;
	requires java.persistence;

	requires coreapi.framework.library;
	requires coreapi.framework.swagger;

	requires lombok;

	requires spring.core;
	requires spring.context;
	requires spring.beans;
	requires spring.data.commons;
	requires org.hibernate.orm.core;
	requires spring.hateoas;

	// Swagger dependencies
	requires com.google.common;
	requires spring.plugin.core;
	requires springfox.swagger2;
	requires springfox.core;
	requires springfox.spi;
	requires swagger.annotations;
	requires transitive springfox.spring.web;
}