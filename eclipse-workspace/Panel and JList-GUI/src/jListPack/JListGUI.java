package jListPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListGUI extends JFrame {
	
	//when we extend from the JFrame we do not need to make a JFrame object
	
	//Container
	Container content;
	
	//Button Attributes
	JButton btnGetValue;
	JTextField tfBefore;
	JLabel lblBefore;
	Border panelBorder;
	JList<String> myList;
	Vector<String> myVector;
	JButton btnClose;
	JTextField tfAfter;
	JLabel lblAfter;
	
	//call the constructor from JFrame - put in title
	public JListGUI() 
	{
		super("JList and JPanel Practice");
	
		//set the bounds
		this.setBounds(100, 100, 500, 520);
		
		//make container
		content = this.getContentPane();
		
		//create border so we can put it around the panels
		panelBorder = BorderFactory.createEtchedBorder();
		
		//make the main panel (the new BorderLayout 10,10 means it must have at least 10 height and width between other components
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		
		/*add the smaller panels inside mainPanel. North means it
		 * will put sub Panel North in the mainPanel.
		 */
		mainPanel.add(createUpperPanel(), BorderLayout.NORTH);
		
		//Create the center Panel to the main panel
		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		
		mainPanel.add(createLowerPanel(), BorderLayout.SOUTH);
		
		//add mainPanel to container
		content.add(mainPanel);
		//To close window
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//make it visible
		
		this.setVisible(true);
	}
	
	private JPanel createUpperPanel() 
	{
		JPanel upperPanel = new JPanel();
		
		//make the border visible around the upper Panel
		upperPanel.setBorder(panelBorder);
		
		//create box to make spaces - putting the buttons and txt and labels in boxes 
		Box hBox = Box.createHorizontalBox();
		
		//create button
		btnGetValue = new JButton("Get Value");
		btnGetValue.addActionListener(btnListner());
		
		//add btn to upperPanel
		hBox.add(btnGetValue);
		
		//this create how big the box is
		hBox.add(Box.createHorizontalStrut(50));
		
		//create text field
		tfBefore = new JTextField(20);
		
		//add tfBefore to upperPanel
		hBox.add(tfBefore);
		
		//insert a box to create some space between textField tfBefore and label lblBefore
		hBox.add(Box.createHorizontalStrut(50));
		
		lblBefore = new JLabel("No Input");
		lblBefore.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblBefore.setForeground(Color.RED);
		
		//Still put the lblBefore in the hBox, but we don't have to have the createHorizontalStrut because it's at the end
		hBox.add(lblBefore);
		
		//now because we have added everything to the hBox we will just add the hBox to the upperPanel 
		upperPanel.add(hBox);
		
		//return the upperPanel to the Constructor
		return upperPanel;
	}
	
	private JPanel createCenterPanel() 
	{
		JPanel centerPanel = new JPanel();
		
		centerPanel.setBorder(panelBorder);
		
		//JLists (lists that show values and you can highlight them
		myList = new JList<String>();
		//set the width of the JList
		myList.setFixedCellWidth(450);
		//set how many rows should be in the JList
		myList.setVisibleRowCount(20);
		
		//add action Listener to list (it's not an action listener
		myList.addListSelectionListener(new listListener2());
		
		//JScrollPane makes all the rows visible
		JScrollPane myScrollPane = new JScrollPane(myList);
		
		//Vector is needed to enter data into list.  A vector is like an ArrayList (changes in size automatically)
		myVector = new Vector<>();
		//adding data to the Vector
		myVector.add("Hello");
		myVector.add("Add some stuff");
		//setting elements in the Vector to be Data in the JList list
		myList.setListData(myVector);
		
		//adding scrollPane because it contains the myList to the centerPanel - makes all
		centerPanel.add(myScrollPane);
		
		return centerPanel;
	}
	
	private JPanel createLowerPanel () 
	{
		JPanel lowerPanel = new JPanel();
		Box hBox = Box.createHorizontalBox();
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(btnListner());
		hBox.add(btnClose);
		hBox.add(Box.createHorizontalStrut(40));
		
		lblAfter = new JLabel("Transferred Text");
		hBox.add(lblAfter);
		
		hBox.add(Box.createHorizontalStrut(40));
		
		tfAfter = new JTextField(20);
		hBox.add(tfAfter);
		
		lowerPanel.add(hBox);
		
		
		return lowerPanel;
	}
	
	private ActionListener btnListner() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnGetValue) 
				{
					lblBefore.setText(tfBefore.getText());
					myVector.add(tfBefore.getText());
					myList.setListData(myVector);
				}
				else if(e.getSource()== btnClose) 
				{
					showExitPage();

				}
				
			}
		};
	}
	
	private void showExitPage() 
	{
		//Message 1th arg is parent, message, title, type of inputs
		int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
		
		//if they answer yes then it exits the program
		if(answer == JOptionPane.YES_OPTION) 
		{
			System.exit(0);
		}
	}
	
	private ListSelectionListener listListener() 
	{
		return new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				tfAfter.setText(myList.getSelectedValue());
				
			}
		};
	}
	
	//Inner Class
	
	public class listListener2 implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			tfAfter.setText(myList.getSelectedValue());
			
		}
		
	}
	
	public static void main (String [] args) 
	{
		//just created the object with no value
		new JListGUI();
	}

}
