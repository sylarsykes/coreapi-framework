package org.sylrsykssoft.coreapi.framework.security.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * User entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 * 
 */
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString(callSuper = false, includeFieldNames = true)
public class BaseUser<N extends Number> implements Persistable<N> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	N entityId;
	
	@Column(name = "username", nullable = false)
	String username;
	
	@Column(name = "password", nullable = false)
    String password;

    @Column(name = "enabled", nullable = false)
    boolean enabled;

    @Column(name = "account_non_expired", nullable = false)
    boolean accountNonExpired;

    @Column(name = "credentials_non_expired", nullable = false)
    boolean credentialsNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    boolean accountNonLocked;

    @Column(name = "creation_date_time")
    LocalDateTime createDateTime;

    @Column(name = "updated_date_time")
    LocalDateTime updateDateTime;

    @Column(name = "last_online_date_time", nullable = false)
    LocalDateTime lastOnline;

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
