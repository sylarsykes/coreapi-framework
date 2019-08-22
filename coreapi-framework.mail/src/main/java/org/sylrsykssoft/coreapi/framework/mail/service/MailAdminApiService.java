package org.sylrsykssoft.coreapi.framework.mail.service;

import javax.mail.MessagingException;

import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.mail.domain.AdminMailTO;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

/**
 * Mail Admin service
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface MailAdminApiService<T extends BaseAdminResource> {

	/**
	 * Send mail
	 * 
	 * @param source
	 */
	void send(T source, boolean html) throws CoreApiFrameworkMailException;

	/**
	 * Setter from
	 * 
	 * @param source
	 */
	void setFrom(AdminMailTO<T> source) throws MessagingException;

	/**
	 * Create mailtto object
	 * 
	 * @param source
	 */
	void setMailtTO(T source);

	/**
	 * Setter subject
	 * 
	 * @param source
	 */
	void setSubject(AdminMailTO<T> source) throws MessagingException;

	/**
	 * Setter content
	 * 
	 * @param source
	 */
	void setText(AdminMailTO<T> source, boolean html) throws MessagingException;

	/**
	 * Setter to
	 * 
	 * @param source
	 */
	void setTo(AdminMailTO<T> source) throws MessagingException;
}