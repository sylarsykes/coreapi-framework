module coreapi.framework.service {
	exports org.sylrsykssoft.coreapi.framework.service;

	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.library;
	requires java.persistence;
	requires spring.beans;
	requires transitive spring.data.commons;
	requires spring.data.jpa;
	requires spring.hateoas;
	requires lombok;
}