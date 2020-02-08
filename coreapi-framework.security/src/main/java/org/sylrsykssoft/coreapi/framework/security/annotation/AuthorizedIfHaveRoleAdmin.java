package org.sylrsykssoft.coreapi.framework.security.annotation;

import static org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.HASROLE_ROLE_ADMIN;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * PreAuthorize Role admin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize(HASROLE_ROLE_ADMIN)
public @interface AuthorizedIfHaveRoleAdmin {

}
