package org.sylrsykssoft.coreapi.framework.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity base.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@MappedSuperclass
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class Base<ID> implements Persistable<ID> {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	protected ID entityId;
	
	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = true, insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
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

