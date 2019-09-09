package org.sylrsykssoft.coreapi.framework.audit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.sylrsykssoft.coreapi.framework.audit.domain.listener.AuditRevisionListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entity audit revision.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
@Table(name = "audit_revision")
@Entity(name = "AuditRevision")
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString
@RevisionEntity(AuditRevisionListener.class)
public class AuditRevision extends DefaultRevisionEntity {

	private static final long serialVersionUID = 1049095879642363065L;

	@Column(name = "username", nullable = false, unique = true, length = 60)
	@NonNull
	String username;

}
