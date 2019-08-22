/**
 * Module info
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.mail {
	exports org.sylrsykssoft.coreapi.framework.mail.configuration;
	exports org.sylrsykssoft.coreapi.framework.mail.service;
	exports org.sylrsykssoft.coreapi.framework.mail.exception;
	exports org.sylrsykssoft.coreapi.framework.mail.domain;

	requires transitive coreapi.framework.api;

	requires freemarker;
	requires java.desktop;
	requires java.mail;
	requires lombok;
	requires org.apache.commons.lang3;
	requires spring.beans;
	requires spring.context;
	requires transitive spring.context.support;
	requires transitive spring.core;
	requires transitive spring.data.commons;
	requires transitive spring.web;

	uses org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
	uses org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
	uses org.sylrsykssoft.coreapi.framework.api.resource.BaseResource;
}