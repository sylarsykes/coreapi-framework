package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseEntityRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;

/**
 * BaseService service.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type class.
 */
public abstract class BaseEntityService<T extends BaseEntity, R extends BaseEntityResource> implements IEntityService<T, R, Long>, IMapperFunction<T, R> {

	@Autowired
	private BaseEntityRepository<T> repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(Long id) throws NotFoundEntityException {
		final Optional<T> source = repository.findById(id);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().apply(input))).orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R getOne(Long id) throws NotFoundEntityException {
		final T source = repository.getOne(id);
		// Convert entity to resource
		return mapperToResource().apply(source);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAll() {
		final List<T> sources = repository.findAll();
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllById(Iterable<Long> ids) {
		final List<T> sources = repository.findAllById(ids);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return repository.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R save(final R entity) throws NotFoundEntityException {
		if (entity.getEntityId() != null && !existsById(entity.getEntityId())) {
			throw new NotFoundEntityException();
		}

		final T source = repository.save(mapperToEntity().apply(entity));
		// Convert entity to resource
		return mapperToResource().apply(source);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> saveAll(final Iterable<R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply).collect(Collectors.toList());

		// Convert entity to resource
		return StreamSupport.stream(entities.spliterator(), false).map(mapperToResource()::apply)
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(Long id) throws NotFoundEntityException {
		repository.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(R source) throws NotFoundEntityException {
		if (source.getEntityId() != null && !existsById(source.getEntityId())) {
			throw new NotFoundEntityException();
		}

		final T entity = mapperToEntity().apply(source);
		repository.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll(Iterable<? extends R> sources) throws NotFoundEntityException {
		// Definir Resource to entity
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply).collect(Collectors.toList());

		repository.deleteAll(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}
