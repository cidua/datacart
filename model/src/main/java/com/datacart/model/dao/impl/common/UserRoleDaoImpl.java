package com.datacart.model.dao.impl.common;

import com.datacart.model.dao.impl.GenericDaoImpl;
import com.datacart.model.dao.spi.common.UserRoleDao;
import com.datacart.model.objects.common.UserRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements UserRoleDao {
	@Override
	protected Session getCurrentSession() {
		return sessions.getCommonSession();
	}
}
