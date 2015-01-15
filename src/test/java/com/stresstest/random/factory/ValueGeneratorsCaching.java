package com.stresstest.random.factory;

import org.junit.Assert;
import org.junit.Test;

import com.clemble.test.random.ValueGenerator;
import com.clemble.test.random.ValueGeneratorFactory;
import com.clemble.test.random.generator.CachedValueGeneratorFactory;
import com.clemble.test.random.generator.RandomValueGeneratorFactory;

@SuppressWarnings("all")
public class ValueGeneratorsCaching {

    final private ValueGeneratorFactory simpleValueGeneratorFactory = new RandomValueGeneratorFactory();
    final private CachedValueGeneratorFactory cachedValueGeneratorFactory = new CachedValueGeneratorFactory(simpleValueGeneratorFactory);

    static class IntRandomClass {
        private int randomClass;
    }

    @SuppressWarnings("unused")
    public static class BooleanRandomClass {
        private boolean randomBoolean;
    }

    @SuppressWarnings("unused")
    public static class CombinedRandomClass {
        private IntRandomClass intRandomClass;
        private BooleanRandomClass booleanRandomClass;
    }

    @Test
    public void testStandardGeneraterCreatedEachTime() {
        ValueGenerator<BooleanRandomClass> valueGenerator = simpleValueGeneratorFactory.getValueGenerator(BooleanRandomClass.class);
        ValueGenerator<BooleanRandomClass> anotherValueGenerator = simpleValueGeneratorFactory.getValueGenerator(BooleanRandomClass.class);
        Assert.assertNotSame(valueGenerator, anotherValueGenerator);
    }

    @Test
    public void testCachedGeneraterCreatedEachTime() {
        ValueGenerator<BooleanRandomClass> valueGenerator = cachedValueGeneratorFactory.getValueGenerator(BooleanRandomClass.class);
        ValueGenerator<BooleanRandomClass> anotherValueGenerator = cachedValueGeneratorFactory.getValueGenerator(BooleanRandomClass.class);
        Assert.assertEquals(valueGenerator, anotherValueGenerator);
    }

    @Test
    public void testCachedGeneraterReused() {
        ValueGenerator<BooleanRandomClass> booleanValueGenerator = cachedValueGeneratorFactory.getValueGenerator(BooleanRandomClass.class);
        Assert.assertEquals(booleanValueGenerator, cachedValueGeneratorFactory.getValueGenerator(BooleanRandomClass.class));

        ValueGenerator<IntRandomClass> intValueGenerator = cachedValueGeneratorFactory.getValueGenerator(IntRandomClass.class);
        Assert.assertEquals(intValueGenerator, cachedValueGeneratorFactory.getValueGenerator(IntRandomClass.class));

        ValueGenerator<CombinedRandomClass> combinedValueGenerator = cachedValueGeneratorFactory.getValueGenerator(CombinedRandomClass.class);
        Assert.assertEquals(combinedValueGenerator, cachedValueGeneratorFactory.getValueGenerator(CombinedRandomClass.class));
    }
}
