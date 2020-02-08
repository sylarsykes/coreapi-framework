package org.sylrsykssoft.coreapi.framework.security.resource;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * User entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseUserResource<N extends Number> extends ResourceSupport {

	N entityId;
	final String username;
	String password;
	final boolean accountNonExpired;
	final boolean accountNonLocked;
	final boolean credentialsNonExpired;
	final boolean enabled;
	LocalDateTime createDateTime;
	LocalDateTime updateDateTime;
	LocalDateTime lastOnline;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	@Builder(builderMethodName = "baseUserResourceBuilder")
	@ConstructorProperties({ "entityId", "username", "password", "enabled", "accountNonExpired",
			"credentialsNonExpired", "accountNonLocked", "createDateTime", "updateDateTime", "lastOnline", "authorities" })
	public BaseUserResource(final N entityId, final String username, final String password, final boolean enabled,
			final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked,
			final LocalDateTime createDateTime, final LocalDateTime updateDateTime, final LocalDateTime lastOnline) {
		
		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException(
					"Cannot pass null or empty values to constructor");
		}
		
		this.entityId = entityId;
		this.password = password;
		this.username = username;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
		this.lastOnline = lastOnline;
	}

	/**
	 * Returns if the {@code Persistable} is new or was persisted already.
	 *
	 * @return if {@literal true} the object is new.
	 */
	public boolean isNew() {
		return null == getEntityId();
	}
	
}
