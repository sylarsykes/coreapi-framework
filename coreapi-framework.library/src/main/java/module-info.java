module coreapi.framework.library {
	exports org.sylrsykssoft.coreapi.framework.library.error;
	exports org.sylrsykssoft.coreapi.framework.library.configuration;
	exports org.sylrsykssoft.coreapi.framework.library.mapper;
	exports org.sylrsykssoft.coreapi.framework.library.error.exception;

	requires java.desktop;
	requires lombok;
	requires modelmapper;
	requires slf4j.api;
	requires spring.beans;
	requires spring.context;
	requires spring.core;
	requires spring.hateoas;
}