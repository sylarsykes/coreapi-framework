package org.sylrsykssoft.coreapi.framework.mail.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;

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
 * DTO Mail BaseAdmin
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString
public class AdminSimpleMailTO<T extends BaseAdminSimpleResource> implements IMailTO<T, Integer> {

	@NonNull
	String from;

	@NonNull
	String to;

	@Nullable
	Optional<List<String>> cc;

	@NonNull
	String subject;

	@Nullable
	Optional<String> content;

	@Nullable
	Optional<String> templateName;
}
