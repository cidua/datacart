package com.datacart.model.dao.impl.common;

import com.datacart.model.dao.impl.GenericDaoImpl;
import com.datacart.model.dao.spi.common.TenantDao;
import com.datacart.model.objects.common.Tenant;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Sinenkiy - Team International
 * @since 2.0
 */
@Repository
public class TenantDaoImpl extends GenericDaoImpl<Tenant> implements TenantDao{
	@Override
	protected Session getCurrentSession() {
		return null;
	}
}
