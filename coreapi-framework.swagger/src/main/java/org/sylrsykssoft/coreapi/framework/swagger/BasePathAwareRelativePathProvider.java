package org.sylrsykssoft.coreapi.framework.swagger;

import org.springframework.web.util.UriComponentsBuilder;

import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;

/**
 * Hello world!
 *
 */
public class BasePathAwareRelativePathProvider extends AbstractPathProvider {
	private final String basePath;

	public BasePathAwareRelativePathProvider(final String basePath) {
		this.basePath = basePath;
	}

	@Override
	protected String applicationPath() {
		return basePath;
	}

	@Override
	protected String getDocumentationPath() {
		return "/";
	}

	@Override
	public String getOperationPath(final String operationPath) {
		final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
		return Paths.removeAdjacentForwardSlashes(
				uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
	}

}
