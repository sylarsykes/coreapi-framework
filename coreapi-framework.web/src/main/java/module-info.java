module coreapi.framework.web {
	exports org.sylrsykssoft.coreapi.framework.web;
	exports org.sylrsykssoft.coreapi.framework.web.configuration;
	exports org.sylrsykssoft.coreapi.framework.web.configuration.annotation;
	exports org.sylrsykssoft.coreapi.framework.web.configuration.property;
	exports org.sylrsykssoft.coreapi.framework.web.rest;
	exports org.sylrsykssoft.coreapi.framework.web.resource.assembler;

	requires java.desktop;
	requires java.validation;

	// Core API Framework Module Dependencies
	requires coreapi.framework.library;
	requires coreapi.framework.swagger;
	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.service;

	requires lombok;

	requires spring.core;
	requires spring.beans;
	requires transitive spring.context;
	requires transitive spring.boot;
	requires spring.boot.autoconfigure;
	requires transitive spring.data.commons;
	requires transitive spring.web;
	requires spring.hateoas;
	requires transitive spring.webmvc;

	// Swagger dependencies
	requires com.google.common;
	requires spring.plugin.core;
	requires springfox.swagger2;
	requires springfox.core;
	requires springfox.spi;
	requires swagger.annotations;
	requires transitive springfox.spring.web;
	requires transitive org.apache.tomcat.embed.core;
	requires coreapi.framework.security;
	requires spring.security.config;
}