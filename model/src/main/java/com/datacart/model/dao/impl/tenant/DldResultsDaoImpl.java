package com.datacart.model.dao.impl.tenant;

import com.datacart.model.dao.impl.GenericDaoImpl;
import com.datacart.model.dao.spi.tenant.DldResultsDao;
import com.datacart.model.objects.tenants.DldResults;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Repository
public class DldResultsDaoImpl extends GenericDaoImpl<DldResults> implements DldResultsDao {
	@Override
	protected Session getCurrentSession() {
		return sessions.getTenantSession();
	}
}
