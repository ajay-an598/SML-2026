package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class SequenceExecutionTest {
    @Test
    void sequenceExecutionResult() {
        Machine machine = InstructionTestSupport.machine();
        
        Instruction instruction1 = InstructionTestSupport.instruction("Lin", 1, 5);
        Instruction instruction2 = InstructionTestSupport.instruction("Add", 2, 1, 1);
        Instruction instruction3 = InstructionTestSupport.instruction("Mul", 3, 2, 1);

        instruction1.execute(machine);
        instruction2.execute(machine);
        instruction3.execute(machine);
        
        assertEquals(10, machine.registers().register(2));
        assertEquals(50, machine.registers().register(3));
    }
}
