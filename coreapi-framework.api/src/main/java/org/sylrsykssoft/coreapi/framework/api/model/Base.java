package org.sylrsykssoft.coreapi.framework.api.model;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.springframework.lang.Nullable;
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
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class Base {

	protected LocalDateTime createdAt;
	protected @Nullable LocalDateTime updatedAt;

	/**
	 * Default builder.
	 * 
	 * @return BaseBuilder
	 */
	public static BaseBuilder builder() {
		return new BaseBuilder();
	}

	/**
	 * BaseBuilder.
	 * 
	 * @param base {@link Base}
	 * @return BaseBuilder
	 */
	public static BaseBuilder builder(final Base base) {
		return new BaseBuilder(base);
	}

	/**
	 * Builder.
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 */
	@Component()
	public static class BaseBuilder {

		/**
		 * Default constructor.
		 */
		public BaseBuilder() {
			super();
		}

		/**
		 * AllArgsConstructor
		 * 
		 * @param base Base object.
		 */
		@ConstructorProperties({ "base"})
		public BaseBuilder(final Base base) {
			this.createdAt = base.getCreatedAt();
			this.updatedAt = base.getUpdatedAt();
		}

	}
}

