package org.sylrsykssoft.coreapi.framework.api.model;

import static org.sylrsykssoft.coreapi.framework.api.configuration.CoreApiFrameworkApiAddressConstants.ONE_ADDRESS_REPOSITORY_ENTITY_NAME;
import static org.sylrsykssoft.coreapi.framework.api.configuration.CoreApiFrameworkApiAddressConstants.ONE_ADDRESS_REPOSITORY_TABLE_NAME;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Address entity
 * 
 * Using
 * 
 * @OneToOne(mappedBy = "person") private OneAddress<T, ID> address;
 * 
 * @param <T> The type of object that extends Base
 * @param <N> The type of the identifier
 * @author juan.gonzalez.fernandez.jgf
 */
@Table(name = ONE_ADDRESS_REPOSITORY_TABLE_NAME)
@Entity(name = ONE_ADDRESS_REPOSITORY_ENTITY_NAME)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class OneAddress<T extends Base<N>, N extends Number> extends Address<T, N> {

	/**
	 * OneAddressBuilder
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 * @param <T> The type of object that extends Base
	 * @param <N> The type of the identifier
	 */
	public static class OneAddressBuilder<T extends Base<N>, N extends Number>
	extends AddressBuilder<T, N, OneAddress<T, N>, OneAddressBuilder<T, N>> {
		/**
		 * {inheritDoc}
		 */
		@Override
		protected OneAddressBuilder<T, N> self() {
			return this;
		}
	}

	// Relationships
	@OneToOne
	T person;
}
