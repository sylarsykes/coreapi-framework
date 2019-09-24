package org.sylrsykssoft.coreapi.framework.web.rest;

import java.beans.ConstructorProperties;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * Base admin simple controller
 * 
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public class BaseAdminSimpleRestTemplateController<R extends BaseAdminSimpleResource, T extends BaseAdminSimple> {

	protected Class<R> responseType;

	@Autowired
	protected RestTemplate restTemplate;

	/**
	 * AllArgsConstructor
	 * 
	 * @param responseType
	 */
	@ConstructorProperties({ "responseType" })
	public BaseAdminSimpleRestTemplateController(final Class<R> responseType) {
		this.responseType = responseType;
	}

	/**
	 * Find all entries.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 * @throws CoreApiFrameworkLibraryException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<R> findAll(final String httpUrl) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleRestTemplateController::findAll Finding all entries");

		Iterable<R> result = null;

		final UriComponents url = UriComponentsBuilder.fromHttpUrl(httpUrl).build();

		try {

			final ResponseEntity<? extends ArrayList> responseEntity = restTemplate.getForEntity(url.toUri(),
					ArrayList.class);

			result = responseEntity.getBody();
		} catch (final RestClientException e) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminSimpleRestTemplateController::findAll resclienterror -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleRestTemplateController::findAll Result -> {}", result);
		return result;
	}

	/**
	 * Find one entry.
	 * 
	 * @param id Id
	 * 
	 * @return T entry.
	 * 
	 * @throws NotFoundEntityException
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R findById(final String httpUrl, final Integer id)
			throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminSimpleRestTemplateController::findById Finding a entry with id: {}", id);

		R result = null;

		// URI (URL) parameters
		final Map<String, Integer> urlParams = new HashMap<>();
		urlParams.put("id", id);

		final UriComponents url = UriComponentsBuilder.fromHttpUrl(httpUrl).buildAndExpand(urlParams);

		result = getForObject(url.toUri());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleRestTemplateController::findById Result -> {}",
				result);
		return result;
	}

	/**
	 * Find by name.
	 * 
	 * @param name Value of attribute name.
	 * 
	 * @return R entity.
	 * 
	 * @throws NotFoundEntityException
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R findByName(final String httpUrl, final String name)
			throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminSimpleRestTemplateController::findByName Finding a entry with name: {}", name);

		final R result = null;

		// URI (URL) parameters
		final Map<String, String> urlParams = new HashMap<>();
		urlParams.put("name", name);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleRestTemplateController::findByName Result -> {}",
				result);
		return result;
	}

	/**
	 * Get object from uri
	 * 
	 * @param url
	 * @return R
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R getForObject(final URI url) throws CoreApiFrameworkLibraryException {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleRestTemplateController::getForObject URI -> {}", url);

		R result = null;

		try {
			result = restTemplate.getForObject(url, responseType);
		} catch (final RestClientException e) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminSimpleRestTemplateController::getForObject RestClientException -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (final Exception e) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminSimpleRestTemplateController::getForObject Exception -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminSimpleRestTemplateController::getForObject Result -> {}",
				result);
		return result;
	}

}
