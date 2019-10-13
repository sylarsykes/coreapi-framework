/**
 * Module info
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.swagger {
	exports org.sylrsykssoft.coreapi.framework.swagger;
	exports org.sylrsykssoft.coreapi.framework.swagger.configuration;
	exports org.sylrsykssoft.coreapi.framework.swagger.configuration.property;

	requires lombok;

	requires spring.boot;
	requires spring.context;
	requires spring.web;

	// Swagger dependencies
	requires com.google.common;
	requires spring.plugin.core;
	requires springfox.swagger2;
	requires springfox.core;
	requires springfox.spi;
	requires swagger.annotations;
	requires transitive springfox.spring.web;
}