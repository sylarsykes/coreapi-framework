package org.sylrsykssoft.coreapi.framework.web.rest;

import java.beans.ConstructorProperties;
import java.net.URI;

import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * Base admin controller
 * 
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public class BaseAdminRestTemplateController<R extends BaseAdminResource, T extends BaseAdmin>
		extends BaseAdminSimpleRestTemplateController<R, T> {

	/**
	 * 
	 * @param responseType
	 */
	@ConstructorProperties({ "responseType" })
	public BaseAdminRestTemplateController(final Class<R> responseType) {
		super(responseType);
	}

	/**
	 * Find by example
	 * 
	 * @param resource Entity to find.
	 * 
	 * @return T entity.
	 * 
	 * @throws NotFoundEntityException
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R findOneByExample(final String httpUrl, final R resource)
			throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminRestTemplateController::findOneByExample Finding a entry with: {}", resource);

		final UriComponents url = UriComponentsBuilder.fromHttpUrl(httpUrl).build();
		final R result = postForObject(url.toUri(), resource);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminRestTemplateController::findOneByExample Result -> {}",
				result);

		return result;
	}

	/**
	 * Create a new resource by POSTing the given object to the URL, and returns the
	 * representation found in the response.
	 * 
	 * @param url
	 * @param resource
	 * @return R
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R postForObject(final URI url, final R resource) throws CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminRestTemplateController::getForObject URI -> {} {}", url,
				resource);

		R result = null;

		try {
			result = restTemplate.postForObject(url, resource, responseType);
		} catch (final RestClientException e) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminRestTemplateController::postForObject RestClientException -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (final Exception e) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminRestTemplateController::postForObject Exception -> {}",
					e);
			throw new CoreApiFrameworkLibraryException(e);
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminRestTemplateController::postForObject Result -> {}", result);
		return result;
	}
}
