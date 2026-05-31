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
}
