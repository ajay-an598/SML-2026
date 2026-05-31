package sml.instructions;

import sml.Instruction;
import sml.Machine;

public class LinInstruction extends Instruction {
    private final int register;
    private final int value;

    public LinInstruction(String label, int i1, int i2) {
        super(label, "lin");
        register = i1;
        value = i2;
    }

    @Override
    public void execute(Machine m) {
        m.registers().register(register,value);
    }

    @Override
    public String toString() {
        return super.toString() + " store value "+value+ " in register " + register ;
    }
}
