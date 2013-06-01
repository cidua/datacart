package com.datacart.model.dao.impl;

import org.hibernate.Session;

/**
 * @author Dmitriy Sinenkiy - Team International
 * @since 2.0
 */
public interface SessionInitializer {
	void init(Session session);
}
