package com.datacart.model.dao.impl;

import com.datacart.model.dao.spi.GenericDao;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy - Team International
 * @since 2.0
 */

public abstract class GenericDaoImpl<T> extends GenericReadOnlyDaoImpl<T> implements GenericDao<T> {
	public GenericDaoImpl() {
		super();
	}

	/**
	 * @return first closest hibernate entity, from parametrized parent DAO class.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Class<T> getGenericParentClass() {
		Type entityType = getClass().getGenericSuperclass();
		while (entityType != null) {
			if (entityType instanceof ParameterizedType) {
				ParameterizedType parameterizedType = ((ParameterizedType) entityType);

				if (parameterizedType.getRawType() == GenericDaoImpl.class) {
					final Type[] actualTypeArguments = ((ParameterizedType) entityType).getActualTypeArguments();
					return (Class<T>) actualTypeArguments[0];
				} else {
					entityType = parameterizedType.getRawType();
				}
			} else if (entityType instanceof Class) {
				entityType = ((Class<T>) entityType).getGenericSuperclass();
			}
		}

		throw new IllegalStateException("No generic super class was found for current DAO");
	}

	@Override
	public void save(T entity) {
		Session session = null;
		try {
			session = getCurrentSession();
			session.saveOrUpdate(entity);
		} catch (NonUniqueObjectException e) {
			session.merge(entity);
		}
	}

	@Override
	public void save(T entity, Long tenantId) {
		Session session = null;
		try {
			session = sessions.getTenantSession(tenantId);
			session.saveOrUpdate(entity);
		} catch (NonUniqueObjectException e) {
			session.merge(entity);
		}
	}

	@Override
	public void saveBatch(Collection<T> entities) {
		Session session = getCurrentSession();
		int i = 0;
		for (T entityTree : entities) {
			session.save(entityTree);

			if (i++ % 100 == 0) { // same as the JDBC batch size
				// flush a batch of inserts and release memory:
				session.flush();
				session.clear();
			}
		}
	}

	@Override
	public void saveOrUpdateBatch(Collection<T> entities) {
		Session session = null;
		try {
			session = getCurrentSession();
			int i = 0;
			for (T entityTree : entities) {
				session.saveOrUpdate(entityTree);

				if (i++ % 100 == 0) { // same as the JDBC batch size
					// flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
		} finally {
			if (session != null) {
				session.flush();
				session.clear();
			}
		}
	}

	@Override
	public void delete(Serializable key) {
		Session session = getCurrentSession();

		Object entity = session.load(entityClass, key);
		session.delete(entity);
	}

	@Override
	public void delete(T object) {
		try {
			Session session = getCurrentSession();

			session.delete(object);
		} catch (HibernateException e) {
			logger.error(getClass().getName() + " delete(" + entityClass.getSimpleName() + " object) ");
			throw e;
		}
	}

	@Override
	public <K extends Serializable> void deleteByIds(List<K> ids) {
		try {
			Session session = getCurrentSession();
			Query query = session.createQuery("delete from " + entityClass.getName() + " obj where obj.id in (:ids)");
			query.executeUpdate();
		} catch (HibernateException e) {
			logger.error(getClass().getName() + " delete(" + entityClass.getSimpleName() + " object) ");
			throw e;
		}
	}

	@Override
	public void deleteAll() {
		getCurrentSession().createQuery("delete from " + entityClass.getName()).executeUpdate();
	}
}
