package org.sylrsykssoft.coreapi.framework.mail.service;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.mail.exception.CoreApiFrameworkMailException;

/**
 * Factory for instance mail admin service
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Service
public class MailAdminApiFactoryService<R extends BaseAdminResource>
		extends AbstractFactoryBean<MailAdminApiService<R>> {

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
	protected MailAdminApiService<R> createInstance() throws Exception {
		return (MailAdminApiService<R>) applicationContext.getBean(serviceConfiguration.getServiceName());
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
	public boolean execute(final MailAdminApiServiceConfiguration serviceConfiguration, final R source,
			final boolean html)
					throws CoreApiFrameworkMailException, Exception {
		this.serviceConfiguration = serviceConfiguration;
		return createInstance().send(source, html);
	}

	/**
	 * Create instance of service and send mail
	 * 
	 * @param serviceConfiguration
	 * @param source
	 * @param html
	 * 
	 * @return Future<Boolean>
	 * 
	 * @throws CoreApiFrameworkMailException
	 * @throws Exception
	 */
	public Future<Boolean> executeAsync(final MailAdminApiServiceConfiguration serviceConfiguration, final R source,
			final boolean html) throws CoreApiFrameworkMailException, Exception {
		this.serviceConfiguration = serviceConfiguration;
		return createInstance().sendAsync(serviceConfiguration, source, html);
	}

	/**
	 * {@inheritDoc}}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class<MailAdminApiService<R>> getObjectType() {
		return (Class<MailAdminApiService<R>>) GenericTypeResolver.resolveTypeArgument(getClass(),
				MailAdminApiFactoryService.class);
	}

}
