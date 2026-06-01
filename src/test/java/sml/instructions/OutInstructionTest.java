package sml.instructions;

import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class OutInstructionTest {
    @Test
    void printRegisterContentuOnScreenResult() {
        Machine machine = InstructionTestSupport.machine();
        machine.registers().register(1, 5);
        machine.registers().register(2, 6);
        machine.registers().register(3, 7);

        machine.registers().register(4, 2147483647+1);
        machine.registers().register(5, -2147483648-1);

        PrintStream originalOut=System.out;

        try{
          ByteArrayOutputStream out =new ByteArrayOutputStream();  
          System.setOut(new PrintStream(out));

          Instruction instruction1 = InstructionTestSupport.instruction("Out", 1);
          instruction1.execute(machine);

          assertEquals("5\n",out.toString());
          out.reset();

          Instruction instruction2 = InstructionTestSupport.instruction("Out", 2);
          instruction2.execute(machine);

          assertEquals("6\n",out.toString());
          out.reset();

          Instruction instruction3 = InstructionTestSupport.instruction("Out", 3);
          instruction3.execute(machine);

          assertEquals("7\n",out.toString());
          out.reset();

          Instruction instruction4 = InstructionTestSupport.instruction("Out", 4);
          instruction4.execute(machine);

          assertEquals("-2147483648\n",out.toString());
          out.reset();

          Instruction instruction5 = InstructionTestSupport.instruction("Out", 5);
          instruction5.execute(machine);

          assertEquals("2147483647\n",out.toString());
          out.reset();
        }
        finally{
          System.setOut(originalOut);
        }        
    }
}
