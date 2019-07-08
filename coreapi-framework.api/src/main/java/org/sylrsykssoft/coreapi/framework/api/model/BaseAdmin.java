package org.sylrsykssoft.coreapi.framework.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/***
 * Entity admin
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
public class BaseAdmin extends Base<Integer> {

	public static final int MAX_LENGTH_NAME = 256;
	public static final int MAX_LENGTH_DESCRIPTION = 10000;
	
	@Column(name = "name", nullable = false, unique = true, length = MAX_LENGTH_NAME)
	protected @NonNull String name;
	
	@Column(name = "descriptiom", nullable = true, columnDefinition = "TEXT", length = MAX_LENGTH_DESCRIPTION)
	protected @Nullable String description;
	
	/**
	 * AllArgsConstructor
	 * 
	 * @param entityId
	 * @param name
	 * @param description
	 * @param createdAt
	 * @param updatedAt
	 */
	@Builder(builderMethodName = "baseAdminBuilder")
	public BaseAdmin(final Integer entityId, final String name, final String description, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
		this.entityId = entityId;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
	}
}
