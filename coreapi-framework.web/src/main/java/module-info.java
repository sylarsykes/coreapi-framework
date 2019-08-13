module coreapi.framework.web {
	exports org.sylrsykssoft.coreapi.framework.web;
	exports org.sylrsykssoft.coreapi.framework.web.configuration;
	exports org.sylrsykssoft.coreapi.framework.web.resource.assembler;

	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.library;
	requires coreapi.framework.service;
	requires java.validation;
	requires lombok;
	requires transitive slf4j.api;
	requires spring.data.commons;
	requires transitive spring.hateoas;
	requires transitive spring.web;
	requires java.desktop;
	requires spring.context;
	requires transitive spring.boot;
	requires spring.beans;
}