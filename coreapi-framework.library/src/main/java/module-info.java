module coreapi.framework.library {
	exports org.sylrsykssoft.coreapi.framework.library.error;
	exports org.sylrsykssoft.coreapi.framework.library.mapper;
	exports org.sylrsykssoft.coreapi.framework.library.error.exception;

	requires java.desktop;
	requires lombok;
	requires modelmapper;
	requires spring.beans;
	requires spring.context;
}