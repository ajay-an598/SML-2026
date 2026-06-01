package sml.instructions;

import sml.Instruction;
import sml.Machine;
import sml.LabelBridge;// to access labels

public class BnzInstruction extends Instruction {
    private final int register;
    private final String new_lable;

    public BnzInstruction(String label, int i1, String i2) {
        super(label, "bnz");
        register = i1;
        new_lable = i2;
    }

    @Override
    public void execute(Machine m) {
        int value=m.registers().register(register);

        if(value!=0){
          //int targetPc=m.labels().indexOf(new_lable);
          int targetPc=LabelBridge.indexOf(m,new_lable);
          if (targetPc!=-1){
            m.pc(targetPc);
          }
          // what if -1
        }
    }

    @Override
    public String toString() {
        return super.toString() + " If the contents of register "+ register+ 
              "is not zero, then make the statement labelled "+ new_lable+" the next statement to execute" ;
    }
}
