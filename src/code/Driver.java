package code;

import javax.swing.*;

import java.awt.*;
import javax.swing.event.ChangeListener;
import javax.swing.JRadioButton;




public class Driver {
	
	private JFrame _frame;
	private JPanel _panel;
	private JButton _button;
	private DataAggregate _da;
	ButtonGroup _buttonGroup;
	private Color _c;
	
	public Driver(){
		_c = new Color(0,190,242);
		_frame = new JFrame("Twitter Data Collection");
		_panel=new JPanel();
		
		JPanel buttonPanel = new JPanel();
		JPanel radioPanel = new JPanel();
		_button = new JButton("Run Data Collection");
		JRadioButton twentySecsButton = new JRadioButton("20 seconds");
		twentySecsButton.addActionListener(new RadioActionListeners(twentySecsButton));
		twentySecsButton.setActionCommand("20s");
		twentySecsButton.setBackground(_c);
		JRadioButton fiveMinutesButton = new JRadioButton("5 minutes");
		fiveMinutesButton.addActionListener(new RadioActionListeners(fiveMinutesButton));
		fiveMinutesButton.setActionCommand("5m");
		fiveMinutesButton.setBackground(_c);
		JRadioButton hourButton = new JRadioButton("1 hour");
		hourButton.addActionListener(new RadioActionListeners(hourButton));
		hourButton.setActionCommand("1h");
		hourButton.setBackground(_c);
		JRadioButton threeHoursButton = new JRadioButton("3 hours");
		threeHoursButton.addActionListener(new RadioActionListeners(threeHoursButton));
		threeHoursButton.setActionCommand("3h");
		threeHoursButton.setBackground(_c);
		JRadioButton dayButton = new JRadioButton("1 day");
		dayButton.addActionListener(new RadioActionListeners(dayButton));
		dayButton.setActionCommand("d");
		dayButton.setBackground(_c);
		JRadioButton weekButton = new JRadioButton("1 week");
		weekButton.addActionListener(new RadioActionListeners(weekButton));
		weekButton.setActionCommand("w");
		weekButton.setBackground(_c);
		_buttonGroup = new ButtonGroup();
		_buttonGroup.add(twentySecsButton);
		_buttonGroup.add(fiveMinutesButton);
		_buttonGroup.add(hourButton);
		_buttonGroup.add(threeHoursButton);
		_buttonGroup.add(dayButton);
		_buttonGroup.add(weekButton);
		twentySecsButton.setSelected(true);

		radioPanel.add(twentySecsButton);
		radioPanel.add(fiveMinutesButton);
		radioPanel.add(hourButton);
		radioPanel.add(threeHoursButton);
		radioPanel.add(dayButton);
		radioPanel.add(weekButton);
		buttonPanel.add(_button);
		_panel.add(radioPanel);
		_panel.add(buttonPanel);
		_button.addActionListener(new CollectActionListener(this));
	    _frame.add(_panel);
	    
	    _da = new DataAggregate(0);
	    radioPanel.setBackground(new Color(255,255,255) );
	    buttonPanel.setBackground(_c );
		_panel.setBackground(_c);
	    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//	    _frame.pack();
	    _frame.setSize(500, 500);
	    _frame.setVisible(true);
	}
	

	
	public void collect(){
		//remove the button
		long time=0;
//		if(twentySecsButton.isSelected()){
//			//collect data for 20secs = 20000ms
//			time =20000;
//		}else if(fiveMinutesButton.isSelected()){
//			//collect data for 5 minutes = 300000ms
//			time = 300000;
//		}else if(hourButton.isSelected()){
//			//collect data for 1 hour = 3.6e+6 ms
//			time = 3600000;
//		}else if(threeHoursButton.isSelected()){
//			//collect data for 3 hours = 1.08e+7ms
//			time = 10800000;
//		}
		String s=_buttonGroup.getSelection().getActionCommand();
		if(s.equals("20s")){
			//collect data for 20secs = 20000ms
			time =20000;
		}else if(s.equals("5m")){
			//collect data for 5 minutes = 300000ms
			time = 300000;
		}else if(s.equals("1h")){
			//collect data for 1 hour = 3.6e+6 ms
			time = 3600000;
		}else if(s.equals("3h")){
			//collect data for 3 hours = 1.08e+7ms
			time = 10800000;
		}else if(s.equals("d")){
			//collect data for 1 day = 86400000ms
			time = 86400000;
		}else if(s.equals("w")){
			//collect data for 1 week = 6.048e+8ms
			time=604800000;
		}
		
		_da.setTime(time);
		
		_panel.removeAll();
		_panel.validate();
		_panel.repaint();

		//collect the data
		_da.start();
		
//		TODO: add output file path to user feedback
		JTextArea t = new JTextArea("Data collection complete.");
		_panel.add(t);
		_panel.revalidate();
		_panel.repaint();
		_frame.validate();
		_frame.repaint();
	}
	

	
	public static void main(String[] args) {
		
		Driver d = new Driver();
		
		
//		System.out.println("Preparing for Data Aggregation...");
//		DataAggregate da = new DataAggregate();
//		
//		System.out.println("Beginning Data Aggregation...");
//		da.start();
	}
		
}
