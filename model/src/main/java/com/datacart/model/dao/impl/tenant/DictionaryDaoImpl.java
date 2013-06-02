package com.datacart.model.dao.impl.tenant;

import com.datacart.model.dao.impl.GenericDaoImpl;
import com.datacart.model.dao.spi.tenant.DictionaryDao;
import com.datacart.model.objects.tenants.Dictionary;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Repository
public class DictionaryDaoImpl extends GenericDaoImpl<Dictionary> implements DictionaryDao {
	@Override
	protected Session getCurrentSession() {
		return sessions.getTenantSession();
	}
}
