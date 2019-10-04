package org.sylrsykssoft.coreapi.framework.web.configuration.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.sylrsykssoft.coreapi.framework.web.configuration.CoreApiFrameworkWebConfiguration;

/**
 * Enables Spring's annotation-driven internationalization
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CoreApiFrameworkWebConfiguration.class)
public @interface EnableCoreApiFrameworkWebInternationalization {

}
