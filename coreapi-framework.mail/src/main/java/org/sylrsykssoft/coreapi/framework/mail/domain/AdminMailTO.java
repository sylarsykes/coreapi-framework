package org.sylrsykssoft.coreapi.framework.mail.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO Mail BaseAdmin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class AdminMailTO<T extends BaseAdminResource> implements IMailTO<T, Integer> {

	protected @NonNull String from;
	protected @NonNull String to;
	protected @Nullable Optional<List<String>> cc;
	protected @NonNull String subject;
	protected @Nullable Optional<String> content;
	protected @Nullable Optional<String> templateName;
}
