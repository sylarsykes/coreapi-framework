package org.sylrsykssoft.coreapi.framework.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Spring Security Configuration
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Configuration
//The prePostEnabled property enables Spring Security pre/post annotations @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter
//The securedEnabled property determines if the @Secured annotation should be enabled
//The jsr250Enabled property allows us to use the @RoleAllowed annotation
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class CoreApiFrameworkWebSecurityConfiguration {

}
