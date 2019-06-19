package org.sylrsykssoft.coreapi.framework.api.model;

import java.beans.ConstructorProperties;
import java.beans.Transient;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Data()
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter()
@Getter()
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseEntity extends Base {

	protected Long entityId;
	
	/**
	 * Builder.
	 * 
	 * @return BaseEntityBuilder
	 */
	public static BaseEntityBuilder builder() {
		return new BaseEntityBuilder();
	}
	
	/**
	 * Builder.
	 * 
	 * @param base {@link BaseEntity}
	 * 
	 * @return BaseEntityBuilder
	 */
	public static BaseEntityBuilder builder(final BaseEntity base) {
		return new BaseEntityBuilder(base);
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Persistable#isNew()
	 */
	@Transient
	public boolean isNew() {
		return null == getEntityId();
	}
	
	/**
	 * BaseEntityBuilder.
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 */
	@Component()
	public static class BaseEntityBuilder extends BaseBuilder {
		
		/**
		 * Default constructor.
		 */
		public BaseEntityBuilder() {
			super();
		}
		
		/**
		 * AllArgsConstructor
		 * 
		 * @param base BaseEntity object.
		 */
		@ConstructorProperties({ "base"})
		public BaseEntityBuilder(final BaseEntity base) {
			super(base);
			this.entityId = base.entityId;
		}
	}
}
