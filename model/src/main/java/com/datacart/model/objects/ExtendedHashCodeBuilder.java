package com.datacart.model.objects;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public class ExtendedHashCodeBuilder {
	/**
	 * Builds a int value for a hashCode method excluding the given field names and annotations.
	 *
	 * @param object             The object get hashCode
	 * @param excludeFieldNames  The field names to exclude
	 * @param excludeAnnotations The annotations which marks fields should be excluded.
	 * @return hashCode value
	 */
	public static int reflectionHashCode(Object object, Collection<String> excludeFieldNames, Class<? extends Annotation>... excludeAnnotations) {
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

		return HashCodeBuilder.reflectionHashCode(object, excludedFieldsNamesSet);
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
