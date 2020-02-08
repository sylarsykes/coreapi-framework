package org.sylrsykssoft.coreapi.framework.security.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;
import org.sylrsykssoft.coreapi.framework.security.configuration.CoreApiFrameworkSecurityConstants.AuthorityType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * @author Juan
 *
 * @see https://stackoverflow.com/questions/56973048/how-to-fetch-custom-user-entity-for-custom-authentication-in-login-on-spring-sec
 */
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString(callSuper = false, includeFieldNames = true)
public class BaseAuthority<N extends Number> implements Persistable<N> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	N entityId;

	@Enumerated(EnumType.STRING)
	AuthorityType authority;

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
