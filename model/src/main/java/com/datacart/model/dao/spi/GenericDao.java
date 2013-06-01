package com.datacart.model.dao.spi;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public interface GenericDao<T> extends GenericReadOnlyDao<T>{
	/**
	 * Save entity
	 *
	 * @param entity - entity to save
	 */
	void save(T entity);

	/**
	 * Save entity
	 *
	 * @param entity   - entity to save
	 * @param tenantId - tenantId
	 */
	void save(T entity, Long tenantId);

	/**
	 * Saves collection of entities using JDBC Bacth Insert
	 *
	 * @param entities - entities to be saved
	 */
	void saveBatch(Collection<T> entities);

	/**
	 * Saves or Updates collection of entities using JDBC Bacth Update
	 *
	 * @param entities - entities to be updated/saved
	 */
	void saveOrUpdateBatch(Collection<T> entities);

	/**
	 * Delete entity by key
	 *
	 * @param key - entity id to delete
	 */
	void delete(Serializable key);

	/**
	 * Delete entity by key
	 *
	 * @param object - entity to delete
	 */
	void delete(T object);

	/**
	 * Delete objects by their ids
	 *
	 * @param ids list of ids to delete
	 */
	<K extends Serializable> void deleteByIds(List<K> ids);

	/**
	 * Removes all records from the table
	 */
	void deleteAll();
}
