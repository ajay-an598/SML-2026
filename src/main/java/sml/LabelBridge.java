package sml;

/*
 * This class represents the mechanism for accessing a label 
 * given that there is restricted access to the class
 *
 * @author KLM and xxx
 */

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import static lombok.AccessLevel.PUBLIC;

@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = PUBLIC)

public class LabelBridge {
    // TODO

  // to access lables
  public static int indexOf(Machine machine,String label) {
		return machine.labels().indexOf(label);
	}

  public static void addLabel(Machine machine,String label){
    machine.labels().addLabel(label);
  }

}