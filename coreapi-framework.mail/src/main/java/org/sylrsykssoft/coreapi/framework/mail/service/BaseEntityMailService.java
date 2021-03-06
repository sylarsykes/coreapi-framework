package org.sylrsykssoft.coreapi.framework.mail.service;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;
import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.FREEMAKER_TEMPLATE_BEAN_NAME;
import static org.sylrsykssoft.coreapi.framework.mail.configuration.CoreApiFrameworkMailConstants.MAIL_ASYNC_EXECUTOR;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.mail.domain.EntityMailTO;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Mail Entity default service
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public abstract class BaseEntityMailService<T extends BaseEntityResource>
implements MailEntityApiService<T>, InitializingBean {

	@Autowired
	protected JavaMailSender mailSender;

	@Autowired
	@Qualifier(FREEMAKER_TEMPLATE_BEAN_NAME)
	protected Configuration mailConfig;

	protected MimeMessage message;

	protected MimeMessageHelper messageHelper;

	protected EntityMailTO<T> mailto;

	protected MailAdminApiServiceConfiguration serviceConfiguration;

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	/**
	 * Initialize service
	 * 
	 * @throws MessagingException
	 */
	public void init() throws MessagingException {
		this.message = this.mailSender.createMimeMessage();
		this.messageHelper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public boolean send(final MailAdminApiServiceConfiguration serviceConfiguration, final T source, final boolean html)
			throws CoreApiFrameworkMailException {
		this.serviceConfiguration = serviceConfiguration;

		return send(source, html);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public boolean send(final T source, final boolean html) throws CoreApiFrameworkMailException {
		setMailtTO(source);

		if (validate(mailto)) {
			try {
				setFrom(mailto);
				setTo(mailto);
				setSubject(mailto);
				setText(mailto, html);
			} catch (final MessagingException e) {
				throw new CoreApiFrameworkMailException(e);
			}

			mailSender.send(this.message);

			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}}
	 * 
	 * @throws InterruptedException
	 */
	@Override
	@Async(MAIL_ASYNC_EXECUTOR)
	public Future<Boolean> sendAsync(final MailAdminApiServiceConfiguration serviceConfiguration, final T source,
			final boolean html) throws CoreApiFrameworkMailException, InterruptedException {
		final boolean result = send(serviceConfiguration, source, html);

		Thread.sleep(1000L); // Intentional delay

		return new AsyncResult<>(result);
	}

	/**
	 * {@inheritDoc}}
	 * 
	 * @throws MessagingException
	 */
	@Override
	public void setFrom(final EntityMailTO<T> source) throws MessagingException {
		messageHelper.setFrom(source.getFrom());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setSubject(final EntityMailTO<T> source) throws MessagingException {
		messageHelper.setSubject(source.getSubject());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setText(final EntityMailTO<T> source, final boolean html) throws MessagingException {
		if (source.getTemplateName() != null && source.getTemplateName().isPresent() && html) {
			try {
				final Template template = mailConfig.getTemplate(source.getTemplateName().get());
				final String htmlOutput = FreeMarkerTemplateUtils.processTemplateIntoString(template, source);

				messageHelper.setText(htmlOutput, html);
			} catch (final Exception e) {
				throw new MessagingException(e.getMessage(), e);
			}
		} else if (source.getContent() != null && source.getContent().isPresent()) {
			messageHelper.setText(source.getContent().get(), html);
		} else
			throw new MessagingException("Not content or template is defined");
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setTo(final EntityMailTO<T> source) throws MessagingException {
		messageHelper.setTo(source.getTo());

		if (source.getCc() != null && source.getCc().isPresent()) {
			messageHelper.setCc((String[]) source.getCc().get().toArray());
		}
	}

	/**
	 * Validate to send mail
	 * 
	 * @param source
	 * @return boolean
	 */
	protected boolean validate(final EntityMailTO<T> source) {
		boolean result = false;

		if (StringUtils.isNotBlank(source.getFrom()) && StringUtils.isNotBlank(source.getTo())
				&& StringUtils.isNotBlank(source.getSubject())) {
			result = true;
		}

		return result;
	}
}
