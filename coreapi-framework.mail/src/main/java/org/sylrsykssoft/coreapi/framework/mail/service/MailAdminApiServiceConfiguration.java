package org.sylrsykssoft.coreapi.framework.mail.service;

/**
 * @author Juan
 *
 */
public interface MailAdminApiServiceConfiguration {

	/**
	 * Getter from
	 * 
	 * @return String
	 */
	String getFrom();

	/**
	 * Getter serviceName
	 * 
	 * @return String
	 */
	String getServiceName();

	/**
	 * Getter templateName
	 * 
	 * @return String
	 */
	String getTemplateName();

	/**
	 * Getter to
	 * 
	 * @return
	 */
	String getTo();

}
