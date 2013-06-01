package com.datacart.webservice.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public class IsDeskUserDetails implements UserDetails {
	private static final long serialVersionUID = -92330220360509027L;

//	private final List<GrantedAuthority> grantedAuthorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public String getPassword() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public String getUsername() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean isEnabled() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
