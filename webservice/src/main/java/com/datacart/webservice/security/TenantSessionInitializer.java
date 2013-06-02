package com.datacart.webservice.security;

import com.datacart.model.dao.impl.SessionInitializer;
import com.datacart.model.dao.impl.TenantHolder;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
* @author Dmitriy Sinenkiy
* @since 1.0
*/
@Component("tenantSessionInitializer")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class TenantSessionInitializer implements SessionInitializer, TenantHolder {
	@Resource
	private UserDetailsService userDetailsService;

	@Override
	public void init(Session session) {
		String schemaName = userDetailsService.getCurrentUser().getSchema();

		String currentSchema = (String) session.createSQLQuery("select current_schema()").uniqueResult();
		if (StringUtils.isNotEmpty(schemaName) && !schemaName.equals(currentSchema)) {
			session.createSQLQuery("SET search_path TO " + schemaName).executeUpdate();
		}
	}

	@Override
	public Long getTenantId() {
		return userDetailsService.getCurrentUser().getUserId();
	}
}
