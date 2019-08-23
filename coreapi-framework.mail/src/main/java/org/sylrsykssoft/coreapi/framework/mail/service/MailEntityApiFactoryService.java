package org.sylrsykssoft.coreapi.framework.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

/**
 * Factory for instance mail service
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Service
public class MailEntityApiFactoryService<R extends BaseEntityResource>
extends AbstractFactoryBean<MailEntityApiService<R>> {

	private MailAdminApiServiceConfiguration serviceConfiguration;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		setSingleton(false);
		super.afterPropertiesSet();
	}

	/**
	 * {@inheritDoc}}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected MailEntityApiService<R> createInstance() throws Exception {
		return (MailEntityApiService<R>) applicationContext.getBean(serviceConfiguration.getServiceName());
	}

	/**
	 * Create instance of service and send mail
	 * 
	 * @param serviceConfiguration
	 * @param source
	 * @param html
	 * 
	 * @throws CoreApiFrameworkMailException
	 * @throws Exception
	 */
	public void execute(final MailAdminApiServiceConfiguration serviceConfiguration, final R source, final boolean html)
			throws CoreApiFrameworkMailException, Exception {
		this.serviceConfiguration = serviceConfiguration;
		createInstance().send(source, html);
	}

	/**
	 * {@inheritDoc}}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class<MailEntityApiService<R>> getObjectType() {
		return (Class<MailEntityApiService<R>>) GenericTypeResolver.resolveTypeArgument(getClass(),
				MailEntityApiFactoryService.class);
	}

}
