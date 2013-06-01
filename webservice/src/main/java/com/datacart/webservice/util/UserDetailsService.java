//package com.datacart.webservice.util;
//
//import com.datacart.model.objects.security.GrantedAuthorityInfo;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.ObjectUtils;
//import org.apache.commons.lang.time.DateUtils;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Dmitriy Sinenkiy
// * @since 1.0
// */
//@Service
//public class UserDetailsService {
//	@Resource
//	private TokenDao tokenDao;
//
//	/**
//	 * Return current user from <code>SecurityContextHolder</code>.
//	 *
//	 * @return current user
//	 */
//	public IsDeskUserDetails getCurrentUser() {
//		final SecurityContext context = SecurityContextHolder.getContext();
//		final Authentication authentication = context.getAuthentication();
//		IsDeskUserDetails principal = ( IsDeskUserDetails ) authentication.getPrincipal();
//		return principal;
//	}
//
//	public AccessToken findLatestShareToken( Long currentUserId, Long shareUserId, GrantedAuthorityInfo grantedAuthorityInfo, boolean strictEntity ) {
//		List<AccessToken> accessTokens = getValidShareTokens( currentUserId, shareUserId, grantedAuthorityInfo, strictEntity );
//
//		if ( CollectionUtils.isNotEmpty(accessTokens) ) {
//			return accessTokens.get( 0 );
//		} else {
//			return null;
//		}
//	}
//
//	public List<AccessToken> getValidShareTokens( Long currentUserId, Long shareUserId, GrantedAuthorityInfo grantedAuthorityInfo, boolean strictEntity ) {
//		DetachedCriteria dc = DetachedCriteria.forClass( AccessToken.class )
//				.createAlias( "grantedAuthorityInfos", "grantedAuthorityInfos" );
//		dc.add( Restrictions.eq("invalid", false) );
//
//		if ( ObjectUtils.equals(currentUserId, shareUserId) ) {
//			dc.add( Restrictions.or(
//					Restrictions.eq( "user.id", currentUserId ),
//					Restrictions.eq( "shareUser.id", shareUserId )
//			) );
//		} else {
//			if ( currentUserId != null ) {
//				dc.add( Restrictions.eq( "user.id", currentUserId ) );
//			}
//			if ( shareUserId != null ) {
//				dc.add( Restrictions.eq( "shareUser.id", shareUserId ) );
//			}
//		}
//
//		Long entityId = null;
//		if ( grantedAuthorityInfo instanceof GrantedRole ) {
//			dc.add( Restrictions.eq( "grantedAuthorityInfos.class", GrantedRole.class ) );
//			dc.add( Restrictions.eq( "grantedAuthorityInfos.roleName", grantedAuthorityInfo.getAuthority() ) );
//
//			entityId = ( ( GrantedRole ) grantedAuthorityInfo ).getEntityId();
//		}
//
//		if ( strictEntity ) {
//			if ( entityId != null ) {
//				dc.add( Restrictions.eq( "grantedAuthorityInfos.entityId", entityId ) );
//			} else {
//				dc.add( Restrictions.isNull( "grantedAuthorityInfos.entityId" ) );
//			}
//		}
//
//		dc.add( Restrictions.eq( "type", TokenType.SHARE ) );
//
//		dc.addOrder( Order.desc("id") );
//
//		List<AccessToken> accessTokens = tokenDao.find( dc );
//
//		List<AccessToken> validAccessTokens = new ArrayList<AccessToken>( accessTokens.size() );
//		for ( AccessToken accessToken : accessTokens ) {
//			if ( accessToken.getInvalidatedAt() != null ) {
//				Date sharedExpiryDate = accessToken.getInvalidatedAt();
//				Date now = new Date();
//
//				if ( now.before( sharedExpiryDate ) || DateUtils.isSameDay(now, sharedExpiryDate) ) {
//					validAccessTokens.add( accessToken );
//				} else {
//					accessToken.setInvalid( true );
//					tokenDao.save( accessToken );
//				}
//			} else {
//				validAccessTokens.add( accessToken );
//			}
//		}
//
//		return validAccessTokens;
//	}
//}
