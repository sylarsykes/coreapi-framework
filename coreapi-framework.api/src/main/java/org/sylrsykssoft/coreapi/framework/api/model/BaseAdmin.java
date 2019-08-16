package org.sylrsykssoft.coreapi.framework.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/***
 * Entity admin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@MappedSuperclass
@Where(clause = "removedAt = null")
@Data
@SuperBuilder
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

	@Column(name = "removed_at", nullable = true, insertable = false, updatable = true)
	protected @Nullable LocalDateTime removedAt;

}
