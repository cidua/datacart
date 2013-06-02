package com.datacart.model.objects.security;

import com.datacart.model.objects.AbstractEntity;
import com.datacart.model.objects.common.User;
import com.datacart.model.objects.enums.TokenType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Entity
@Table(name = "token")
public class AccessToken extends AbstractEntity {

	private static final long serialVersionUID = -1507883530604179632L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccessTokenSeq")
	@SequenceGenerator(name = "AccessTokenSeq", sequenceName = "token_id_seq", allocationSize = 1)
	private Long id;

	private String tokenId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accessUserId")
	private User user;

	private Date created;

	private Date validUntil;

	@Column(nullable = false)
	private Boolean invalid;

	@Enumerated(EnumType.STRING)
	private TokenType type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentid")
	private AccessToken parentToken;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<GrantedAuthorityInfo> grantedAuthorityInfos = new HashSet<GrantedAuthorityInfo>();

	private Date invalidatedAt;

	public AccessToken() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public Boolean getInvalid() {
		return invalid;
	}

	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public AccessToken getParentToken() {
		return parentToken;
	}

	public void setParentToken(AccessToken parentToken) {
		this.parentToken = parentToken;
	}

	public Set<GrantedAuthorityInfo> getGrantedAuthorityInfos() {
		return grantedAuthorityInfos;
	}

	public void setGrantedAuthorityInfos(Set<GrantedAuthorityInfo> grantedAuthorityInfos) {
		this.grantedAuthorityInfos = grantedAuthorityInfos;
	}

	public Date getInvalidatedAt() {
		return invalidatedAt;
	}

	public void setInvalidatedAt(Date invalidatedAt) {
		this.invalidatedAt = invalidatedAt;
	}
}
