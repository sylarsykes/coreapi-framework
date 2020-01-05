package org.sylrsykssoft.coreapi.framework.security.domain;

import java.beans.ConstructorProperties;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * User entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class BaseUser<N extends Number> extends User implements Persistable<N> {

	private static final long serialVersionUID = 5355466822306327832L;

	public static final int MAX_LENGTH_NAME = 30;

	public static final int MAX_LENGTH_SURNAME = 256;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	N entityId;

	@Column(name = "name", nullable = false, length = MAX_LENGTH_NAME)
	@NonNull
	String name;

	@Column(name = "surname", nullable = true, length = MAX_LENGTH_SURNAME)
	@Nullable
	String surname;

	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param username
	 * @param name
	 * @param surname
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	@Builder(builderMethodName = "baseUserBuilder")
	@ConstructorProperties({ "entityId", "username", "name", "surname", "password", "enabled", "accountNonExpired",
			"credentialsNonExpired", "accountNonLocked", "authorities" })
	public BaseUser(final N entityId, final String username, final String name, final String surname,
			final String password, final boolean enabled, final boolean accountNonExpired,
			final boolean credentialsNonExpired, final boolean accountNonLocked,
			final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		this.entityId = entityId;
		this.name = name;
		this.surname = surname;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public N getId() {
		return entityId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNew() {
		return null == getId();
	}
}
