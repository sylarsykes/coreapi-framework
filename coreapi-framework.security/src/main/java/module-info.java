module coreapi.framework.security {
	exports org.sylrsykssoft.coreapi.framework.security.configuration;

	requires transitive javax.servlet.api;

	requires spring.beans;
	requires spring.context;
	requires spring.core;
	requires transitive spring.security.config;
	requires transitive spring.security.core;
	requires spring.security.web;
	requires spring.web;
}