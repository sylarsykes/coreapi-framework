module coreapi.framework.audit {
	exports org.sylrsykssoft.coreapi.framework.audit.configuration;
	exports org.sylrsykssoft.coreapi.framework.audit.configuration.properties;
	exports org.sylrsykssoft.coreapi.framework.audit.controller;
	exports org.sylrsykssoft.coreapi.framework.audit.domain;
	exports org.sylrsykssoft.coreapi.framework.audit.domain.listener;
	exports org.sylrsykssoft.coreapi.framework.audit.exception;
	exports org.sylrsykssoft.coreapi.framework.audit.repository;
	exports org.sylrsykssoft.coreapi.framework.audit.resource;
	exports org.sylrsykssoft.coreapi.framework.audit.resource.assembler;
	exports org.sylrsykssoft.coreapi.framework.audit.service;

	requires java.desktop;
	requires java.transaction;
	requires java.xml.bind;

	requires coreapi.framework.library;
	requires coreapi.framework.swagger;
	requires coreapi.framework.api;
	requires coreapi.framework.database;
	requires coreapi.framework.service;
	requires coreapi.framework.web;

	requires lombok;

	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires transitive spring.data.commons;
	requires hibernate.envers;
	requires spring.data.envers;
	requires spring.data.jpa;
	requires hibernate.jpa;
	requires spring.hateoas;

	// Swagger dependencies
	requires com.google.common;
	requires spring.plugin.core;
	requires springfox.swagger2;
	requires springfox.core;
	requires springfox.spi;
	requires swagger.annotations;
	requires transitive springfox.spring.web;
}