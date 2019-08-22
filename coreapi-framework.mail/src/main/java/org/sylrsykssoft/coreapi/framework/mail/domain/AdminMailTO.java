package org.sylrsykssoft.coreapi.framework.mail.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DTO Mail BaseAdmin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString
public class AdminMailTO<T extends BaseAdminResource> implements IMailTO<T, Integer> {

	protected String from;
	protected String to;
	protected @Nullable Optional<List<String>> cc;
	protected String subject;
	protected @Nullable Optional<String> content;
	protected @Nullable Optional<String> templateName;
}
