package com.datacart.model.dao.spi;

import com.datacart.model.objects.UniqueEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public interface GenericReadOnlyDao<T> {
	/**
	 * Get entity by primary key
	 *
	 * @param key - entity primary key
	 * @return entity by key value
	 */
	T getEntity(Serializable key);

	/**
	 * Find all entity
	 *
	 * @return List of entities
	 */
	List<T> findAll();

	/**
	 * Find entities by list of id field
	 *
	 * @return List of entities
	 */
	<K extends Serializable> List<T> findByIds(List<K> ids);

	/**
	 * Get entity count
	 *
	 * @return count of entities
	 */
	Long getCount();

	/**
	 * Get entity count
	 *
	 * @return count of entities for specified tenant
	 */
	Long getCount(Long tenantId);

	/**
	 * Get entity count
	 *
	 * @return count of entities
	 */
	Long getCount(UniqueEntity parent);

	/**
	 * Get entity count
	 *
	 * @param detachedCriteria criteria to meet
	 * @return count of entities
	 */
	Long getCount(DetachedCriteria detachedCriteria);

	/**
	 * Get entity count
	 *
	 * @param tenantId         Id of the tenant which is used to contact concrete schema
	 * @param detachedCriteria criteria to meet
	 * @return count of entities
	 */
	Long getCount(Long tenantId, DetachedCriteria detachedCriteria);

	/**
	 * Find entities
	 *
	 * @param detachedCriteria - DetachedCriteria to find
	 * @return List of entities
	 */
	List<T> find(DetachedCriteria detachedCriteria);

	/**
	 * @param detachedCriteria - DetachedCriteria to find
	 * @param maxResults       max results to find
	 * @return List of entities
	 */
	List<T> find(DetachedCriteria detachedCriteria, int maxResults);

	/**
	 * @param detachedCriteria - DetachedCriteria to find
	 * @param firstResult      first result to find
	 * @param maxResults       max results to find
	 * @return List of entities
	 */
	List<T> find(DetachedCriteria detachedCriteria, int firstResult, int maxResults);

	/**
	 * @param detachedCriteria  - DetachedCriteria to find
	 * @param firstResult       first result to find
	 * @param maxResults        max results to find
	 * @param resultTransformer transformer type
	 * @return List of entities
	 */
	List<T> find(DetachedCriteria detachedCriteria, int firstResult, int maxResults, ResultTransformer resultTransformer);

	/**
	 * Get unique entity
	 *
	 * @return List of entities
	 */
	T getUnique(DetachedCriteria detachedCriteria);

	/**
	 * @return Collection of all entityId elements
	 */
	List<Long> getIds();
}
