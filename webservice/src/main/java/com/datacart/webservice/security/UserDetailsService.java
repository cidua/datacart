package com.datacart.webservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
* @author Dmitriy Sinenkiy
* @since 1.0
*/
@Service
public class UserDetailsService {
	/**
	 * Return current user from <code>SecurityContextHolder</code>.
	 *
	 * @return current user
	 */
	public DatacartUserDetails getCurrentUser() {
		final SecurityContext context = SecurityContextHolder.getContext();
		final Authentication authentication = context.getAuthentication();
		return (DatacartUserDetails) authentication.getPrincipal();
	}
}
