package com.datacart.model.dao.impl.common;

import com.datacart.model.dao.impl.GenericDaoImpl;
import com.datacart.model.dao.spi.common.UserTenantRelationDao;
import com.datacart.model.objects.common.UserTenantRelation;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Repository
public class UserTenantRelationDaoImpl extends GenericDaoImpl<UserTenantRelation> implements UserTenantRelationDao {
	@Override
	protected Session getCurrentSession() {
		return sessions.getCommonSession();
	}
}
