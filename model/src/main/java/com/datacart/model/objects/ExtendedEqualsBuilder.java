package com.datacart.model.objects;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public class ExtendedEqualsBuilder {
	/**
	 * Builds a boolean value for a equals method excluding the given field names and annotations.
	 *
	 * @param object             The object get equals
	 * @param excludeFieldNames  The field names to exclude
	 * @param excludeAnnotations The annotations which marks fields should be excluded.
	 * @return equals value
	 */
	public static boolean reflectionEquals(Object object, Object otherObject, Collection<String> excludeFieldNames, Class<? extends Annotation>... excludeAnnotations) {
		Set<String> excludedFieldsNamesSet = new HashSet<String>();
		if (excludeFieldNames != null && excludeFieldNames.size() > 0) {
			excludedFieldsNamesSet.addAll(excludeFieldNames);
		}

		if (excludeAnnotations != null && excludeAnnotations.length > 0) {
			Field[] declaredFields = object.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				if (hasAnyAnnotation(field, excludeAnnotations)) {
					excludedFieldsNamesSet.add(field.getName());
				}
			}
		}

		return EqualsBuilder.reflectionEquals(object, otherObject, excludedFieldsNamesSet);
	}

	protected static boolean hasAnnotation(Field field, Class<? extends Annotation> annotationClass) {
		return field.getAnnotation(annotationClass) != null;
	}

	protected static boolean hasAnyAnnotation(Field field, Class<? extends Annotation>... annotationClasses) {
		for (Class<? extends Annotation> annotationClass : annotationClasses) {
			if (hasAnnotation(field, annotationClass)) {
				return true;
			}
		}

		return false;
	}
}
