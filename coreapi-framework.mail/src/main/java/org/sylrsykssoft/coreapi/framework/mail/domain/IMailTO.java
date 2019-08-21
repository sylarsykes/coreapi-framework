package org.sylrsykssoft.coreapi.framework.mail.domain;

import java.util.List;
import java.util.Optional;

import org.sylrsykssoft.coreapi.framework.api.model.Base;

/**
 * MailTO interface
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public interface IMailTO<T extends Base<ID>, ID extends Number> {

	/**
	 * Getter list destination
	 * 
	 * @return
	 */
	Optional<List<String>> getCc();

	/**
	 * Getter content
	 * 
	 * @return String
	 */
	Optional<String> getContent();

	/**
	 * Getter from
	 * 
	 * @return String
	 */
	String getFrom();

	/**
	 * Getter subject
	 * 
	 * @return String
	 */
	String getSubject();

	/**
	 * Getter template name
	 * 
	 * @return Optional<String>
	 */
	Optional<String> getTemplateName();

	/**
	 * Getter to destination
	 * 
	 * @return String
	 */
	String getTo();

	/**
	 * Setter list to destination
	 * 
	 * @param listTo
	 */
	void setCc(Optional<List<String>> cc);

	/**
	 * Setter content
	 * 
	 * @param content
	 */
	void setContent(Optional<String> content);

	/**
	 * Setter from
	 * 
	 * @param from
	 */
	void setFrom(String from);

	/**
	 * Setter subject
	 * 
	 * @param subject
	 */
	void setSubject(String subject);

	/**
	 * Setter template name
	 * 
	 * @param templateName
	 */
	void setTemplateName(Optional<String> templateName);

	/**
	 * Setter destination mail
	 * 
	 * @param to
	 */
	void setTo(String to);
}
