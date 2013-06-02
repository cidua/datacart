package com.datacart.webservice.security;

import com.datacart.model.objects.common.Tenant;
import com.datacart.model.objects.common.User;
import com.datacart.model.objects.security.AccessToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public class DatacartUserDetails implements UserDetails {
	private static final long serialVersionUID = -92330220360509027L;

	private final List<GrantedAuthority> grantedAuthorities;
	private final long userId;
	private final String password;
	private final String userName;
	private final String email;
	private final boolean isEnabled;
	private final Date created;
	private final Date validuntil;
	private final boolean invalid;
	private final Long tenantId;
	private final String token;

	public DatacartUserDetails(List<? extends GrantedAuthority> grantedAuthorities, Tenant tenant, AccessToken accessToken) {
		User user = accessToken.getUser();

		userId = user.getId();
		password = user.getPassword();
		userName = user.getDisplayName();
		email = user.getEmail();
		isEnabled = user.getEnabled();
		created = accessToken.getCreated();
		validuntil = accessToken.getValidUntil();
		invalid = accessToken.getInvalid();
		tenantId = tenant.getId();
		token = accessToken.getTokenId();

		List<GrantedAuthority> resultGrantedAuthorities = new ArrayList<GrantedAuthority>(grantedAuthorities);
		resultGrantedAuthorities.add(new UserGrantedAuthority("IS_FULLY_AUTHENTICATED"));

		this.grantedAuthorities = Collections.unmodifiableList(resultGrantedAuthorities);
	}

	public static String getSchema(Long tenantId) {
		return String.format("t%s", tenantId);
	}

	public String getSchema() {
		return getSchema(tenantId);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isEnabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isEnabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		Date now = new Date();
		return ((created.before(now)) && (validuntil.after(now))) && !invalid;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

	public long getUserId() {
		return userId;
	}
}
