module coreapi.framework.web {
	exports org.sylrsykssoft.coreapi.framework.web;

	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.library;
	requires coreapi.framework.service;
	requires lombok;
	requires slf4j.api;
	requires spring.beans;
	requires spring.hateoas;
	requires spring.web;
}