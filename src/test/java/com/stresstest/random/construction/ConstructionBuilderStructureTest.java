package com.stresstest.random.construction;

import java.util.Collection;

import org.junit.Assert;

import org.junit.Test;

import com.clemble.test.random.ObjectGenerator;
import com.clemble.test.random.ValueGenerator;
import com.clemble.test.random.ValueGeneratorFactory;
import com.clemble.test.random.constructor.ClassConstructorBuilder;
import com.clemble.test.random.constructor.ClassValueGenerator;
import com.clemble.test.random.generator.RandomValueGeneratorFactory;


@SuppressWarnings("unused")
public class ConstructionBuilderStructureTest {

    final private ValueGeneratorFactory valueGeneratorFactory = new RandomValueGeneratorFactory();

    private static class PrivateBuilderBasedClass {
        final private boolean data;

        private PrivateBuilderBasedClass(boolean dataValue) {
            this.data = dataValue;
        }

        private boolean getData() {
            return data;
        }

        private static class PrivateBuilderBasedClassBuilder {
            private boolean value;

            public PrivateBuilderBasedClass build() {
                return new PrivateBuilderBasedClass(value);
            }
        }

        private static PrivateBuilderBasedClass create(boolean value) {
            return new PrivateBuilderBasedClass(value);
        }

        public static PrivateBuilderBasedClassBuilder newBuilder() {
            return new PrivateBuilderBasedClassBuilder();
        }
    }

    @Test
    public void testPrivateBuilderConstructorUsed() {
        ValueGenerator<PrivateBuilderBasedClass> factoryGenerator = valueGeneratorFactory.getValueGenerator(PrivateBuilderBasedClass.class);
        ClassValueGenerator<PrivateBuilderBasedClass> classValueGenerator = (ClassValueGenerator<PrivateBuilderBasedClass>) factoryGenerator;
        Assert.assertTrue(classValueGenerator.getObjectConstructor() instanceof ClassConstructorBuilder);
        Assert.assertNotNull(classValueGenerator.getPropertySetter());
    }

    protected static class ProtectedBuilderBasedClass {
        final private boolean data;

        protected ProtectedBuilderBasedClass(boolean dataValue) {
            this.data = dataValue;
        }

        protected boolean getData() {
            return data;
        }

        protected static class ProtectedBuilderBasedClassBuilder {
            protected boolean value;

            public ProtectedBuilderBasedClass build() {
                return new ProtectedBuilderBasedClass(value);
            }
        }

        private static ProtectedBuilderBasedClass create(boolean value) {
            return new ProtectedBuilderBasedClass(value);
        }

        public static ProtectedBuilderBasedClassBuilder newBuilder() {
            return new ProtectedBuilderBasedClassBuilder();
        }
    }

    @Test
    public void testProtectedBuilderConstructorUsed() {
        ValueGenerator<ProtectedBuilderBasedClass> factoryGenerator = valueGeneratorFactory.getValueGenerator(ProtectedBuilderBasedClass.class);
        ClassValueGenerator<ProtectedBuilderBasedClass> classValueGenerator = (ClassValueGenerator<ProtectedBuilderBasedClass>) factoryGenerator;
        Assert.assertTrue(classValueGenerator.getObjectConstructor() instanceof ClassConstructorBuilder);
        Assert.assertNotNull(classValueGenerator.getPropertySetter());
    }

    static class DefaultBuilderBasedClass {
        final private boolean data;

        protected DefaultBuilderBasedClass(boolean dataValue) {
            this.data = dataValue;
        }

        boolean getData() {
            return data;
        }

        static class DefaultBuilderBasedClassBuilder {
            protected boolean value;

            public DefaultBuilderBasedClass build() {
                return new DefaultBuilderBasedClass(value);
            }
        }

        private static DefaultBuilderBasedClass create(boolean value) {
            return new DefaultBuilderBasedClass(value);
        }

