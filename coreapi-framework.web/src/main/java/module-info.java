module coreapi.framework.web {
	exports org.sylrsykssoft.coreapi.framework.web;

	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.library;
	requires coreapi.framework.service;
	requires java.validation;
	requires lombok;
	requires transitive slf4j.api;
	requires spring.data.commons;
	requires transitive spring.hateoas;
	requires spring.web;
}