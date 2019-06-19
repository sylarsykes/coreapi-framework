module coreapi.framework.api {
	exports org.sylrsykssoft.coreapi.framework.api.configuration;
	exports org.sylrsykssoft.coreapi.framework.api.resource;
	exports org.sylrsykssoft.coreapi.framework.api.model;
	exports org.sylrsykssoft.coreapi.framework.api.resource.assembler;

	requires coreapi.framework.library;
	requires java.desktop;
	requires lombok;
	requires spring.beans;
	requires spring.context;
	requires spring.core;
	requires spring.hateoas;
	requires spring.web;
}