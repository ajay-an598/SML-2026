package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import sml.LabelBridge;// to access labels

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class BnzInstructionTest {
    @Test
    void bnzWhenRegisterValueNotZero() {
        Machine machine = InstructionTestSupport.machine();
        LabelBridge.addLabel(machine,"L1");
        LabelBridge.addLabel(machine,"L2");

        machine.registers().register(1,5);

        BnzInstruction instruction = new BnzInstruction("L1", 1, "L2");
        instruction.execute(machine);
        
        assertEquals(LabelBridge.indexOf(machine,"L2"), machine.pc());
        assertEquals(1, LabelBridge.indexOf(machine,"L2"));
        assertEquals(1, machine.pc());
    }
    @Test
    void bnzWhenRegisterValueZero() {
        Machine machine = InstructionTestSupport.machine();
        LabelBridge.addLabel(machine,"L1");
        LabelBridge.addLabel(machine,"L2");

        machine.registers().register(1,0);

        machine.pc(7);

        BnzInstruction instruction = new BnzInstruction("L1", 1, "L2");
        instruction.execute(machine);
        
        assertEquals(7, machine.pc());
    }
}
