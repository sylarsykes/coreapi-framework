package org.sylrsykssoft.coreapi.framework.mail.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.Nullable;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO Mail BaseEntity
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@ToString
public class EntityMailTO<T extends BaseEntityResource> implements IMailTO<T, Long> {

	private String from;
	private String to;
	private @Nullable Optional<List<String>> cc;
	private String subject;
	private @Nullable Optional<String> content;
	private @Nullable Optional<String> templateName;
}
