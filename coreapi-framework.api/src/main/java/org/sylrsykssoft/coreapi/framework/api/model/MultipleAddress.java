package org.sylrsykssoft.coreapi.framework.api.model;

import static org.sylrsykssoft.coreapi.framework.api.configuration.CoreApiFrameworkApiAddressConstants.MULTIPLE_ADDRESS_REPOSITORY_ENTITY_NAME;
import static org.sylrsykssoft.coreapi.framework.api.configuration.CoreApiFrameworkApiAddressConstants.MULTIPLE_ADDRESS_REPOSITORY_TABLE_NAME;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * MultipleAddress entity
 * 
 * ManyToMany entity with extra field
 * 
 * Using In entity 1
 * 
 * @OneToMany(mappedBy = "person", cascade = CascadeType.ALL) private @Singular
 *                     Set<MultipleAddress<T, Address<T, ID>, ID> addresses;
 * 
 *                     In entity 2
 * @OneToMany(mappedBy = "address", cascade = CascadeType.ALL) private @Singular
 *                     Set<MultipleAddress<T, Address<T, ID>, ID> addresses;
 * 
 * @param <T> The type of object that extends Base
 * @param <A> The type of object that extends Address
 * @param <N> The type of the identifier
 * @author juan.gonzalez.fernandez.jgf
 * 
 * @see https://hellokoding.com/jpa-many-to-many-extra-columns-relationship-mapping-example-with-spring-boot-maven-and-mysql/
 */
@Table(name = MULTIPLE_ADDRESS_REPOSITORY_TABLE_NAME)
@Entity(name = MULTIPLE_ADDRESS_REPOSITORY_ENTITY_NAME)
@Where(clause = "removedAt = null")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class MultipleAddress<T extends Base<N>, A extends Address<T, N>, N extends Number> {

	@Id
	@ManyToOne
	@JoinColumn
	private T person;

	@Id
	@ManyToOne
	@JoinColumn
	private A address;

	@Column(name = "removed_at", nullable = true, insertable = false, updatable = true)
	private @Nullable LocalDateTime removedAt;

}
