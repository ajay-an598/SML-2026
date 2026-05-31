package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivInstructionTest {
    @Test
    void divideTwoRegistersAndStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 5);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Div", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));

        machine.registers().register(5, 7);
        machine.registers().register(6, 6);

        instruction = InstructionTestSupport.instruction("Div", 4, 5, 6);
        instruction.execute(machine);

        assertEquals(1, machine.registers().register(4));
    }

    @Test
    void supportsNegativeOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -50);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Div", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(-8, machine.registers().register(1));
    }
}
