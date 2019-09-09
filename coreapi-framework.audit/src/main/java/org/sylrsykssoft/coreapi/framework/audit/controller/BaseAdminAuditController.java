package org.sylrsykssoft.coreapi.framework.audit.controller;

import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.CONTROLLER_GET_FINDREVISION_BY_ID_REVISIONNUMBER;
import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.CONTROLLER_GET_FIND_ALL_REVISIONS;
import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.CONTROLLER_GET_FIND_LAST_CHANGE_REVISION_BY_ID;
import static org.sylrsykssoft.coreapi.framework.audit.configuration.BaseAdminAuditConstants.CONTROLLER_POST_FIND_ALL_BY_PAGEABLE;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionSort;
import org.springframework.data.history.Revisions;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.sylrsykssoft.coreapi.framework.audit.domain.BaseAdminAudit;
import org.sylrsykssoft.coreapi.framework.audit.exception.NotFoundAuditEntityException;
import org.sylrsykssoft.coreapi.framework.audit.resource.BaseAdminAuditResource;
import org.sylrsykssoft.coreapi.framework.audit.service.BaseAdminAuditService;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;
import org.sylrsykssoft.coreapi.framework.web.BaseAdminController;

/**
 * Base admin controller
 * 
 * @param <R> Resource class
 * @param <T> Admin class
 * @author juan.gonzalez.fernandez.jgf
 */
public abstract class BaseAdminAuditController<R extends BaseAdminAuditResource, T extends BaseAdminAudit>
extends BaseAdminController<R, T> {

	/**
	 * Returns the revision of the entity it was last changed in.
	 *
	 * @param id must not be {@literal null}.
	 * @return R the last {@link Revision} of the entity with the given ID
	 */
	@GetMapping(path = CONTROLLER_GET_FIND_LAST_CHANGE_REVISION_BY_ID, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	public R findLastChangeRevision(final @PathVariable Integer id) throws NotFoundAuditEntityException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminAuditService::findLastChangeRevision Finding a last change revision with id: {}", id);

		final Optional<Revision<Integer, R>> source = getAdminService().findLastChangeRevision(id);

		if (source.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN,
					"BaseAdminAuditController::findLastChangeRevision not find result for id -> {}",
					id);
			throw new NotFoundAuditEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findLastChangeRevision Result -> {}",
				source.get().getEntity());

		return source.get().getEntity();
	}

	/**
	 * Returns the entity with the given ID in the given revision number.
	 *
	 * @param id             must not be {@literal null}.
	 * @param revisionNumber must not be {@literal null}.
	 * @return R the {@link Revision} of the entity with the given ID in the given
	 *         revision number.
	 */
	@GetMapping(path = CONTROLLER_GET_FINDREVISION_BY_ID_REVISIONNUMBER, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	public R findRevision(final @PathVariable Integer id, final @PathVariable Integer revisionNumber)
			throws NotFoundAuditEntityException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminAuditController::findRevision Finding a revision with id: {} and revison number: {}", id,
				revisionNumber);
		final Optional<Revision<Integer, R>> source = getAdminService().findRevision(id, revisionNumber);

		if (source.isPresent()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminAuditController::findById not find result for id -> {}",
					id);
			throw new NotFoundAuditEntityException();
		}

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminController::findById Result -> {}",
				source.get().getEntity());

		return source.get().getEntity();
	}

	/**
	 * Returns all {@link Revisions} of an entity with the given id.
	 *
	 * @param id must not be {@literal null}.
	 * @return Iterable<R>
	 */
	@GetMapping(path = CONTROLLER_GET_FIND_ALL_REVISIONS, produces = { MediaTypes.HAL_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	public Iterable<R> findRevisions(final Integer id) {
		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminAuditService::findRevisions Finding revisions with id: {}",
				id);

		final Revisions<Integer, R> sources = getAdminService().findRevisions(id);

		if (sources.isEmpty()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminAuditService::findRevisions not find result");
			throw new NotFoundEntityException();
		}

		final Iterable<R> result = sources.getContent().stream().map(s -> s.getEntity()).collect(Collectors.toList());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminAuditService::findRevisions Found {} entries.", result);

		return result;
	}

	/**
	 * Returns a list of revisions for the entity with the given id.
	 *
	 * @param id       must not be {@literal null}.
	 * @param pageable
	 * @see RevisionSort
	 * @return
	 */
	@PostMapping(path = CONTROLLER_POST_FIND_ALL_BY_PAGEABLE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.FOUND)
	public Iterable<R> findRevisions(final @PathVariable Integer id, final PageRequest pageable) {

		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseAdminAuditService::findRevisions Finding revisions with id: {} and  pageable: {}", id, pageable);

		final Page<Revision<Integer, R>> sources = getAdminService().findRevisions(id, pageable);

		if (sources.isEmpty()) {
			LoggerUtil.message(LogMessageLevel.WARN, "BaseAdminAuditService::findRevisions not find result");
			throw new NotFoundEntityException();
		}

		final Iterable<R> result = sources.getContent().stream().map(s -> s.getEntity()).collect(Collectors.toList());

		LoggerUtil.message(LogMessageLevel.INFO, "BaseAdminAuditService::findRevisions Found {} entries.", result);

		return result;
	}

	/**
	 * Getter admin service implementation
	 * 
	 * @return BaseAdminService<T, R>
	 */
	@Override
	public abstract BaseAdminAuditService<T, R> getAdminService();
}
