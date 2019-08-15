/**
 * Module info
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.web {
	exports org.sylrsykssoft.coreapi.framework.web;
	exports org.sylrsykssoft.coreapi.framework.web.configuration;
	exports org.sylrsykssoft.coreapi.framework.web.resource.assembler;

	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.library;
	requires coreapi.framework.service;
	requires java.desktop;
	requires java.validation;
	requires lombok;
	requires slf4j.api;
	requires spring.beans;
	requires transitive spring.boot;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires spring.hateoas;
	requires transitive spring.web;
}