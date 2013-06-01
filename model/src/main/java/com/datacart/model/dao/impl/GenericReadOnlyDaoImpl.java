package com.datacart.model.dao.impl;

import com.datacart.model.dao.spi.GenericReadOnlyDao;
import com.datacart.model.objects.UniqueEntity;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public abstract class GenericReadOnlyDaoImpl<T> implements GenericReadOnlyDao<T> {
	@Resource
	protected Sessions sessions;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<T> entityClass;

	protected abstract Session getCurrentSession();

	public GenericReadOnlyDaoImpl() {
		this.entityClass = getGenericParentClass();
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getGenericParentClass() {
		Type entityType = getClass().getGenericSuperclass();
		while (entityType != null) {
			if (entityType instanceof ParameterizedType) {
				ParameterizedType parameterizedType = ((ParameterizedType) entityType);

				if (parameterizedType.getRawType() == GenericReadOnlyDaoImpl.class) {
					Type[] actualTypeArguments = ((ParameterizedType) entityType).getActualTypeArguments();

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
	@SuppressWarnings("unchecked")
	public T getEntity(Serializable id) {
		Session session = getCurrentSession();
		return (T) session.get(entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = getCurrentSession();
		return session.createQuery("from " + entityClass.getSimpleName() + " order by " + getOrderProperty()).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByIds(List ids) {
		String hql = "from " + entityClass.getName() + " e where e.id in (:ids) order by e.id";
		return getCurrentSession().createQuery(hql).list();
	}

	@Override
	public Long getCount() {
		Session session = getCurrentSession();
		return getCount(session);
	}

	@Override
	public Long getCount(Long tenantId) {
		if (tenantId == null) {
			throw new IllegalArgumentException("tenantId is not specified");
		}

		Session session = sessions.getTenantSession(tenantId);
		return getCount(session);
	}

	@Override
	public Long getCount(UniqueEntity parent) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Long getCount(DetachedCriteria detachedCriteria) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Long getCount(Long tenantId, DetachedCriteria detachedCriteria) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<T> find(DetachedCriteria detachedCriteria) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<T> find(DetachedCriteria detachedCriteria, int maxResults) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<T> find(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<T> find(DetachedCriteria detachedCriteria, int firstResult, int maxResults, ResultTransformer resultTransformer) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public T getUnique(DetachedCriteria detachedCriteria) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<Long> getIds() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@SuppressWarnings("unchecked")
	protected String getOrderProperty() {
		try {
			entityClass.getMethod("getEntityId");
			return "entityId";
		} catch (NoSuchMethodException e) {
		}

		try {
			entityClass.getMethod("getId");
			return "id";
		} catch (NoSuchMethodException e) {
		}

		throw new RuntimeException("Unable to get order property");
	}

	private Long getCount(Session session) {
		return (Long) session.createQuery("select count(e) from " + entityClass.getSimpleName() + " e").uniqueResult();
	}
}
