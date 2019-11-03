module coreapi.framework.security.oauth {
	exports org.sylrsykssoft.coreapi.framework.security.oauth.configuration;

	requires transitive java.sql;

	requires coreapi.framework.security;

	requires org.apache.commons.lang3;
	requires spring.beans;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires transitive spring.jdbc;
	requires spring.security.config;
	requires transitive spring.security.core;
	requires transitive spring.security.oauth2;
	requires spring.security.web;
}