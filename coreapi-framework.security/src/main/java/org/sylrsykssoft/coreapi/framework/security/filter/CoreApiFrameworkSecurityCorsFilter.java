package org.sylrsykssoft.coreapi.framework.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * Cors filter
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CoreApiFrameworkSecurityCorsFilter implements Filter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final List<String> allowMethods = Arrays.asList(HttpMethod.POST.name(), HttpMethod.PUT.name(),
				HttpMethod.GET.name(), HttpMethod.OPTIONS.name(), HttpMethod.DELETE.name());
		final List<String> allowHeaders = Arrays.asList("Authorization", "Content-Type");

		final HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", StringUtils.join(allowMethods, ","));
		response.setHeader("Access-Control-Allow-Headers", StringUtils.join(allowHeaders, ","));
		response.setHeader("Access-Control-Max-Age", "3600");
		if (HttpMethod.OPTIONS.name().equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final FilterConfig config) throws ServletException {
	}

}
