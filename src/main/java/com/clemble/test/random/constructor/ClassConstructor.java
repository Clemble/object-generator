package com.clemble.test.random.constructor;


import java.lang.reflect.Modifier;
import java.util.List;

import com.clemble.test.random.ValueGenerator;
import com.clemble.test.random.ValueGeneratorFactory;

/**
 * Constructor of empty Objects, for {@link ClassValueGenerator}.
 * 
 * @author Anton Oparin
 * 
 * @param <T>
 *            parameterized {@link Class}.
 */
abstract public class ClassConstructor<T> {

    /**
     * Returns {@link Object} of defined type.
     * 
     * @return empty {@link Object} of defined type.
     */
    abstract public T construct();
    
    abstract public List<ValueGenerator<?>> getValueGenerators();
    
    abstract public ClassConstructor<T> clone(List<ValueGenerator<?>> generatorsToUse);

    /**
     * Generates {@link ClassConstructor}. It firstly checks Constructor, than FactoryMethod and the last is Builder based construction.
     * 
     * @param classToGenerate
     *            {@link Class} to generate.
     * @param valueGeneratorFactory
     *            {@link ValueGeneratorFactory} to use.
     * @return {@link ClassConstructor} if it is possible to generate one, <code>null</code> otherwise.
     */
	@SuppressWarnings({"unchecked"})
    public static <T> ClassConstructor<T> construct(final ClassAccessWrapper<?> classToGenerate, final ValueGeneratorFactory valueGeneratorFactory) {
        ClassConstructor<T> objectConstructor = null;
        if ((objectConstructor = ClassConstructorFactory.build(classToGenerate, valueGeneratorFactory)) != null && canConstruct(objectConstructor))
            return objectConstructor;
        if ((objectConstructor = ClassConstructorBuilder.build(classToGenerate, valueGeneratorFactory)) != null && canConstruct(objectConstructor))
            return objectConstructor;
        return (ClassConstructor<T>) ((classToGenerate.getModifiers() & Modifier.ABSTRACT) == 0 ? ClassConstructorSimple.build(classToGenerate, valueGeneratorFactory) : null);
    }

    private static boolean canConstruct(ClassConstructor<?> constructor) {
        try {
            constructor.construct();
            // Step 4.2. We were able to generate value, continue
            return true;
        } catch (Throwable throwable) {
        }
        return false;
    }

}
