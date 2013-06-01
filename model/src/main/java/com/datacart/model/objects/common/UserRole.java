package com.datacart.model.objects.common;

import com.datacart.model.objects.AbstractEntity;
import com.datacart.model.objects.enums.AccessRole;
import com.datacart.model.objects.UniqueEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Entity
@Table(name = "userrole")
public class UserRole extends AbstractEntity implements UniqueEntity {

	private static final long serialVersionUID = 7546755514135674795L;

	@Id
	@SequenceGenerator(name = "UserRoleSeq", sequenceName = "userrole_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserRoleSeq")
	private Long id;

	@Enumerated(EnumType.STRING)
	private AccessRole accessRole;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccessRole getAccessRole() {
		return accessRole;
	}

	public void setAccessRole(AccessRole accessRole) {
		this.accessRole = accessRole;
	}
}
