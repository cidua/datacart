package com.datacart.model.dao.impl.tenant;

import com.datacart.model.dao.impl.GenericDaoImpl;
import com.datacart.model.dao.spi.tenant.EncounterDao;
import com.datacart.model.objects.tenants.Encounter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Repository
public class EncounterDaoImpl extends GenericDaoImpl<Encounter> implements EncounterDao {
	@Override
	protected Session getCurrentSession() {
		return sessions.getTenantSession();
	}
}
