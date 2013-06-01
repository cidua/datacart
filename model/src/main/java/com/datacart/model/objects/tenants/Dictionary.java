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
@Table(name = "dictionary")
public class Dictionary extends AbstractEntity implements UniqueEntity {
	private static final long serialVersionUID = 4877876633763453512L;

	@Id
	@SequenceGenerator(name = "DictionarySeq", sequenceName = "dictionary_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DictionarySeq")
	private Long id;

	private String word;

	private String spelling;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSpelling() {
		return spelling;
	}

	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}
}
