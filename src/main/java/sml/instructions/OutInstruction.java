package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * Example instruction for the SML machine
*/
public class OutInstruction extends Instruction {
    private final int register;

    public OutInstruction(String label, int i) {
        super(label, "out");
        register = i;
    }

    @Override
    public void execute(Machine m) {
        System.out.println(m.registers().register(register));
    }

    @Override
    public String toString() {
        return super.toString() + " content of register "+ register;
    }
}
