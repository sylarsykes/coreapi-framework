package org.sylrsykssoft.coreapi.framework.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class Base<ID extends Number> implements Persistable<ID> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected ID entityId;
	
	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	protected LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = true, insertable = false, updatable = true)
	protected @Nullable LocalDateTime updatedAt;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Persistable#getId()
	 */
	@Override
	public ID getId() {
		return entityId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Persistable#isNew()
	 */
	@Override
	public boolean isNew() {
		return null == getId();
	}

}

