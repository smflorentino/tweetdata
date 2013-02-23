package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollectActionListener implements ActionListener {
	
	private Driver _d;
	
	public CollectActionListener(Driver d){
		_d=d;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		_d.collect();
		

	}

}
