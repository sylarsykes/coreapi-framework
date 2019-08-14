package org.sylrsykssoft.coreapi.framework.api.model;

import static org.sylrsykssoft.coreapi.framework.api.configuration.CoreApiFrameworkApiAddressConstants.ADDRESS_REPOSITORY_ENTITY_NAME;
import static org.sylrsykssoft.coreapi.framework.api.configuration.CoreApiFrameworkApiAddressConstants.ADDRESS_REPOSITORY_TABLE_NAME;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

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


/**
 * Address entity
 * 
 * Using
 * 	@OneToOne(mappedBy = "person")
 *  private Address address;
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T>
 * @param <ID>
 */
@Table(name = ADDRESS_REPOSITORY_TABLE_NAME)
@Entity(name = ADDRESS_REPOSITORY_ENTITY_NAME)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class Address<T extends Base<ID>, ID extends Number> {

	public static final int MAX_LENGTH_NAME = 256;
	
	@Column(name = "street_name_number", nullable = false, length = MAX_LENGTH_NAME)
    protected @NonNull String streetNameAndNumber;
	@Column(name = "apartment_number", nullable = false, length = MAX_LENGTH_NAME)
    protected @NonNull String apartmentOrSuiteNumber;
	@Column(name = "city", nullable = false, length = MAX_LENGTH_NAME)
    protected @NonNull String city;
	@Column(name = "state", nullable = false, length = MAX_LENGTH_NAME)
    protected @NonNull String state;
	@Column(name = "zip_code", nullable = true, length = MAX_LENGTH_NAME)
    protected @Nullable String zipcode;

}
