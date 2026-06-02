package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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


    @Test
    void divideWithNegativeStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, Integer.MIN_VALUE);
        machine.registers().register(3, -1);

        Instruction instruction = InstructionTestSupport.instruction("Div", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MIN_VALUE, machine.registers().register(1));

        machine.registers().register(5, Integer.MAX_VALUE);
        machine.registers().register(6, -1);

        instruction = InstructionTestSupport.instruction("Div", 4, 5, 6);
        instruction.execute(machine);

        assertEquals(-2147483647, machine.registers().register(4));
    }

    @Test
    void divideZeroStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 0);
        machine.registers().register(3, -1);

        Instruction instruction = InstructionTestSupport.instruction("Div", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(0, machine.registers().register(1));
    }

    @Test
    void divideByZeroStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, 1);
        machine.registers().register(3, 0);

        Instruction instruction = InstructionTestSupport.instruction("Div", 1, 2, 3);

        assertThrows(ArithmeticException.class,()->instruction.execute(machine));
    }

    @Test
    void divideByMinusOneStoresResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(2, Integer.MIN_VALUE);
        machine.registers().register(3, -1);

        Instruction instruction = InstructionTestSupport.instruction("Div", 1, 2, 3);
        instruction.execute(machine);

        assertEquals(Integer.MIN_VALUE, machine.registers().register(1));
    }
}
