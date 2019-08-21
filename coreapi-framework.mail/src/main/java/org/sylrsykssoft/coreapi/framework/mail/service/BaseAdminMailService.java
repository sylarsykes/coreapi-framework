package org.sylrsykssoft.coreapi.framework.mail.service;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;
import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.FREEMAKER_TEMPLATE_BEAN_NAME;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.mail.domain.IMailTO;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Mail Admin default service
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public abstract class BaseAdminMailService<T extends BaseAdmin> implements MailAdminApiService<T> {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier(FREEMAKER_TEMPLATE_BEAN_NAME)
	private Configuration mailConfig;

	private final MimeMessage message;

	private final MimeMessageHelper messageHelper;

	/**
	 * 
	 * @param mailSender
	 * @throws MessagingException
	 */
	public BaseAdminMailService() throws MessagingException {
		this.message = this.mailSender.createMimeMessage();
		this.messageHelper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void send(final IMailTO<T, Integer> source, final boolean html) throws CoreApiFrameworkMailException {
		if (validate(source)) {

			try {
				setFrom(source);
				setTo(source);
				setSubject(source);
				setText(source, html);
			} catch (final MessagingException e) {
				throw new CoreApiFrameworkMailException(e);
			}

			mailSender.send(this.message);
		}
	}

	/**
	 * {@inheritDoc}}
	 * 
	 * @throws MessagingException
	 */
	@Override
	public void setFrom(final IMailTO<T, Integer> source) throws MessagingException {
		messageHelper.setFrom(source.getFrom());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setSubject(final IMailTO<T, Integer> source) throws MessagingException {
		messageHelper.setSubject(source.getSubject());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setText(final IMailTO<T, Integer> source, final boolean html) throws MessagingException {
		if (source.getTemplateName().isPresent() && html) {
			try {
				final Template template = mailConfig.getTemplate(source.getTemplateName().get());
				final String htmlOutput = FreeMarkerTemplateUtils.processTemplateIntoString(template, source);

				messageHelper.setText(htmlOutput, html);
			} catch (final Exception e) {
				throw new MessagingException(e.getMessage(), e);
			}
		} else if (source.getContent().isPresent()) {
			messageHelper.setText(source.getContent().get(), html);
		} else
			throw new MessagingException("Not content or template is defined");
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setTo(final IMailTO<T, Integer> source) throws MessagingException {
		messageHelper.setTo(source.getTo());

		if (source.getCc().isPresent()) {
			messageHelper.setCc((String[]) source.getCc().get().toArray());
		}
	}

	/**
	 * Validate to send mail
	 * 
	 * @param source
	 * @return boolean
	 */
	protected boolean validate(final IMailTO<T, Integer> source) {
		boolean result = false;

		if (StringUtils.isNotBlank(source.getFrom()) && StringUtils.isNotBlank(source.getTo())) {
			result = true;
		}

		return result;
	}
}
