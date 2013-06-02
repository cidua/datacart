package com.datacart.model.objects.security;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Entity
@Table(name = "grantedrole")
public class GrantedRole extends GrantedAuthorityInfo {

	private static final long serialVersionUID = -5506956662081897109L;

	private String roleName;

	private Long entityId;

	public GrantedRole() {
	}

	public GrantedRole(String roleName) {
		this.roleName = roleName;
	}

	@Override
	@Transient
	public String getAuthority() {
		return roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		GrantedRole that = (GrantedRole) o;

		if (entityId != null ? !entityId.equals(that.entityId) : that.entityId != null) return false;
		if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
		result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
		return result;
	}
}
