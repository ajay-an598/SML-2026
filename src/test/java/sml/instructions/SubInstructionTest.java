package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubInstructionTest {
    @Test
    void subtractsTwoRegistersAndStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 5);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(-1, machine.registers().register(1));
    }

    @Test
    void supportsNegativeOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -5);
        machine.registers().register(3, 6);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(-11, machine.registers().register(1));
    }











    @Test
    void SubItselfAndStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 5);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 2, 2, 2);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(2));
    }
        
    @Test
    void subFromIntMaxOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 2147483647);
        machine.registers().register(3, -1);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MIN_VALUE, machine.registers().register(1));
    }

    @Test
    void subTwoIntMaxOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 2147483647);
        machine.registers().register(3, 2147483647);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));
    }

    @Test
    void subFromIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, 1);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MAX_VALUE, machine.registers().register(1));
    }

    @Test
    void subTwoIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, -2147483648);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));
    }

    @Test
    void subIntMaxFromIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, 2147483647);

        Instruction instruction = InstructionTestSupport.instruction("Sub", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(1, machine.registers().register(1));
    }
}
