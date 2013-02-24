package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
/**
 * ActionListener for the radio buttons that allow the change of collection duration
 * @author Alyssa
 *
 */
public class RadioActionListeners implements ActionListener {

	private JRadioButton _b;
	
	public RadioActionListeners(JRadioButton b){
		_b=b;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		_b.setSelected(!_b.isSelected());
	}

    public void itemStateChanged(ItemEvent e) {
        System.out.println("ItemEvent received: " 
    		           + e.getItem()
    		           + " is now "
    		           + ((e.getStateChange() == ItemEvent.SELECTED)?
    			      "selected.":"unselected"));
    }

    public void stateChanged(ChangeEvent e) {
        System.out.println("ChangeEvent received from: "
    		           + e.getSource());
    }

}
