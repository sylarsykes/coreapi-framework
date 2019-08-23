package org.sylrsykssoft.coreapi.framework.mail.service;

import java.util.concurrent.Future;

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
	 * @param serviceConfiguration
	 * @param source
	 * @param html
	 * @throws CoreApiFrameworkMailException
	 */
	boolean send(MailAdminApiServiceConfiguration serviceConfiguration, T source, boolean html)
			throws CoreApiFrameworkMailException;

	/**
	 * Send mail
	 * 
	 * @param source
	 * @param html
	 * @throws CoreApiFrameworkMailException
	 */
	boolean send(T source, boolean html) throws CoreApiFrameworkMailException;

	/**
	 * Send mail
	 * 
	 * @param serviceConfiguration
	 * @param source
	 * @param html
	 * @throws CoreApiFrameworkMailException
	 */
	Future<Boolean> sendAsync(MailAdminApiServiceConfiguration serviceConfiguration, T source, boolean html)
			throws CoreApiFrameworkMailException, InterruptedException;

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
