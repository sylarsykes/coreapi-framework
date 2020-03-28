package org.sylrsykssoft.coreapi.framework.audit.service.find;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.sylrsykssoft.coreapi.framework.audit.domain.BaseAdminAudit;
import org.sylrsykssoft.coreapi.framework.audit.exception.NotFoundAuditEntityException;
import org.sylrsykssoft.coreapi.framework.audit.repository.BaseAdminAuditRepository;
import org.sylrsykssoft.coreapi.framework.audit.resource.BaseAdminAuditResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil;
import org.sylrsykssoft.coreapi.framework.library.util.LoggerUtil.LogMessageLevel;

/**
 * BaseFindRevisionAdminAuditService implementation of services of
 * IFindRevisionAdminAuditService
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type class.
 * @param <R> Resource class.
 * 
 * @see IFindRevisionAdminAuditService
 * @see IMapperFunction
 */
public abstract class BaseFindRevisionAdminAuditService<T extends BaseAdminAudit, R extends BaseAdminAuditResource>
implements IFindRevisionAdminAuditService<T, R, Integer, Integer>, IMapperFunction<T, R> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Revision<Integer, R>> findLastChangeRevision(final Integer id) throws NotFoundAuditEntityException {
		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseFindRevisionAdminAuditService::findLastChangeRevision Finding a last change revision with id: {}",
				id);

		final Optional<Revision<Integer, T>> source = getAdminRepository().findLastChangeRevision(id);

		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseFindRevisionAdminAuditService::findLastChangeRevision Result -> {}", source);

		// Convert entity to resource
		return Optional
				.of(source
						.flatMap((input) -> (input == null) ? Optional.empty()
								: Optional.of(Revision.of(input.getMetadata(),
										mapperToResource().toResource(input.getEntity()))))
						.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Revision<Integer, R>> findRevision(final Integer id, final Integer revisionNumber)
			throws NotFoundAuditEntityException {

		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseFindRevisionAdminAuditService::findRevision Finding a revision with id: {} and revison number: {}",
				id,
				revisionNumber);

		final Optional<Revision<Integer, T>> source = getAdminRepository().findRevision(id, revisionNumber);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindRevisionAdminAuditService::findRevision Result -> {}",
				source);

		// Convert entity to resource
		return Optional
				.of(source
						.flatMap((input) -> (input == null) ? Optional.empty()
								: Optional.of(Revision.of(input.getMetadata(),
										mapperToResource().toResource(input.getEntity()))))
						.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Revisions<Integer, R> findRevisions(final Integer id) {

		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseFindRevisionAdminAuditService::findRevisions Finding revisions with id: {}",
				id);

		final Revisions<Integer, T> result = getAdminRepository().findRevisions(id);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindRevisionAdminAuditService::findRevisions Result -> {}",
				result);

		final List<Revision<Integer, R>> revisions = new ArrayList<>();

		result.getContent().forEach((input) -> {
			revisions.add(Revision.of(input.getMetadata(), mapperToResource().toResource(input.getEntity())));
		});

		return Revisions.of(revisions);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Revision<Integer, R>> findRevisions(final Integer id, final Pageable pageable) {

		LoggerUtil.message(LogMessageLevel.INFO,
				"BaseFindRevisionAdminAuditService::findRevisions Finding revisions with id: {} and page: {}", id,
				pageable);

		final Page<Revision<Integer, T>> result = getAdminRepository().findRevisions(id, pageable);

		LoggerUtil.message(LogMessageLevel.INFO, "BaseFindRevisionAdminAuditService::findRevisions Result -> {}",
				result);

		final List<Revision<Integer, R>> revisions = new ArrayList<>();

		result.getContent().forEach((input) -> {
			revisions.add(Revision.of(input.getMetadata(), mapperToResource().toResource(input.getEntity())));
		});

		return new PageImpl<>(revisions, pageable, result.getTotalElements());
	}

	/**
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminAuditRepository<T> getAdminRepository();


}
