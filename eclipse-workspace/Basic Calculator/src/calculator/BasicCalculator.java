package calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BasicCalculator {
	
	private JFrame myCal;
	private JTextField tfFirstValue;
	private JTextField tfSecondValue;
	private JTextField tfAnswer;
	private JButton btnCalculate;
	private JButton btnClear;
	private JButton btnClose;
	private JRadioButton rbAdd;
	private JRadioButton rbSub;
	private JRadioButton rbMul;
	private JRadioButton rbDiv;
	private JComboBox<String>cbMode;
	private Container content;
	
	private String operation = "add";
	
	public BasicCalculator() 
	{
		//create the frame called Basic Calculator
		myCal = new JFrame("Basic Calculator");
		
		//to close the window
		setCloseOperation();
		
		//set the size of the window
		myCal.setBounds(100,100,450,350);
		
		
		//set up container
		content = myCal.getContentPane();
		Color c = new Color(0, 204, 255);
		content.setBackground(c);
		//create layout
		content.setLayout(null); //null can sometimes make it easier to set things up where we want it - not specifying a layout type
		
		createMenu();
		createLabelsAndTextFields();
		createButtons();
		createRadiobtns();
		createComboBox();
		
	}
	
	private void setCloseOperation() 
	{
		myCal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createRadiobtns() 
	{
				//Adding the Add radio Button
				rbAdd = new JRadioButton("Add");
				rbAdd.setSelected(true);
				rbAdd.setBounds(15, 200, 80, 25);
				rbAdd.addActionListener(actionAdd());
				content.add(rbAdd);
				
				
				//Adding the Subtraction radio Button
				rbSub = new JRadioButton("Subtraction");
				rbSub.setBounds(125, 200, 80, 25);
				rbSub.addActionListener(actionSub());
				content.add(rbSub);
				
				//Adding the Multiplying radio Button
				rbMul = new JRadioButton("Multiply");
				rbMul.setBounds(240, 200, 80, 25);
				rbMul.addActionListener(actionMul());
				content.add(rbMul);
				
				//Adding the Division radio Button
				rbDiv = new JRadioButton("Division");
				rbDiv.setBounds(340, 200, 80, 25);
				rbDiv.addActionListener(actionDiv());
				content.add(rbDiv);
	}
	
	private void createLabelsAndTextFields() 
	{
				//Adding the big label
				JLabel lblBigLabel = new JLabel("My Calculator");
				lblBigLabel.setFont(new Font("Arial", Font.BOLD, 24));
				lblBigLabel.setForeground(Color.CYAN);
				lblBigLabel.setBounds(50, 15, 330, 50);
				lblBigLabel.setHorizontalAlignment(SwingConstants.CENTER);
				//add label to container
				content.add(lblBigLabel);
				
				//adding first value label
				JLabel lblFirstValue = new JLabel("First Value:");
				lblFirstValue.setFont(new Font("Arial", Font.PLAIN, 12));
				lblFirstValue.setBounds(30, 60, 150, 25);
				lblFirstValue.setHorizontalAlignment(SwingConstants.RIGHT);
				//add label to container
				content.add(lblFirstValue);
				
				//adding the first value text field
				tfFirstValue = new JTextField();
				tfFirstValue.setBounds(190,60, 120, 25);
				content.add(tfFirstValue);
				
				//adding first value label
				JLabel lblSecondValue = new JLabel("Second Value:");
				lblSecondValue.setFont(new Font("Arial", Font.PLAIN, 12));
				lblSecondValue.setBounds(30, 95, 150, 25);
				lblSecondValue.setHorizontalAlignment(SwingConstants.RIGHT);
				//add label to container
				content.add(lblSecondValue);
				
				//adding the second value text field
				tfSecondValue = new JTextField();
				tfSecondValue.setBounds(190,95, 120, 25);
				content.add(tfSecondValue);
				
				//adding the answer label
				JLabel lblAnswer = new JLabel("Answer:");
				lblAnswer.setFont(new Font("Arial", Font.PLAIN,12));
				lblAnswer.setBounds(30, 130, 150, 25);
				lblAnswer.setHorizontalAlignment(SwingConstants.RIGHT);
				content.add(lblAnswer);
				
				//adding the answer text field
				tfAnswer = new JTextField();
				tfAnswer.setBounds(190, 130, 120, 25);
				tfAnswer.setEditable(false);
				content.add(tfAnswer);
	}
	
	private void createButtons () 
	{
		//Adding the Calculate button
				btnCalculate = new JButton("Calculate");
				btnCalculate.setBounds(40, 165, 100, 25);
				btnCalculate.addActionListener(actionCalculate());
				content.add(btnCalculate);
				
				//Adding the Clear button
				btnClear = new JButton("Clear");
				btnClear.setBounds(170, 165, 100, 25);
				btnClear.addActionListener(actionClear());
				content.add(btnClear);
				
				//adding the Close button
				btnClose = new JButton("Close");
				btnClose.setBounds(300,165, 100, 25);
				btnClose.addActionListener(actionClose());
				content.add(btnClose);
	}
	
	private void createComboBox () 
	{
		cbMode = new JComboBox<String>();
		cbMode.addItem("Double");
		cbMode.addItem("Integer");
		cbMode.addItem("Binary");
		cbMode.setBounds(40, 250, 100, 25);
		content.add(cbMode);
	}
	
	private double calculateResult(double firstValue, double secondValue) 
	{
		double answer = 0;
		switch(operation) 
		{
			case "add":
				answer = firstValue + secondValue;
				break;
			case "sub":
				answer = firstValue -secondValue;
				break;
			case "div":
				answer = firstValue/secondValue;
				break;
			case "mul":
			    answer = firstValue * secondValue;
				break;
		}
		
		return answer;
	}
	
	private void createMenu() 
	{
		//creating MenuBar
		JMenuBar mnubMyBar = new JMenuBar();
		mnubMyBar.setBounds(0, 0, 450, 25);
		
		//creating the File Menu
		JMenu mnuFile = new JMenu("File");
	
		
		//creating the Open items in the menu
		JMenuItem mnuiOpen = new JMenuItem("Open");
		
		//creating Save SubMenu
		JMenu mnuSubMenu = new JMenu("Save");
		JMenuItem mnuiSave = new JMenuItem("Save");
		JMenuItem mnuiSaveAs = new JMenuItem("Save as");
		JMenuItem mnuiSaveAll = new JMenuItem("Save all");
		
		//add subMenu items to the submenu
		mnuSubMenu.add(mnuiSave);
		mnuSubMenu.add(mnuiSaveAs);
		mnuSubMenu.add(mnuiSaveAll);
		
		//add the Save submenu to the file
		mnuFile.add(mnuSubMenu);
		
		//creating the Close item in the menu
		JMenuItem mnuiClose = new JMenuItem("Close");
		
		//add the items to File
		mnuFile.add(mnuiOpen);
		mnuFile.add(mnuiClose);
		
		//Add file to menu bar
		mnubMyBar.add(mnuFile);
		
		//create another Menu in the menu bar
		JMenu mnuHelp = new JMenu("Help");
		
		//create item for help menu 
		JMenuItem mnuiAbout = new JMenuItem("About");
		mnuiAbout.addActionListener(actionAbout());
		
		//add the About item to the Help Menu
		mnuHelp.add(mnuiAbout);
		//add the Help menu to the menu bar
		mnubMyBar.add(mnuHelp);
		content.add(mnubMyBar);
	}
	
	private ActionListener actionAbout() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//print message when About under Help is clicked
				String msg = "This application is implemented by CPRG 251 students";
				/*myCal means it shows up on top of that frame, the message, title of message, and type of message */
				JOptionPane.showMessageDialog(myCal, msg, "About this Application", JOptionPane.QUESTION_MESSAGE);
			
			}
		};
	}
	
	private ActionListener actionCalculate() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//getText will grab the value in the JTextField
				double firstValue = Double.parseDouble(tfFirstValue.getText());
				double secondValue = Double.parseDouble(tfSecondValue.getText());
				double answer = calculateResult(firstValue, secondValue);
				
				switch(cbMode.getSelectedItem().toString()) 
				{
				case "Double":
					//setText will assign a value in a JTextField
					tfAnswer.setText(answer+"");
					break;
				case "Integer":
					tfAnswer.setText((int)answer+"");
					break;
				case "Binary":
					tfAnswer.setText(Integer.toBinaryString((int)answer) +"");
					break;
					
				}

				
			}
		};
	}
	
	private ActionListener actionClear() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfAnswer.setText("");
				tfFirstValue.setText("");
				tfSecondValue.setText("");
				
			}
		};
	}
	
	private ActionListener actionClose() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		};
	}
	
	//setting all the other radio buttons to setSelected to false when add button is clicked
	private ActionListener actionAdd() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rbDiv.setSelected(false);
				rbMul.setSelected(false);
				rbSub.setSelected(false);
				operation = "add";
				
			}
		};
	}
	
	private ActionListener actionSub() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rbDiv.setSelected(false);
				rbMul.setSelected(false);
				rbAdd.setSelected(false);
				operation = "sub";
				
			}
		};
	}
	
	private ActionListener actionMul() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rbDiv.setSelected(false);
				rbSub.setSelected(false);
				rbAdd.setSelected(false);
				operation = "mul";
				
			}
		};
	}
	
	private ActionListener actionDiv() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rbMul.setSelected(false);
				rbSub.setSelected(false);
				rbAdd.setSelected(false);
				operation = "div";
				
			}
		};
	}
	
	//must make the window/frame visible. Not called in constructor- is in AppDriver
	public void makeVisible() 
	{
		myCal.setVisible(true);
	}
}
