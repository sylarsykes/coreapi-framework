package org.sylrsykssoft.coreapi.framework.api.model;

import java.beans.ConstructorProperties;

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
 * Entity translatable
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
public class BaseTranslate {

	protected String locale;

	/**
	 * Default builder.
	 * 
	 * @return BaseTranslateBuilder
	 */
	public static BaseTranslateBuilder builder() {
		return new BaseTranslateBuilder();
	}

	/**
	 * Base translate builder.
	 * 
	 * @param translate {@link BaseTranslate}
	 * 
	 * @return BaseTranslateBuilder
	 */
	public static BaseTranslateBuilder builder(final BaseTranslate translate) {
		return new BaseTranslateBuilder(translate);
	}

	/**
	 * BaseTranslateBuilder.
	 * 
	 * @author juan.gonzalez.fernandez.jgf
	 *
	 */
	@Component()
	public static class BaseTranslateBuilder {

		/**
		 * Default constructor.
		 */
		public BaseTranslateBuilder() {
			super();
		}

		/**
		 * AllArgsContructor
		 *  
		 * @param translate.
		 */
		@ConstructorProperties({ "baseTranslate"})
		public BaseTranslateBuilder(final BaseTranslate baseTranslate) {
			this.locale = baseTranslate.getLocale();
		}
	}
}
