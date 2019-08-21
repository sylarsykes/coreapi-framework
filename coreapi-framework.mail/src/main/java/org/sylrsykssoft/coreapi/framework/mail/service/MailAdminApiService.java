package org.sylrsykssoft.coreapi.framework.mail.service;

import javax.mail.MessagingException;

import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.mail.domain.IMailTO;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

/**
 * Mail Admin service
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface MailAdminApiService<T extends BaseAdmin> {

	/**
	 * Send mail
	 * 
	 * @param source
	 */
	void send(IMailTO<T, Integer> source, boolean html) throws CoreApiFrameworkMailException;

	/**
	 * Setter from
	 * 
	 * @param source
	 */
	void setFrom(IMailTO<T, Integer> source) throws MessagingException;

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
	void setSubject(IMailTO<T, Integer> source) throws MessagingException;

	/**
	 * Setter content
	 * 
	 * @param source
	 */
	void setText(IMailTO<T, Integer> source, boolean html) throws MessagingException;

	/**
	 * Setter to
	 * 
	 * @param source
	 */
	void setTo(IMailTO<T, Integer> source) throws MessagingException;
}
