package sml.instructions;

import sml.Instruction;
import sml.Machine;

import java.lang.reflect.Constructor;

/**
 * Helper class used by the provided instruction unit tests.
 *
 * This file should be placed in:
 *   sml/src/test/java/sml/instructions/InstructionTestSupport.java
 */
final class InstructionTestSupport {

    private InstructionTestSupport() {
        // Utility class: do not instantiate.
    }

    static Machine machine() {
        return new Machine();
    }

    static Instruction instruction(String name, int... args) {
        try {
            String className = "sml.instructions." + name + "Instruction";
            Class<?> clazz = Class.forName(className);

            Class<?>[] parameterTypes = new Class<?>[args.length + 1];
            Object[] values = new Object[args.length + 1];

            parameterTypes[0] = String.class;
            values[0] = "test";

            for (int i = 0; i < args.length; i++) {
                parameterTypes[i + 1] = int.class;
                values[i + 1] = args[i];
            }

            Constructor<?> constructor = clazz.getConstructor(parameterTypes);
            return (Instruction) constructor.newInstance(values);

        } catch (Exception e) {
            throw new RuntimeException("Could not create instruction: " + name, e);
        }
    }
}
