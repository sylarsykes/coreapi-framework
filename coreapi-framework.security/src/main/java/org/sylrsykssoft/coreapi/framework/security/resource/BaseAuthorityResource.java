package org.sylrsykssoft.coreapi.framework.security.resource;

import java.beans.ConstructorProperties;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.core.GrantedAuthority;
import org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.AuthorityType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Authority entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseAuthorityResource<N extends Number> extends ResourceSupport implements GrantedAuthority {

	private static final long serialVersionUID = 5342339393965904488L;

	N entityId;
	AuthorityType authority;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param authority
	 */
	@Builder(builderMethodName = "baseAuthorityResourceBuilder")
	@ConstructorProperties({ "entityId", "authority" })
	public BaseAuthorityResource(final N entityId, final AuthorityType authority) {
		super();
		this.entityId = entityId;
		this.authority = authority;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getAuthority() {
		return this.authority.toString();
	}

}
