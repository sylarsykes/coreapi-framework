module coreapi.framework.api {
	exports org.sylrsykssoft.coreapi.framework.api.configuration;
	exports org.sylrsykssoft.coreapi.framework.api.resource;
	exports org.sylrsykssoft.coreapi.framework.api.model;

	requires coreapi.framework.library;
	requires lombok;
	requires spring.beans;
	requires spring.context;
	requires spring.core;
	requires spring.web;
	requires java.persistence;
	requires spring.data.commons;
	requires spring.data.jpa;
	requires java.desktop;
	requires spring.hateoas;
}