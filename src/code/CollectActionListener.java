package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the collect button
 * @author Alyssa
 *
 */

public class CollectActionListener implements ActionListener {
	
	private Driver _d;
	
	public CollectActionListener(Driver d){
		_d=d;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//tell driver to initiate data collection
		_d.collect();
		

	}

}
