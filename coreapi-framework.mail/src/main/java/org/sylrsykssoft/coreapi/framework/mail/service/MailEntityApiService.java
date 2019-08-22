package org.sylrsykssoft.coreapi.framework.mail.service;

import javax.mail.MessagingException;

import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.mail.domain.EntityMailTO;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

/**
 * MailEntity service
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface MailEntityApiService<T extends BaseEntityResource> {
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
	void setFrom(EntityMailTO<T> source) throws MessagingException;

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
	void setSubject(EntityMailTO<T> source) throws MessagingException;

	/**
	 * Setter content
	 * 
	 * @param source
	 */
	void setText(EntityMailTO<T> source, boolean html) throws MessagingException;

	/**
	 * Setter to
	 * 
	 * @param source
	 */
	void setTo(EntityMailTO<T> source) throws MessagingException;
}
