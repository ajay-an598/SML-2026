package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddInstructionTest {
    @Test
    void addsTwoRegistersAndStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 5);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Add", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(11, machine.registers().register(1));
    }

    @Test
    void supportsNegativeOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -5);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Add", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(1, machine.registers().register(1));
    }
}
