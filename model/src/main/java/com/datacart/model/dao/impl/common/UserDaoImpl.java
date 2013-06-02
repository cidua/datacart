package com.datacart.model.dao.impl.common;

import com.datacart.model.dao.impl.GenericDaoImpl;
import com.datacart.model.dao.spi.common.UserDao;
import com.datacart.model.objects.common.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
	@Override
	protected Session getCurrentSession() {
		return sessions.getCommonSession();
	}
}
