package com.datacart.model.objects.common;

import com.datacart.model.objects.AbstractEntity;
import com.datacart.model.objects.enums.UserTenantRelationStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Entity
@Table(name = "usertenantrelation")
public class UserTenantRelation extends AbstractEntity {
	private static final long serialVersionUID = 6628736699344427749L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserTenantRelationSeq")
	@SequenceGenerator(name = "UserTenantRelationSeq", sequenceName = "usertenantrelation_id_seq", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "tenantid")
	private Tenant tenant;

	@Enumerated(EnumType.STRING)
	private UserTenantRelationStatus status;

	private Calendar createDate;

	private Calendar acceptDate;

	Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public UserTenantRelationStatus getStatus() {
		return status;
	}

	public void setStatus(UserTenantRelationStatus status) {
		this.status = status;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Calendar getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Calendar acceptDate) {
		this.acceptDate = acceptDate;
	}
}
