package com.datacart.model.objects.tenants;

import com.datacart.model.objects.AbstractEntity;
import com.datacart.model.objects.UniqueEntity;

import javax.persistence.Entity;
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
@Table(name = "encounter")
public class Encounter extends AbstractEntity implements UniqueEntity {
	@Id
	@SequenceGenerator(name = "EncounterSeq", sequenceName = "encounter_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EncounterSeq")
	private Long id;

	//todo: to be defined entity structure

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
