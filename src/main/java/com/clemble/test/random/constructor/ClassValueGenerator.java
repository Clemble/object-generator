package com.clemble.test.random.constructor;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Callable;

/**
 * Generates random value of specified Class.
 * 
 * @author Anton Oparin
 *
 * @param <T> parameterized {@link Class}.
 */
public class ClassValueGenerator<T> implements Callable<T> {

    /**
     * {@link ClassConstructor} used for value construction.
     */
    final private ClassConstructor<T> objectConstructor;
    /**
     * {@link ClassPropertySetter} used for properties population.
     */
    final private ClassPropertySetter<T> propertySetter;

    /**
     * Default constructor.
     * 
     * @param objectConstructor {@link ClassConstructor} to use.
     * @param propertySetter {@link ClassPropertySetter} to use.
     */
    public ClassValueGenerator(final ClassConstructor<T> objectConstructor, final ClassPropertySetter<T> propertySetter) {
        this.objectConstructor = checkNotNull(objectConstructor);
        this.propertySetter = checkNotNull(propertySetter);
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public T call() {
        // Step 1. Generating random Object
        Object generatedObject = objectConstructor.construct();
        // Step 2. Setting properties to the Object
        propertySetter.setProperties(generatedObject);
        // Step 3. Generated Object can be used
        return (T) generatedObject;
    }
    
//    @Override
//    public int scope(){
//    	int totalSize = 1;
//    	// Step 1. Calculating scope of constructor
//    	List<ValueGenerator<?>> constuctorGenerators = objectConstructor.getValueGenerators();
//    	for(ValueGenerator<?> generator: constuctorGenerators) {
//    		totalSize = totalSize * Math.max(1, generator.scope());
//    	}
//    	// Step 2. Calculating scope of properties
//    	constuctorGenerators = propertySetter != null ? propertySetter.getValueGenerators() : Collections.<ValueGenerator<?>>emptyList();
//    	for(ValueGenerator<?> generator: constuctorGenerators) {
//    		totalSize = totalSize * Math.max(1, generator.scope());
//    	}
//    	// Step 3. Returning final result
//    	return totalSize;
//    }

    /**
     * Returns associated {@link ClassConstructor}.
     * 
     * @return associated {@link ClassConstructor}.
     */
    public ClassConstructor<T> getObjectConstructor() {
        return objectConstructor;
    }

    /**
     * Returns associated {@link ClassPropertySetter}.
     * 
     * @return associated {@link ClassPropertySetter}.
     */
    public ClassPropertySetter<T> getPropertySetter() {
        return propertySetter;
    }
    
}
