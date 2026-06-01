package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MulInstructionTest {
    @Test
    void multiplyTwoRegistersAndStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 5);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(30, machine.registers().register(1));
    }

    @Test
    void supportsNegativeOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -5);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(-30, machine.registers().register(1));
    }


    @Test
    void mulIntMaxOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 2147483647);
        machine.registers().register(3, 2);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(-2, machine.registers().register(1));
    }

    @Test
    void mulIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, 2);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));
    }

    @Test
    void mulIntMaxIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 2147483647);
        machine.registers().register(3, -2147483648);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MIN_VALUE, machine.registers().register(1));
    }

    @Test
    void mulIntMinWithNegative() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, -1);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MIN_VALUE, machine.registers().register(1));
    }

    @Test
    void mulTwoIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, -2147483648);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));
    }

    @Test
    void mulWithZeroOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, 0);

        Instruction instruction = InstructionTestSupport.instruction("Mul", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));
    }
}
