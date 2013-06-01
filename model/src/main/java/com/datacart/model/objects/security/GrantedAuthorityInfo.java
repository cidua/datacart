package com.datacart.model.objects.security;

import com.datacart.model.objects.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Entity
@Table(name = "grantedauthoritiesinfo")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class GrantedAuthorityInfo extends AbstractEntity implements GrantedAuthority {

	private static final long serialVersionUID = -5706169486745581159L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GrantedAuthorityInfoSeq")
	@SequenceGenerator(name = "GrantedAuthorityInfoSeq", sequenceName = "grantedauthoritiesinfo_id_seq", allocationSize = 1)
	private Long id;

	public Long getId() {
		return id;
	}
}
