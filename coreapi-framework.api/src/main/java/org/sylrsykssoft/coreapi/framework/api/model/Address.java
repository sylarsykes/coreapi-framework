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
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * Address entity
 * 
 * Using
 * 	@OneToOne(mappedBy = "person")
 *  private Address address;
 * 
 * @param <T> The type of object that extends Base
 * @param <N> The type of the identifier
 * @author juan.gonzalez.fernandez.jgf
 */
@Table(name = ADDRESS_REPOSITORY_TABLE_NAME)
@Entity(name = ADDRESS_REPOSITORY_ENTITY_NAME)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString(includeFieldNames = true)
public class Address<T extends Base<N>, N extends Number> {

	public static final int MAX_LENGTH_NAME = 256;
	
	@Column(name = "street_name_number", nullable = false, length = MAX_LENGTH_NAME)
    @NonNull String streetNameAndNumber;
	
	@Column(name = "apartment_number", nullable = false, length = MAX_LENGTH_NAME)
    @NonNull String apartmentOrSuiteNumber;
	
	@Column(name = "city", nullable = false, length = MAX_LENGTH_NAME)
    @NonNull String city;
	
	@Column(name = "state", nullable = false, length = MAX_LENGTH_NAME)
    @NonNull String state;
	
	@Column(name = "zip_code", nullable = true, length = MAX_LENGTH_NAME)
    @Nullable String zipcode;

}
