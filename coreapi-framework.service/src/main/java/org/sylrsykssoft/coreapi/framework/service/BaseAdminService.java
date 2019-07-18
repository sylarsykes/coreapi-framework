package org.sylrsykssoft.coreapi.framework.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

	@Autowired
	protected BaseAdminRepository<T> superAdminRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findByName(final String name) throws NotFoundEntityException, IncorrectResultSizeException {
		final Optional<T> source = superAdminRepository.findByName(name);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().apply(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findById(Integer id) throws NotFoundEntityException {
		final Optional<T> source = superAdminRepository.findById(id);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().apply(input)))
				.orElseThrow(NotFoundEntityException::new));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public R getOne(Integer id) throws NotFoundEntityException {
		final T source = superAdminRepository.getOne(id);
		// Convert entity to resource
		return mapperToResource().apply(source);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<R> findOne(Example<R> example) throws NotFoundEntityException, IncorrectResultSizeException {
		final T entity = mapperToEntity().apply(example.getProbe());
		final Example<T> exampleToFind = Example.of(entity, example.getMatcher());
		
		final Optional<T> source = superAdminRepository.findOne(exampleToFind);
		// Convert entity to resource
		return Optional.of(source.flatMap(
				(input) -> (input == null) ? Optional.empty() : Optional.of(mapperToResource().apply(input)))
				.orElseThrow(NotFoundEntityException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAll() {
		final List<T> sources = superAdminRepository.findAll();
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<R> findAllById(Iterable<Integer> ids) {
		final List<T> sources = superAdminRepository.findAllById(ids);
		// Convert entity to resource
		return sources.stream().map(mapperToResource()::apply).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return superAdminRepository.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsById(Integer id) {
		return superAdminRepository.existsById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R save(final R entity) throws NotFoundEntityException {
		if (entity.getEntityId() != null && !existsById(entity.getEntityId())) {
			throw new NotFoundEntityException();
		}
		
		final T source = superAdminRepository.save(mapperToEntity().apply(entity));
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
		superAdminRepository.deleteById(id);
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
		superAdminRepository.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll(Iterable<? extends R> sources) throws NotFoundEntityException {
		final Iterable<T> entities = StreamSupport.stream(sources.spliterator(), false)
				.map(mapperToEntity()::apply)
				.collect(Collectors.toList());
		
		superAdminRepository.deleteAll(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		superAdminRepository.deleteAll();
	}

}
