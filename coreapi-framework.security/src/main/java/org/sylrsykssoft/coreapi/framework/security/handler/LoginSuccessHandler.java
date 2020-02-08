package org.sylrsykssoft.coreapi.framework.security.handler;

import static org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.HANDLER_SUCCES_KEY;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

/**
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException {

		final SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
		final FlashMap flashMap = new FlashMap();

		flashMap.put(HANDLER_SUCCES_KEY, "Logged in succesfully");
		flashMapManager.saveOutputFlashMap(flashMap, request, response);

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