        public static DefaultBuilderBasedClassBuilder newBuilder() {
            return new DefaultBuilderBasedClassBuilder();
        }
    }

    @Test
    public void testDefaultBuilderConstructorUsed() {
        ValueGenerator<DefaultBuilderBasedClass> factoryGenerator = valueGeneratorFactory.getValueGenerator(DefaultBuilderBasedClass.class);
        ClassValueGenerator<DefaultBuilderBasedClass> classValueGenerator = (ClassValueGenerator<DefaultBuilderBasedClass>) factoryGenerator;
        Assert.assertTrue(classValueGenerator.getObjectConstructor() instanceof ClassConstructorBuilder);
        Assert.assertNotNull(classValueGenerator.getPropertySetter());
    }

    public static class PublicBuilderBasedClass {
        final private boolean data;

        private PublicBuilderBasedClass(boolean dataValue) {
            this.data = dataValue;
        }

        public boolean getData() {
            return data;
        }

        public static class PublicBuilderBasedClassBuilder {
            private boolean value;

            public PublicBuilderBasedClass build() {
                return new PublicBuilderBasedClass(value);
            }
        }

        private static PublicBuilderBasedClass create(boolean value) {
            return new PublicBuilderBasedClass(value);
        }

        public static PublicBuilderBasedClassBuilder newBuilder() {
            return new PublicBuilderBasedClassBuilder();
        }
    }

    @Test
    public void testPublicBuilderConstructorUsed() {
        ValueGenerator<PublicBuilderBasedClass> factoryGenerator = valueGeneratorFactory.getValueGenerator(PublicBuilderBasedClass.class);
        ClassValueGenerator<PublicBuilderBasedClass> classValueGenerator = (ClassValueGenerator<PublicBuilderBasedClass>) factoryGenerator;
        Assert.assertTrue(classValueGenerator.getObjectConstructor() instanceof ClassConstructorBuilder);
        Assert.assertNotNull(classValueGenerator.getPropertySetter());
    }

    public static class UnconstructableBuilderBasedClass {
        final private boolean data;

        private UnconstructableBuilderBasedClass(boolean dataValue) {
            this.data = dataValue;
        }

        public boolean getData() {
            return data;
        }

        public static class UnconstructableBuilderBasedClassBuilder {
            private boolean value;
            private Collection<Integer> integer;

            private void setValue(int value) {
                throw new IllegalAccessError();
            }

            private void addInteger(int newInteger) {
                throw new IllegalAccessError();
            }

            public UnconstructableBuilderBasedClass build() {
                throw new IllegalAccessError();
            }
        }

        private static UnconstructableBuilderBasedClassBuilder newBuilder() {
            return new UnconstructableBuilderBasedClassBuilder();
        }
    }

    @Test
    public void testUnconstructableInitiation() {
        UnconstructableBuilderBasedClass result = ObjectGenerator.generate(UnconstructableBuilderBasedClass.class);
        Assert.assertNotNull(result);
    }

    public static class UnconstructableBuilderBasedClass2 {
        final private boolean data;

        private UnconstructableBuilderBasedClass2(boolean dataValue) {
            this.data = dataValue;
        }

        public boolean getData() {
            return data;
        }

        public static class UnconstructableBuilderBasedClassBuilder2 {
            private boolean value;
            private Collection<Integer> integer;

            private void setValue(int value) {
                throw new IllegalAccessError();
            }

            private void addInteger(int newInteger) {
                throw new IllegalAccessError();
            }

            public UnconstructableBuilderBasedClass2 build() {
                throw new IllegalAccessError();
            }
        }

        private static UnconstructableBuilderBasedClassBuilder2 newBuilder() {
            throw new IllegalAccessError();
        }
    }

    @Test
    public void testUnconstructableInitiation2() {
        UnconstructableBuilderBasedClass2 result = ObjectGenerator.generate(UnconstructableBuilderBasedClass2.class);
        Assert.assertNotNull(result);
    }
}
