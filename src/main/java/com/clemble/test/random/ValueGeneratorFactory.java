package com.clemble.test.random;

import java.util.Collection;
import java.util.concurrent.Callable;

import com.clemble.test.random.constructor.ClassPropertySetterRegistry;

/**
 * 
 * Factory for ValueGenerators
 * 
 * @author Anton Oparin
 * 
 */
public interface ValueGeneratorFactory {

    /**
     * Produces {@link Callable} for specified {@link Class}.
     *
     * @param <T> the type of object to generate
     * @param klass
     *            generated {@link Class}
     * @return {@link Callable} for procided {@link Class}
     */
    public <T> Callable<T> getValueGenerator(Class<T> klass);

    /**
     * Produces {@link Collection} of {@link Callable} for provided {@link Class}es.
     * 
     * @param parameters
     *            Collection of {@link Class}s to generate.
     * @return {@link Collection} of {@link Callable} to use.
     */
    public Collection<Callable<?>> getValueGenerators(Class<?>[] parameters);

    public ClassPropertySetterRegistry getPropertySetterManager();

    public <T> void put(Class<T> klass, Callable<T> valueGenerator);

}
