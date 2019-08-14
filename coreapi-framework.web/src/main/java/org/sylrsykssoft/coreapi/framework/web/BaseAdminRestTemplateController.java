package org.sylrsykssoft.coreapi.framework.web;

import java.beans.ConstructorProperties;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.library.error.exception.CoreApiFrameworkLibraryException;
import org.sylrsykssoft.coreapi.framework.library.util.EnvironmentUtil;
import org.sylrsykssoft.coreapi.framework.web.configuration.BaseAdminConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Base admin controller
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <R> Resource class
 * @param <T> Admin class
 */
@Slf4j
public class BaseAdminRestTemplateController<R extends BaseAdminResource, T extends BaseAdmin> {

	private static final String SLASH_SEPARATOR = "/";
	
	/** BasePath */
	private String basePath;
	/** BaseAdminResourceName */
	private String controllerRequestName;
	
	private Class<R> responseType;
	
	@Autowired
	private EnvironmentUtil environmentUtil;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * AllArgsConstructor
	 * 
	 * @param basePath
	 * @param controllerRequestName
	 */
	@ConstructorProperties({ "basePath", "controllerRequestName", "responseType" })
	public BaseAdminRestTemplateController(final String basePath, final String controllerRequestName, final Class<R> responseType) {
		this.basePath = basePath;
		this.controllerRequestName = controllerRequestName;
		this.responseType = responseType;
	}
	
	/**
	 * Get object from uri
	 * 
	 * @param url
	 * @return R
	 * @throws URISyntaxException
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R getForObject(final String url) throws CoreApiFrameworkLibraryException {
		try {
			return getForObject(new URI(url));
		} catch (URISyntaxException e) {
			LOGGER.warn("BaseAdminRestTemplateController::getForObject URISyntaxException -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
	}

	/**
	 * Get object from uri
	 * 
	 * @param url
	 * @return R
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R getForObject(final URI url) throws CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminRestTemplateController::getForObject URI -> {}", url);
		
		R result = null;
		
		try {
			result = restTemplate.getForObject(url, responseType);
		} catch (RestClientException e) {
			LOGGER.warn("BaseAdminRestTemplateController::getForObject RestClientException -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (Exception e) {
			LOGGER.warn("BaseAdminRestTemplateController::getForObject Exception -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::getForObject Result -> {}", result);
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
	public R findById(final Integer id) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminRestTemplateController::findById Finding a entry with id: {}", id);
		
		R result = null;
		
		try {
			
			final StringBuilder url = new StringBuilder(environmentUtil.getServerUrlPrefi());
			url.append(SLASH_SEPARATOR)
				.append(basePath)
				.append(SLASH_SEPARATOR)
				.append(controllerRequestName)
				.append(SLASH_SEPARATOR)
				.append(BaseAdminConstants.CONTROLLER_GET_FIND_BY_ID).append(SLASH_SEPARATOR)
				.append(id);
			
			result = getForObject(url.toString());
			
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findById find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::findById Result -> {}", result);
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
	public R findByName(final String name) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminRestTemplateController::findByName Finding a entry with name: {}", name);
		
		R result = null;
		
		try {
			
			final StringBuilder url = new StringBuilder(environmentUtil.getServerUrlPrefi());
			url.append(SLASH_SEPARATOR)
				.append(basePath)
				.append(SLASH_SEPARATOR)
				.append(controllerRequestName)
				.append(SLASH_SEPARATOR)
				.append(BaseAdminConstants.CONTROLLER_GET_FIND_BY_NAME)
				.append(SLASH_SEPARATOR).append(name);
			
				result = getForObject(url.toString());
			
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findByName find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::findByName Result -> {}", result);
		return result;
	}
	
	/**
	 * Create a new resource by POSTing the given object to the URL, and returns the representation found in the response.
	 * 
	 * @param url
	 * @param resource
	 * @return R
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R postForObject(final String url, final R resource) throws CoreApiFrameworkLibraryException {
		try {
			return postForObject(new URI(url), resource);
		} catch (URISyntaxException e) {
			LOGGER.warn("BaseAdminRestTemplateController::postForObject URISyntaxException -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
	}
	
	/**
	 * Create a new resource by POSTing the given object to the URL, and returns the representation found in the response. 
	 * 
	 * @param url
	 * @param resource
	 * @return R
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R postForObject(final URI url, final R resource) throws CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminRestTemplateController::getForObject URI -> {} {}", url, resource);
		
		R result = null;
		
		try {
			result = restTemplate.postForObject(url, resource, responseType);
		} catch (RestClientException e) {
			LOGGER.warn("BaseAdminRestTemplateController::postForObject RestClientException -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (Exception e) {
			LOGGER.warn("BaseAdminRestTemplateController::postForObject Exception -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::postForObject Result -> {}", result);
		return result;
	}
		
	/**
	 * Find by example
	 * 
	 * @param resource
	 *            Entity to find.
	 * 
	 * @return T entity.
	 * 
	 * @throws NotFoundEntityException
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R findOneByExample(final R resource) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminRestTemplateController::findOneByExample Finding a entry with: {}", resource);
		
		R result = null;
		
		try {
			
			final StringBuilder url = new StringBuilder(environmentUtil.getServerUrlPrefi());
			url.append(SLASH_SEPARATOR)
				.append(basePath)
				.append(SLASH_SEPARATOR)
				.append(controllerRequestName)
				.append(SLASH_SEPARATOR)
				.append(BaseAdminConstants.CONTROLLER_GET_FIND_BY_EXAMPLE);
			
			result = postForObject(url.toString(), resource);
			
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findOneByExample find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::findOneByExample Result -> {}", result);
		return result;
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
	public Iterable<R> findAll() throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminRestTemplateController::findAll Finding all entries");
		
		Iterable<R> result = null;
		
		try {
			
			final StringBuilder url = new StringBuilder(environmentUtil.getServerUrlPrefi());
			url.append(SLASH_SEPARATOR)
				.append(basePath)
				.append(SLASH_SEPARATOR)
				.append(controllerRequestName);
			
			ResponseEntity<? extends ArrayList> responseEntity = restTemplate.getForEntity(new URI(url.toString()), ArrayList.class);
			
			result = (ArrayList<R>) responseEntity.getBody();
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findAll find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (RestClientException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findAll resclienterror -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (URISyntaxException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findAll resclienterror -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::findAll Result -> {}", result);
		return result;
	}
}
