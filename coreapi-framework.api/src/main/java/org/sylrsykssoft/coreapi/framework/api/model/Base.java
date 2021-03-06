package org.sylrsykssoft.coreapi.framework.api.model;

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
 * Entity base.
 * 
 * @param <N> The type of the identifier
 * @author juan.gonzalez.fernandez.jgf
 */
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString
public class Base<N extends Number> implements Persistable<N> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	N entityId;
	
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

