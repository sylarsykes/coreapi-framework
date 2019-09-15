module coreapi.framework.web {
	exports org.sylrsykssoft.coreapi.framework.web;
	exports org.sylrsykssoft.coreapi.framework.web.configuration;
	exports org.sylrsykssoft.coreapi.framework.web.resource.assembler;

	requires coreapi.framework.library;
	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.service;

	requires java.desktop;
	requires java.validation;
	requires lombok;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires transitive spring.boot;
	requires transitive spring.data.commons;
	requires transitive spring.web;
	requires spring.hateoas;
}