package org.sylrsykssoft.coreapi.framework.audit.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionSort;
import org.springframework.data.history.Revisions;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdminSimple;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminSimpleResource;
import org.sylrsykssoft.coreapi.framework.audit.exception.NotFoundAuditEntityException;
import org.sylrsykssoft.coreapi.framework.service.IAdminSimpleService;

/**
 * Service.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type of class.
 * @param <R> Resourse of class.
 * @param <I> Class of identifier.
 * @param <N> Class of identifier of {@link Revisions}
 */
public interface IAdminSimpleAuditService<T extends BaseAdminSimple, R extends BaseAdminSimpleResource, I extends Number, N extends Number & Comparable<N>>
		extends IAdminSimpleService<T, R, N> {

	/**
	 * Returns the revision of the entity it was last changed in.
	 *
	 * @param id must not be {@literal null}.
	 * @return Optional<Revision<N, R>> the last {@link Revision} of the entity with
	 *         the given ID
	 */
	Optional<Revision<N, R>> findLastChangeRevision(I id) throws NotFoundAuditEntityException;

	/**
	 * Returns the entity with the given ID in the given revision number.
	 *
	 * @param id must not be {@literal null}.
	 * @param revisionNumber must not be {@literal null}.
	 * @return the {@link Revision} of the entity with the given ID in the given revision number.
	 */
	Optional<Revision<N, R>> findRevision(I id, N revisionNumber) throws NotFoundAuditEntityException;

	/**
	 * Returns all {@link Revisions} of an entity with the given id.
	 *
	 * @param id must not be {@literal null}.
	 * @return
	 */
	Revisions<N, R> findRevisions(I id);

	/**
	 * Returns a {@link Page} of revisions for the entity with the given id. Note, that it's not guaranteed that
	 * implementations have to support sorting by all properties.
	 *
	 * @param id must not be {@literal null}.
	 * @param pageable
	 * @see RevisionSort
	 * @return
	 */
	Page<Revision<N, R>> findRevisions(I id, Pageable pageable);
}
