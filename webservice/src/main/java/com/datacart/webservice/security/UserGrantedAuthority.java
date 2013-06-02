package com.datacart.webservice.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public class UserGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 292618231874735220L;

	private final String grantedAuthority;

	public UserGrantedAuthority(String grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

	public String getAuthority() {
		return grantedAuthority;
	}
}
