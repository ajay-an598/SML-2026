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
    void addsItselfAndStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 5);

        Instruction instruction = InstructionTestSupport.instruction("Add", 2, 2, 2);
        instruction.execute(machine);

        assertEquals(10, machine.registers().register(2));
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

        
    @Test
    void addIntMaxOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 2147483647);
        machine.registers().register(3, 1);

        Instruction instruction = InstructionTestSupport.instruction("Add", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MIN_VALUE, machine.registers().register(1));
    }

    @Test
    void addTwoIntMaxOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 2147483647);
        machine.registers().register(3, 2147483647);

        Instruction instruction = InstructionTestSupport.instruction("Add", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(-2, machine.registers().register(1));
    }

    @Test
    void addIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, -1);

        Instruction instruction = InstructionTestSupport.instruction("Add", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MAX_VALUE, machine.registers().register(1));
    }

    @Test
    void addTwoIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, -2147483648);

        Instruction instruction = InstructionTestSupport.instruction("Add", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));
    }

    @Test
    void addIntMaxAndIntMinOperands() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, -2147483648);
        machine.registers().register(3, 2147483647);

        Instruction instruction = InstructionTestSupport.instruction("Add", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(-1, machine.registers().register(1));
    }
        
}
