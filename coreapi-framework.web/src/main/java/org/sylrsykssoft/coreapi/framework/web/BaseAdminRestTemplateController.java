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
 * @param <restTemplate>
 */
@Slf4j
public class BaseAdminRestTemplateController<R extends BaseAdminResource, T extends BaseAdmin, restTemplate> {

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
			url.append("/").append(basePath).append("/").append(controllerRequestName).append("/")
			.append(BaseAdminConstants.CONTROLLER_GET_FIND_BY_ID).append("/").append(id);
			
			result = restTemplate.getForObject(url.toString(), responseType);
			
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findById find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (RestClientException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findById resclienterror -> {}", e);
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
	 * @example /admin/musicalGenres/name/{name}]
	 * 
	 * @return T entity.
	 * 
	 * @throws NotFoundEntityException
	 * @throws CoreApiFrameworkLibraryException
	 */
	public R findByName(final String name) throws NotFoundEntityException, CoreApiFrameworkLibraryException {
		LOGGER.info("BaseAdminRestTemplateController::findByName Finding a entry with name: {}", name);
		
		R result = null;
		
		try {
			
			final StringBuilder url = new StringBuilder(environmentUtil.getServerUrlPrefi());
			url.append("/").append(basePath).append("/").append(controllerRequestName).append("/")
			.append(BaseAdminConstants.CONTROLLER_GET_FIND_BY_NAME).append("/").append(name);
			
			result = restTemplate.getForObject(url.toString(), responseType);
			
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findByName find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (RestClientException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findByName resclienterror -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::findByName Result -> {}", result);
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
			url.append("/").append(basePath).append("/").append(controllerRequestName).append("/")
			.append(BaseAdminConstants.CONTROLLER_GET_FIND_BY_EXAMPLE);
			
			result = restTemplate.postForObject(url.toString(), resource, responseType);
			
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findOneByExample find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (RestClientException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findOneByExample resclienterror -> {}", e);
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
	 */
	@SuppressWarnings("unchecked")
	public Iterable<R> findAll() throws NotFoundEntityException {
		LOGGER.info("BaseAdminController::findAll Finding all entries");
		
		Iterable<R> result = null;
		
		try {
			
			final StringBuilder url = new StringBuilder(environmentUtil.getServerUrlPrefi());
			url.append("/").append(basePath).append("/").append(controllerRequestName);
			
			ResponseEntity<? extends ArrayList<R>> responseEntity = restTemplate.getForEntity(new URI(url.toString()), (Class<? extends ArrayList<R>>)ArrayList.class);
			
		} catch (UnknownHostException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findByName find error on get evironment information -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (RestClientException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findByName resclienterror -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		} catch (URISyntaxException e) {
			LOGGER.warn("BaseAdminRestTemplateController::findByName resclienterror -> {}", e);
			throw new CoreApiFrameworkLibraryException(e);
		}
		
		LOGGER.info("BaseAdminRestTemplateController::findByName Result -> {}", result);
		return result;
	}
}
