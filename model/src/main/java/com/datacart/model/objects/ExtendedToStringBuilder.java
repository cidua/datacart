package com.datacart.model.objects;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
public class ExtendedToStringBuilder extends ReflectionToStringBuilder {
	private Class<? extends Annotation>[] excludeAnnotations;

	public ExtendedToStringBuilder(Object object) {
		super(object);
	}

	/**
	 * Builds a String for a toString method excluding the given field names.
	 *
	 * @param object             The object to "toString".
	 * @param excludeFieldNames  The field names to exclude
	 * @param excludeAnnotations The annotations which marks files should be excluded.
	 * @return The toString value.
	 */
	public static String reflectionToString(Object object, String[] excludeFieldNames, Class<? extends Annotation>... excludeAnnotations) {
		ExtendedToStringBuilder builder = new ExtendedToStringBuilder(object);
		builder.setExcludeAnnotations(excludeAnnotations);
		builder.setExcludeFieldNames(excludeFieldNames);

		return builder.toString();
	}

	public Class<? extends Annotation>[] getExcludeAnnotations() {
		return excludeAnnotations;
	}

	public void setExcludeAnnotations(Class<? extends Annotation>[] excludeAnnotations) {
		if (excludeAnnotations == null) {
			this.excludeAnnotations = null;
		} else {
			this.excludeAnnotations = noNullArrayClass(excludeAnnotations);
		}

		this.excludeAnnotations = excludeAnnotations;
	}

	@Override
	protected boolean accept(Field field) {
		if (super.accept(field)) {
			if (hasAnyAnnotation(field, excludeAnnotations)) {
				return false;
			}
		}

		return true;
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

	protected static Class[] noNullArrayClass(Class[] array) {
		List<Class> list = new ArrayList<Class>(array.length);
		for (Class e : array) {
			if (e != null) {
				list.add(e);
			}
		}
		return list.toArray(new Class[list.size()]);
	}
}
