package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class LinInstructionTest {
    @Test
    void storeValueInRegisterResult() {
        Machine machine = InstructionTestSupport.machine();
        
        Instruction instruction1 = InstructionTestSupport.instruction("Lin", 1, 2);
        instruction1.execute(machine);
        
        Instruction instruction2 = InstructionTestSupport.instruction("Lin", 2, 3);
        instruction2.execute(machine);

        Instruction instruction3 = InstructionTestSupport.instruction("Lin", 3, 4);
        instruction3.execute(machine);
        
        assertEquals(2, machine.registers().register(1));
        assertEquals(3, machine.registers().register(2));
        assertEquals(4, machine.registers().register(3));

    }

    @Test
    void storeValueInRegisterAfterOperationResult() {
        Machine machine = InstructionTestSupport.machine();
        
        Instruction instruction1 = InstructionTestSupport.instruction("Lin", 1, Integer.MIN_VALUE-1);
        instruction1.execute(machine);
        
        Instruction instruction2 = InstructionTestSupport.instruction("Lin", 2, Integer.MAX_VALUE+1);
        instruction2.execute(machine);

        Instruction instruction3 = InstructionTestSupport.instruction("Lin", 3, Integer.MIN_VALUE+Integer.MIN_VALUE);
        instruction3.execute(machine);
        
        assertEquals(Integer.MAX_VALUE, machine.registers().register(1));
        assertEquals(Integer.MIN_VALUE, machine.registers().register(2));
        assertEquals(0, machine.registers().register(3));

    }
}
