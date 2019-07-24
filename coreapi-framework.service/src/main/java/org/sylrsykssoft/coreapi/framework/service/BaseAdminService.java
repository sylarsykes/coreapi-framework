package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.sylrsykssoft.coreapi.framework.api.model.BaseAdmin;
import org.sylrsykssoft.coreapi.framework.api.resource.BaseAdminResource;
import org.sylrsykssoft.coreapi.framework.database.exception.IncorrectResultSizeException;
import org.sylrsykssoft.coreapi.framework.database.exception.NotFoundEntityException;
import org.sylrsykssoft.coreapi.framework.database.repository.BaseAdminRepository;
import org.sylrsykssoft.coreapi.framework.library.mapper.IMapperFunction;

/**
 * BaseAdminService service.
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 * @param <T> Type class.
 */
public abstract class BaseAdminService<T extends BaseAdmin, R extends BaseAdminResource> implements IAdminService<T, R, Integer>, IMapperFunction<T, R> {

	/**
	 * Getter admin repository implementation
	 * 
	 * @return BaseAdminRepository<T>
	 */
	public abstract BaseAdminRepository<T> getAdminRepository(); 

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(Integer id) throws NotFoundEntityException {
		final Optional<T> source = getAdminRepository().findById(id);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().apply(input)))
				.orElseThrow(NotFoundEntityException::new));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByName(final String name) throws NotFoundEntityException, IncorrectResultSizeException {
		final Optional<T> source = getAdminRepository().findByName(name);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().apply(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByExample(Example<R> example) throws NotFoundEntityException, IncorrectResultSizeException {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());
		
		final Optional<T> source = getAdminRepository().findOne(exampleToFind);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().apply(input)))
				.orElseThrow(NotFoundEntityException::new));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllById(Iterable<Integer> ids) {
		final List<T> sources = getAdminRepository().findAllById(ids);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAll() {
		final List<T> sources = getAdminRepository().findAll();
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllByExample(Example<R> example) {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());
		
		final List<T> sources = getAdminRepository().findAll(exampleToFind);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllByExampleSortable(Example<R> example, Sort sort) {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());
		
		final List<T> sources = getAdminRepository().findAll(exampleToFind, sort);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return getAdminRepository().count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsById(Integer id) {
		return getAdminRepository().existsById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R save(final R entity) throws NotFoundEntityException {
		if (entity.getEntityId() != null && !existsById(entity.getEntityId())) {
			throw new NotFoundEntityException();
		}
		
		final T source = getAdminRepository().save(mapperToEntity().apply(entity));
		// Convert entity to resource
		return mapperToResource().apply(source);
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
				.map(mapperToResource()::apply)
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(Integer id) throws NotFoundEntityException {
		getAdminRepository().deleteById(id);
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
		getAdminRepository().delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll(Iterable<? extends R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
				.collect(Collectors.toList());
		
		getAdminRepository().deleteAll(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		getAdminRepository().deleteAll();
	}

}
