package org.sylrsykssoft.coreapi.framework.mail.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO Mail BaseEntity
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
public class EntityMailTO<T extends BaseEntityResource> implements IMailTO<T, Long> {

	private @NonNull String from;
	private @NonNull String to;
	private @Nullable Optional<List<String>> cc;
	private @NonNull String subject;
	private @Nullable Optional<String> content;
	private @Nullable Optional<String> templateName;
}
