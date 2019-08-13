package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseEntity;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseEntityResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
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

	/**
	 * Getter repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseEntityRepository<T> getEntityRepository();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(Long id) throws NotFoundEntityException {
		final Optional<T> source = getEntityRepository().findById(id);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().toResource(input)))
				.orElseThrow(NotFoundEntityException::new));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByExample(Example<R> example) throws NotFoundEntityException, IncorrectResultSizeException {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());
		
		final Optional<T> source = getEntityRepository().findOne(exampleToFind);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().toResource(input)))
				.orElseThrow(NotFoundEntityException::new));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllById(Iterable<Long> ids) {
		final List<T> sources = getEntityRepository().findAllById(ids);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAll() {
		final List<T> sources = getEntityRepository().findAll();
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllByExample(Example<R> example) {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());
		
		final List<T> sources = getEntityRepository().findAll(exampleToFind);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllByExampleSortable(Example<R> example, Sort sort) {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());
		
		final List<T> sources = getEntityRepository().findAll(exampleToFind, sort);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::toResource).collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return getEntityRepository().count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsById(Long id) {
		return getEntityRepository().existsById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R create(final R entity) throws NotFoundEntityException {
		final T source = getEntityRepository().save(mapperToEntity().apply(entity));
		// Convert entity to resource
		return mapperToResource().toResource(source);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public R update(final R entity) throws NotFoundEntityException {
		if (entity.getEntityId() == null) {
			throw new NotFoundEntityException();
		}
		
		final T source = getEntityRepository().save(mapperToEntity().apply(entity));
		// Convert entity to resource
		return mapperToResource().toResource(source);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> saveAll(final Iterable<R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
				.collect(Collectors.toList());
		
		// Convert entity to resource
		return StreamSupport.stream(entities.spliterator(), false)
				.map(mapperToResource()::toResource)
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(Long id) throws NotFoundEntityException {
		getEntityRepository().deleteById(id);
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
		getEntityRepository().delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll(Iterable<? extends R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
				.collect(Collectors.toList());
		
		getEntityRepository().deleteAll(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		getEntityRepository().deleteAll();
	}
	
}
