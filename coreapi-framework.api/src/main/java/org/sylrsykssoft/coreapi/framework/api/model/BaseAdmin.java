package org.sylrsykssoft.coreapi.framework.api.model;

import java.beans.ConstructorProperties;
import java.beans.Transient;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

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
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class BaseAdmin {

	protected Integer adminId;
	protected @NonNull String name;
	protected @Nullable String description;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Persistable#isNew()
	 */
	@Transient
	public boolean isNew() {
		return null == getAdminId();
	}
	
	/**
	 * Default builder.
	 * 
	 * @return BaseAdminBuilder
	 */
	public static BaseAdminBuilder builder() {
		return new BaseAdminBuilder();
	}

	/**
	 * BaseAdminBuilder.
	 * 
	 * @param base {@link BaseAdmin}
	 * 
	 * @return BaseAdminBuilder
	 */
	public static BaseAdminBuilder builder(final BaseAdmin base) {
		return new BaseAdminBuilder(base);
	}

	/**
	 * BaseAdminBuilder.
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 */
	@Component()
	public static class BaseAdminBuilder {

		/**
		 * Default constructor.
		 */
		public BaseAdminBuilder() {
			super();
		}

		/**
		 * AllArgsConstuctor
		 * 
		 * @param base {@link BaseAdmin} object.
		 */
		@ConstructorProperties({ "base"})
		public BaseAdminBuilder(final BaseAdmin base) {
			this.adminId = base.getAdminId();
			this.name = base.getName();
			this.description = base.getDescription();
		}
	}
	
}
