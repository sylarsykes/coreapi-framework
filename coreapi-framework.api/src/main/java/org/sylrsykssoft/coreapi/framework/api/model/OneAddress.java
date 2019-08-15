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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Address entity
 * 
 * Using
 * 	@OneToOne(mappedBy = "person")
 *  private OneAddress<T, ID> address;
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T>
 * @param <ID>
 */
@Table(name = ONE_ADDRESS_REPOSITORY_TABLE_NAME)
@Entity(name = ONE_ADDRESS_REPOSITORY_ENTITY_NAME)
@Builder(toBuilder = true)
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class OneAddress<T extends Base<ID>, ID extends Number> extends Address<T, ID> {

	/**
	 * OneAddressBuilder
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 * @param <T>
	 * @param <ID>
	 */
	public static class OneAddressBuilder<T extends Base<ID>, ID extends Number> extends AddressBuilder<T, ID, OneAddress<T, ID>, OneAddressBuilder<T, ID>> {
    	/**
    	 * {inheritDoc}
    	 */
    	@Override
    	protected OneAddressBuilder<T, ID> self() {
			return this;
		}
    }

	@OneToOne
    private T person;
}
