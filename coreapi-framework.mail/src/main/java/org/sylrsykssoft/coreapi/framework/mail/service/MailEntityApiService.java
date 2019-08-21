package org.sylrsykssoft.coreapi.framework.mail.service;

import javax.mail.MessagingException;

import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.mail.domain.IMailTO;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

/**
 * MailEntity service
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface MailEntityApiService<T extends BaseEntity> {

	/**
	 * Send mail
	 * 
	 * @param source
	 */
	void send(IMailTO<T, Long> source, boolean html) throws CoreApiFrameworkMailException;

	/**
	 * Setter from
	 * 
	 * @param source
	 */
	void setFrom(IMailTO<T, Long> source) throws MessagingException;

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
	void setSubject(IMailTO<T, Long> source) throws MessagingException;

	/**
	 * Setter content
	 * 
	 * @param source
	 */
	void setText(IMailTO<T, Long> source, boolean html) throws MessagingException;

	/**
	 * Setter to
	 * 
	 * @param source
	 */
	void setTo(IMailTO<T, Long> source) throws MessagingException;
}
