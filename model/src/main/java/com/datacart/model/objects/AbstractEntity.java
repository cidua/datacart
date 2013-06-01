package com.datacart.model.objects;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public abstract class AbstractEntity implements Serializable{

	private static final long serialVersionUID = 2305400439738204043L;

	protected static final Class[] EXCLUDED_ANNOTATIONS = {
			ManyToOne.class, OneToOne.class, OneToMany.class, ManyToOne.class, ManyToMany.class
	};

	@Override
	@SuppressWarnings("unchecked")
	public int hashCode() {
		return ExtendedHashCodeBuilder.reflectionHashCode( this, null, EXCLUDED_ANNOTATIONS );
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		return ExtendedEqualsBuilder.reflectionEquals( this, obj, null, EXCLUDED_ANNOTATIONS );
	}

	@Override
	@SuppressWarnings("unchecked")
	public String toString() {
		return ExtendedToStringBuilder.reflectionToString( this, null, EXCLUDED_ANNOTATIONS );
	}
}
