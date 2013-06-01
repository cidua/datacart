package com.datacart.model.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Component
public class Sessions {
	@Resource
	private SessionFactory commonSessionFactory;

	@Resource
	private SessionFactory tenantSessionFactory;

	@Qualifier("commonSessionInitializer")
	@Autowired(required = false)
	private SessionInitializer commonSessionInitializer;

	@Qualifier("tenantSessionInitializer")
	@Autowired(required = false)
	private SessionInitializer tenantSessionInitializer;

	public Session getCommonSession() {

		Session currentSession = commonSessionFactory.getCurrentSession();

		if (commonSessionInitializer != null) {
			commonSessionInitializer.init(currentSession);
		}

		return currentSession;
	}

	public Session getTenantSession() {
		Session currentSession = tenantSessionFactory.getCurrentSession();

		if (tenantSessionInitializer != null) {
			tenantSessionInitializer.init(currentSession);
		}

		return currentSession;
	}

	public Session getTenantsPublicSession() {
		Session currentSession = tenantSessionFactory.getCurrentSession();
		currentSession.createSQLQuery("SET search_path TO public").executeUpdate();
		return currentSession;
	}

	public Session createTenantSessionForUser( Long id ) {
		String schemaName = id == null ? null : "t" + id;

		Session currentSession = tenantSessionFactory.getCurrentSession();

		if ( StringUtils.isNotEmpty(schemaName) ) {
			currentSession.createSQLQuery( "SET search_path TO " + schemaName ).executeUpdate();
		}

		return currentSession;
	}
	public Session getTenantSessionForUser(Long id) {
		String schemaName = id == null ? null : "t" + id;

		Session currentSession = tenantSessionFactory.getCurrentSession();

		if (StringUtils.isNotEmpty(schemaName)) {
			currentSession.createSQLQuery("SET search_path TO " + schemaName).executeUpdate();
		}

		return currentSession;
	}

	public Session getTenantSession(Long id) {
		String schemaName = id == null ? null : "t" + id;

		return getTenantSession(schemaName);
	}

	public Session getTenantSession(String schemaName) {
		Session session = getTenantSession();

		session.flush();
		session.clear();

		if (StringUtils.isNotEmpty(schemaName)) {
			session.createSQLQuery("SET search_path TO " + schemaName).executeUpdate();
		}

		return session;
	}
}
