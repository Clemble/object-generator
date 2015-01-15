package com.clemble.test.random.constructor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import com.clemble.test.random.ValueGenerator;

/**
 * Property setter implementation for a plain field.
 * 
 * @author Anton Oparin
 * 
 * @param <T>
 *            parameterized {@link Class}.
 */
final class ClassPropertySimpleSetter<T> extends ClassPropertySetter<T> {

	/**
	 * Field to reference.
	 */
	final Field field;
	/**
	 * Method assuming java bean naming, or field naming.
	 */
	final Method method;
	/**
	 * Value Generator to use to set the value property
	 */
	final private ValueGenerator<?> valueGenerator;

	/**
	 * Default constructor.
	 * 
	 * @param field
	 *            field to use as reference.
	 * @param method
	 *            method to use as reference.
	 * @param valueGenerator
	 *            ValueGenerator to use.
	 */
	ClassPropertySimpleSetter(final Field field, final Method method, final ValueGenerator<T> valueGenerator) {
		this.field = field;
		this.method = method;
		this.valueGenerator = valueGenerator;
	}

	@Override
	public void setProperties(final Object target) {
		Object valueToSet = valueGenerator.generate();
		// Step 1. Setting value, preferring method over field
		try {
			if (method != null) {
				method.invoke(target, valueToSet);
			} else {
				field.set(target, valueToSet);
			}
		} catch (Exception methodSetException) {
			// Step 3. Changing access level and making another attempt
			try {
				if (method != null) {
					method.setAccessible(true);
					method.invoke(target, valueToSet);
				} else {
					field.setAccessible(true);
					field.set(target, valueToSet);
				}
			} catch (Exception anotherMethodSetException) {
			}
		}
	}

	@Override
	protected Class<?> getAffectedClass() {
		return field != null ? field.getDeclaringClass() : method.getDeclaringClass();
	}

	@Override
	public String toString() {
		// DO NOT CHANGE !!! This is import, it will be used for comparison.
		return (field != null ? field.getName() : "-") + " / " + (method != null ? method.getName() : "-");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ValueGenerator<?>> getValueGenerators() {
		return (List<ValueGenerator<?>>)(List<?>) Collections.singletonList(valueGenerator);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ClassPropertySetter<T> clone(List<ValueGenerator<?>> generatorsToUse) {
		return new ClassPropertySimpleSetter<T>(field, method, (ValueGenerator<T>) generatorsToUse.remove(0));
	}

}