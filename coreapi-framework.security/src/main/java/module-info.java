module coreapi.framework.security {
	exports org.sylrsykssoft.coreapi.framework.security.annotation;
	exports org.sylrsykssoft.coreapi.framework.security.configuration;
	exports org.sylrsykssoft.coreapi.framework.security.handler;
	exports org.sylrsykssoft.coreapi.framework.security.domain;
	exports org.sylrsykssoft.coreapi.framework.security.util;

	requires java.desktop;
	requires java.persistence;
	requires transitive javax.servlet.api;

	requires transitive coreapi.framework.library;
	requires coreapi.framework.api;
	requires coreapi.framework.database;

	requires lombok;
	requires org.slf4j;
	requires spring.beans;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires transitive spring.security.config;
	requires transitive spring.security.core;
	requires spring.security.web;
	requires spring.web;
	requires spring.webmvc;
	requires spring.data.jpa;
	requires spring.hateoas;
	requires org.hibernate.orm.core;
}