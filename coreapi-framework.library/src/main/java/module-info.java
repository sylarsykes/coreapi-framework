/**
 * Module info
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.library {
	exports org.sylrsykssoft.coreapi.framework.library.configuration;
	exports org.sylrsykssoft.coreapi.framework.library.error;
	exports org.sylrsykssoft.coreapi.framework.library.error.exception;
	exports org.sylrsykssoft.coreapi.framework.library.mapper;
	exports org.sylrsykssoft.coreapi.framework.library.util;

	requires java.desktop;

	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires lombok;
	requires transitive modelmapper;
	requires transitive slf4j.api;
	requires transitive org.apache.commons.lang3;
	requires spring.beans;
	requires spring.context;
	requires spring.core;
	requires spring.hateoas;
	requires spring.web;
	requires descriptive.logger;
}