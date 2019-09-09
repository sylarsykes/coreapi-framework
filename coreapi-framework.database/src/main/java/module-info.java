/**
 * Module info
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
module coreapi.framework.database {
	exports org.sylrsykssoft.coreapi.framework.database.exception;
	exports org.sylrsykssoft.coreapi.framework.database.model.listener;
	exports org.sylrsykssoft.coreapi.framework.database.repository;

	requires java.desktop;
	requires java.persistence;

	requires coreapi.framework.api;

	requires org.apache.commons.lang3;
	requires spring.data.commons;
	requires spring.data.jpa;
	requires spring.tx;
	requires spring.web;
}