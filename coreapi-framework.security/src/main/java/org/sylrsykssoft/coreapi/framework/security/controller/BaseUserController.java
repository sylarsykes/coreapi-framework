package org.sylrsykssoft.coreapi.framework.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;
import org.sylrsykssoft.coreapi.framework.security.domain.BaseUser;
import org.sylrsykssoft.coreapi.framework.security.resource.BaseUserResource;
import org.sylrsykssoft.coreapi.framework.security.service.IBaseUserUserDetailsService;
import org.sylrsykssoft.coreapi.framework.security.util.IAuthenticationFacade;
import org.sylrsykssoft.coreapi.framework.security.util.LoggerUserUtil;

/**
 * BaseUserController
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public abstract class BaseUserController<R extends BaseUserResource<N>, T extends BaseUser<N>, N extends Number> {

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected IAuthenticationFacade authenticationFacade;

	/**
	 * Find all entries.
	 * 
	 * @return Iterable<T> entries.
	 * 
	 * @throws NotFoundEntityException
	 */
	@GetMapping(produces = { MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	public Iterable<R> findAll() throws NotFoundEntityException {
		LoggerUserUtil.log(LogMessageLevel.INFO, authenticationFacade.getAuthentication(),
				"BaseUserController::findAll");
		LoggerUtil.message(LogMessageLevel.INFO, "BaseUserController::findAll Finding all entries");

		final Iterable<R> entities = getUserDetailService().findAll();
		if (entities == null) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseUserController::findAll not find result");
			throw new NotFoundEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseUserController::findAll Found {} entries.", entities);

		return entities;
	}

	/**
	 * Getter user detail service implementation
	 * 
	 * @return IBaseUserUserDetailsService<T, R, N>
	 */
	public abstract IBaseUserUserDetailsService<T, R, N> getUserDetailService();
}
