package org.sylrsykssoft.coreapi.framework.database.model.listener;

import java.util.Locale;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;
import org.sylrsykssoft.coreapi.framework.api.model.BaseTranslate;
import org.sylrsykssoft.coreapi.framework.api.model.EntityTranslatable;

/**
 * Translate listener.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
public class BaseTranslateListener {

	/**
	 * Assign locale.
	 * 
	 * @param base
	 */
	@PrePersist
	void onPrePersist(final Object translatebleObject) {
		if (translatebleObject instanceof EntityTranslatable) {
			final BaseTranslate translate = getTranslatable((EntityTranslatable) translatebleObject);

			if (StringUtils.isEmpty(translate.getLocale())) {
				translate.setLocale(Locale.UK.getLanguage());
			}
		}
	}
	
	/**
	 * Assign locale.
	 * 
	 * @param base
	 */
	@PreUpdate
	void onPreUpdate(final Object translatebleObject) {
		if (translatebleObject instanceof EntityTranslatable) {
			final BaseTranslate translate = getTranslatable((EntityTranslatable) translatebleObject);
			
			if (StringUtils.isEmpty(translate.getLocale())) {
				translate.setLocale(Locale.UK.getLanguage());
			}
		}
	}

	/**
	 * Getter audit
	 * 
	 * @param auditableObject
	 * @return
	 */
	private BaseTranslate getTranslatable(final EntityTranslatable translatableObject) {
		BaseTranslate translate = translatableObject.getTranslate();
		if (translate == null) {
			translate = new BaseTranslate();
			translatableObject.setTranslate(translate);
		}
		return translate;
	}
}
