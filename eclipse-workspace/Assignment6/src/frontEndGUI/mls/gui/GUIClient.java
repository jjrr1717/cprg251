/**
 * GUIClient is a class that hold all the methods and 
 * components needed in the Client Panel.  
 * 
 * @author Jocelyn Wegen
 * @version Febraury 17, 2019
 */

package frontEndGUI.mls.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import saitMLS.exceptions.InvalidPhoneNumberException;
import saitMLS.exceptions.InvalidPostalCodeException;
import saitMLS.persistance.ClientBroker;
import saitMLS.problemDomain.Client;



public class GUIClient {
	
	/**
	 * actionListener2 is an ActionListner that listens
	 * for a button to perform a search function.  
	 */
	private ActionListener actionListener2;
	/**
	 * actionListenerAddButtons is an ActionListner that 
	 * listens for a button that adds an item to an ArrayList.  
	 */
	private ActionListener actionListenerAddButtons;
	/**
	 * actionListenerRemvoveButtons is an ActionListener that
	 * listens for a button that will remove an item from 
	 * an ArrayList
	 */
	private ActionListener actionListenerRemoveButtons;
	/**
	 * actionListenerClear is an ActionListener that listens 
	 * for a button that will clear either the information side 
	 * of the screen or the search function of the screen. 
	 */
	private ActionListener actionListenerClearButtons;
	/**
	 * index is an int that provides the index spot in an
	 * ArrayList.  
	 */
	private int index;
	
	//client list and search variables
	/**
	 * lstSearchResult is a JList that displays the searched 
	 * results. 
	 */
	private JList <String>lstSearchResult;
	/**
	 * listModel is a DefaultListModel that holds 
	 * the searched list to display in the JList. 
	 */
	private DefaultListModel<String> listModel;
	/**
	 * cb is an object of the ClientBroker to get access
	 * to the methods in that class. 
	 */
	private ClientBroker cb;
	/**
	 * searchResult is an ArrayList of items
	 * created from the Broker. It is used to
	 * hold items that have been searched
	 * based on a search criteria (such as
	 * Id, address, etc.)
	 * 
	 */
	private ArrayList<Client> searchResult;
	
	/**
	 * rightPanelLabel is a JLabel for the
	 * rightPanel label.  
	 */
	private JLabel lblRightPanelLabel;
	
	//Client top left center components
	/**
	 * lblSearchByClient is a JLabel "Search by:" 
	 */
	private JLabel lblSearchByClient;
	/**
	 * cbSearchByClient is a JComboBox that holds 
	 * all the different variations a user can
	 * search by.
	 */
	private JComboBox<String>cbSearchByClient;
	/**
	 * tfSearchClient is a JTextField that a user
	 * can use to enter what they want to search.
	 */
	private JTextField tfSearchClient;
	/**
	 * btnSearchClient is a JButton that a user
	 * can click to search for whatever criteria 
	 * they have entered. 
	 */
	private JButton btnSearchClient;
	/**
	 * btnClearSearchClient is a JButton that a
	 * user can click to clear the JList and 
	 * textfield in the search function part of 
	 * the GUI. 
	 */
	private JButton btnClearSearchClient;
	
	//Client right center components
	/**
	 * lblIdClient is a Jlabel for the id
	 */
	private JLabel lblIdClient;
	/**
	 * tfIdClient is a JTextField for the id
	 */
	private JTextField tfIdClient;
	/**
	 * lblFirstNameClient is a JLabel for the 
	 * client's firstname 
	 */
	private JLabel lblFirstNameClient;
	/**
	 * tfFirstNameClient is a textfield
	 * for the client's firstname
	 */
	private JTextField tfFirstNameClient;
	/**
	 * lblLastNameClient is a JLabel
	 * for the client's lastname
	 */
	private JLabel lblLastNameClient;
	/**
	 * tfLastNameClient is a JTextField
	 * for the client's lastname.
	 */
	private JTextField tfLastNameClient;
	/**
	 * lblAddressClient is a JLabel
	 * for the client's address
	 */
	private JLabel lblAddressClient;
	/**
	 * tfAddressClient is a JTextField
	 * for the client's address
	 */
	private JTextField tfAddressClient;
	/**
	 * lblTypeClient is a JLabel for the
	 * client Type
	 */
	private JLabel lblTypeClient;
	/**
	 * cbTypeCient is a JComboBox that lists 
	 * the Type of Client. R-Residential, 
	 * C-Commerical, B-Both.
	 */
	private JComboBox<String>cbTypeClient;
	/**
	 * lblPhoneNumberClient is a JLabel for the
	 * client's phone number. 
	 */
	private JLabel lblPhoneNumberClient;
	/**
	 * tfPhoneNumberClient is a JTextField for the 
	 * client's phone number. Format XXX-XXX-XXXX
	 */
	private JTextField tfPhoneNumberClient;
	/**
	 * lblPostalCodeClient is a JLabel for the 
	 * client's postal code. 
	 */
	private JLabel lblPostalCodeClient;
	/**
	 * tfPostalCodeClient is a JTextField 
	 * for the client's postal code.
	 * Format A1A 1A1
	 */
	private JTextField tfPostalCodeClient;
	/**
	 * btnAddClient is a button the user
	 * can click to add a client or save a modified 
	 * Client. 
	 */
	private JButton btnAddClient;
	/**
	 * btnAddClient is a JButton the user
	 * can click to remove a Client from 
	 * the ArrayList
	 */
	private JButton btnRemoveClient;
	/**
	 * btnClearInfoClient is a JButton the
	 * user can click to clear the information
	 * presented on the right panel. 
	 */
	private JButton btnClearInfoClient;
	
	/**
	 * Constructor
	 * Accepts no parameters
	 */
	public GUIClient() 
	{
		actionListener2 = new MyActionListener2();
		actionListenerAddButtons = new MyActionListenerAddButtons();
		actionListenerRemoveButtons = new MyActionListenerRemoveButtons();
		actionListenerClearButtons = new MyActionListenerClearButtons();
		
		cb = ClientBroker.getClientBroker();
	}
	
	/**
	 * getClientPanel contains all the panels that 
	 * make up the client panel.
	 * @return a JPanel of the complete client panel
	 */
	public JPanel getClientPanel() {
		//create panel
		JPanel panel = new JPanel(new BorderLayout());
		//add other panels to main client panel
		panel.add(getNorthCenterPanel(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanel(), BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * getCenterCenterPanel creates the whole center
	 * panel, which will hold all the subsection 
	 * panels for the center.  
	 * @return a JPanel of complete center panel
	 */
	private JPanel getCenterCenterPanel() {
		//create panel
		JPanel panel = new JPanel(new GridLayout(1,2));
		//add other panels to this panel
		panel.add(getLeftCenterPanel());
		panel.add(getRightCenterPanel());
		return panel;
	}
	
	/**
	 * getRightCenterPanel creates the right panel.
	 * This will hold all the details of the Client.
	 * @return a Jpanel of the complete right panel
	 */
	private JPanel getRightCenterPanel() {
		//create panel (this panel will display client information
		JPanel panel = new JPanel(new GridLayout(20,1,5, 0));
		//set background to light gray
		panel.setBackground(Color.LIGHT_GRAY);
		
		
		//heading
		lblRightPanelLabel = new JLabel("Client Information");
		lblRightPanelLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		
		/*display client id information (set to 0 to enable adding clients.
		 * Set the Client id to setEditable false, so user can change it. 
		 */
		lblIdClient = new JLabel("Id");
		tfIdClient = new JTextField();
		tfIdClient.setText("0");
		tfIdClient.setEditable(false);

		//Client firstname information
		lblFirstNameClient = new JLabel("First Name:");
		tfFirstNameClient = new JTextField();
	
		//Client lastname information
		lblLastNameClient = new JLabel("Last Name:");
		tfLastNameClient = new JTextField();
		
		//Client Address information
		lblAddressClient = new JLabel("Address:");
		tfAddressClient = new JTextField();
		
		//client type information. R = Residential, C = Client, B = Both
		lblTypeClient = new JLabel("Client Type:");
		cbTypeClient = new JComboBox<String>();
		cbTypeClient.addItem("R");
		cbTypeClient.addItem("C");
		cbTypeClient.addItem("B");
		
		//Client phone number information
		lblPhoneNumberClient = new JLabel("Phone Number:");
		tfPhoneNumberClient = new JTextField();
		
		//Client PostalCode information
		lblPostalCodeClient = new JLabel("Postal Code");
		tfPostalCodeClient = new JTextField();
		
		//Client add, remove, and clear buttons
		btnAddClient = new JButton("Save");
		btnAddClient.addActionListener(actionListenerAddButtons); 
		btnRemoveClient = new JButton("Delete");
		btnRemoveClient.addActionListener(actionListenerRemoveButtons);
		btnClearInfoClient = new JButton("Clear");
		btnClearInfoClient.addActionListener(actionListenerClearButtons);

		//add all the components to the panel
		panel.add(lblRightPanelLabel);
		panel.add(lblIdClient);
		panel.add(tfIdClient);
		
		panel.add(lblFirstNameClient);
		panel.add(tfFirstNameClient);
		
		panel.add(lblLastNameClient);
		panel.add(tfLastNameClient);
		
		panel.add(lblAddressClient);
		panel.add(tfAddressClient);
		
		panel.add(lblTypeClient);
		panel.add(cbTypeClient);
		
		panel.add(lblPhoneNumberClient);
		panel.add(tfPhoneNumberClient);
		
		panel.add(lblPostalCodeClient);
		panel.add(tfPostalCodeClient);
		
		panel.add(btnAddClient);
		panel.add(btnRemoveClient);
		panel.add(btnClearInfoClient);

		
		return panel;
	}
	
	/**
	 * getLeftCenterPanel creates the left panel.
	 * This will be further split into a Top and
	 * Bottom panel.
	 * @return a JPanel of the complete left panel
	 */
	private JPanel getLeftCenterPanel() {
		//create panel
		JPanel panel = new JPanel(new GridLayout(2,1));
		//add other panels 
		panel.add(getTopLeftCenterPanel());
		panel.add(getBottomLeftCenterPanel());
		return panel;
	}
	
	/**
	 * getBottomLeftCenterPanel creates the bottom
	 * panel on the left side.  This will hold
	 * all the search results.
	 * @return a JPanle of the complete bottom left
	 * panel
	 */
	private JPanel getBottomLeftCenterPanel() {
		//create panel
		JPanel panel = new JPanel();
		//border for appearance
		Border listBorder = BorderFactory.createEtchedBorder();
		//set the border for the panel
		panel.setBorder(listBorder);
		
		//hold the list of search results
		listModel = new DefaultListModel<String>();
		//JList to display the search results
		lstSearchResult = new JList<String>(listModel);
		//add listSelectionListener to JList
		lstSearchResult.addListSelectionListener(new MyListSelectionListener());
		//create JScrollPane for JList
		JScrollPane scrollPane = new JScrollPane(lstSearchResult);
		//make JList visible
		lstSearchResult.setFixedCellWidth(260);
		lstSearchResult.setVisibleRowCount(15);
		
		//add components to panel
		panel.add(scrollPane);
		panel.setBackground(Color.LIGHT_GRAY);
		return panel;
	}
	
	/**
	 * getTopLeftCenterPanel creates the top
	 * panel on the left side. This will hold all
	 * of the search functionality of the GUI. 
	 * @return a JPanel of the complete top left 
	 * panel. 
	 */
	private JPanel getTopLeftCenterPanel() {
		//create panel
		JPanel panel = new JPanel(new GridLayout(0,1));
		//set background to light gray
		panel.setBackground(Color.LIGHT_GRAY);
		
		//create label
		lblSearchByClient = new JLabel("Search By:");
		lblSearchByClient.setFont(new Font("Arial", Font.BOLD, 20));
		
		//create ComboBox and add items to it
		cbSearchByClient = new JComboBox<String>();
		cbSearchByClient.addItem("Id");
		cbSearchByClient.addItem("Firstname");
		cbSearchByClient.addItem("Lastname");
		cbSearchByClient.addItem("Postal Code");
		cbSearchByClient.addItem("Type");
		
		//create text field for search
		tfSearchClient = new JTextField();
		
		//create buttons for Search and Clear and add actionListners to them
		btnSearchClient = new JButton("Search");
		btnSearchClient.addActionListener(actionListener2);
		btnClearSearchClient = new JButton("Clear");
		btnClearSearchClient.addActionListener(actionListenerClearButtons);
		
		//add components to panel
		panel.add(lblSearchByClient);
		panel.add(cbSearchByClient);
		panel.add(tfSearchClient);
		panel.add(btnSearchClient);
		panel.add(btnClearSearchClient);
		return panel;
	}
	
	/**
	 * getNorthCenterPanle creates the very top 
	 * panel which holds the buttons to navigate  
	 * through the Client, Residential, and 
	 * Commercial buttons.
	 * @return a JPanel of the very top panel.
	 */
	private JPanel getNorthCenterPanel() {
		//create panel
		JPanel panel = new JPanel();
		//create "CLIENT" label
		JLabel label = new JLabel("CLIENT");
		//create border for panel
		Border headingborder = BorderFactory.createEtchedBorder();
		//set border to panel
		panel.setBorder(headingborder);
		//set text as Blue
		label.setForeground(Color.BLUE);
		//set text font detials
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		
		//add component
		panel.add(label);
		//set background as light gray
		panel.setBackground(Color.LIGHT_GRAY);
		return panel;
	}
	
	
	/**
	 * clearSearchClient clears the 
	 * search textfield of text and
	 * also clears the JList of items. 
	 */
	private void clearSearchClient() 
	{
		//set tfSearchClient to a blank string
		tfSearchClient.setText("");
		//clear out the DefaultListModel list
		listModel.clear();	

	}
	
	/**
	 * clearInformationClient clears all the
	 * textfields in the right panel. 
	 */
	private void clearInformationClient()
	{
		//clear out textfields in the right panel
		tfIdClient.setText("0");
		tfFirstNameClient.setText("");
		tfLastNameClient.setText("");
		tfAddressClient.setText("");
		tfPhoneNumberClient.setText("");
		tfPostalCodeClient.setText("");
	}
	
	/**
	 * maxTextFieldLength is used to validate 
	 * the max character length in textfields. 
	 * @param maxLength is an int that indicates the allowed character
	 * length for the textfield. 
	 * @param field is a String that represents what textfield is being tested.
	 * @return a boolean. ture means it is valid and fits within maxLength. false
	 * means it is not valid and goes over maxLength. 
	 */
	private boolean maxTextFieldLength(int maxLength, String field)
	{
		//start boolean as true
		boolean isValidLength = true;
		
		
		if(field.length() > maxLength) 
		{
			//make boolean false if textfield has gone over max length
			isValidLength = false;
		}
		
		return isValidLength;
	} 
	
	/**
	 * checkFieldsEmpty will validate that every field has
	 * information entered before it can be saved. 
	 * @return a boolean if all is filled =true, any empty = false
	 */
	private boolean checkFliedsEmpty() 
	{
		boolean fieldsFilled = true;
		
		if(tfFirstNameClient.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfFirstNameClient.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfLastNameClient.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfAddressClient.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfPhoneNumberClient.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfPostalCodeClient.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		
		return fieldsFilled;
		
	}
	//*******************************************************************************
	
	/**
	 * MyActionListener2 searches for whatever the user typed
	 * in the textfield in an ArrayList. If it is found, it will
	 * print it out in the JList.  
	 * @author 783661
	 *
	 */
	private class MyActionListener2 implements ActionListener  {

		

		@Override
		public void actionPerformed(ActionEvent e) {
			
				//if btnSearchClient is clicked search for the criteria entered by user. 
				if(e.getSource() == btnSearchClient) {
					
					//if there is already a search in JList clear it out
					if(listModel.size() >0) 
					{
						//clear out the DefaultListModel list
						listModel.clear();	
					}
					
					//create a Client object
					Client client = new Client();
					
					//whatever the user has selected in the ComboBox to search by:
					switch(cbSearchByClient.getSelectedItem().toString()) 
					{
						case "Id":
							//set the id of the client to what the user entered in the textfield to search
							client.setClientId(Long.parseLong(tfSearchClient.getText()));
							//use ClientBroker to search for id that matches that in client. Add to searchResult Array
							searchResult = (ArrayList<Client>) cb.search(client.getClientId() + "","Id");
							//for every item found with a match add to the listModel to display in JList
							for(Client c : searchResult) {
								listModel.addElement(c.getClientId()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
							//search by firstname and find matches
						case "Firstname":
							client.setFirstName(tfSearchClient.getText().toUpperCase());
							searchResult = (ArrayList<Client>) cb.search(client.getFirstName(), "Firstname");
							for(Client c : searchResult) {
								listModel.addElement(c.getClientId()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
							//search by lastname and find matches
						case "Lastname":
							client.setLastName(tfSearchClient.getText().toUpperCase());
							searchResult = (ArrayList<Client>) cb.search(client.getLastName(), "Lastname");
							for(Client c : searchResult) {
								listModel.addElement(c.getClientId()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
							//search by postal code and find matches
						case "Postal Code":
							/*postal code has an exception for proper formatting. 
							 * If format correct it will set the postal code to client. 
							 * If format is incorrect it will display warning. 
							 */
						try {
							client.setPostalCode(tfSearchClient.getText().toUpperCase());
						} catch (InvalidPostalCodeException e1) {
							JOptionPane.showMessageDialog(null, "Invalid format for postal code!");
						}
							searchResult = (ArrayList<Client>) cb.search(client.getPostalCode(), "Postal Code");
							for(Client c : searchResult) {
								listModel.addElement(c.getClientId()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
							//search by type and return matches
						case "Type":
							/*
							 * type has an exception for format. If format is 
							 * correct add type to client. If it's not correct
							 * display error message. 
							 */
						
							client.setClientType(Character.toUpperCase(tfSearchClient.getText().charAt(0)));

							searchResult = (ArrayList<Client>) cb.search(client.getClientType() + "", "Type");
							for(Client c : searchResult) {
								listModel.addElement(c.getClientId()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
						
					}
					
				}
			}
			
	}
	
	/**
	 * MyListSelectionListener will display all the elements in an
	 * ArrayList in the textfields in the right panel.  
	 * @author 783661
	 *
	 */
	private class MyListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			//set the index to the selected item from the JList
			index = lstSearchResult.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResult.get(index));
				
				/*
				 * from the element determined by the index, get all the attributes 
				 * of the item to display in the right panel
				 */
				tfIdClient.setText(searchResult.get(index).getClientId() + "");
				tfFirstNameClient.setText(searchResult.get(index).getFirstName());
				tfLastNameClient.setText(searchResult.get(index).getLastName());
				tfAddressClient.setText(searchResult.get(index).getAddress());
				cbTypeClient.setSelectedItem(searchResult.get(index).getClientType() + "");
				tfPhoneNumberClient.setText(searchResult.get(index).getPhoneNumber());
				tfPostalCodeClient.setText(searchResult.get(index).getPostalCode());
			}
			
		}
		
	}
	
	/**
	 * MyActionListenerAddButtons will add an object to an
	 * ArrayList.  The object is made up of what the user
	 * entered in the right panel.  
	 * @author 783661
	 *
	 */
	private class MyActionListenerAddButtons implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			//set booleans to validate length of fields are correct
			boolean validLengthFirstname = true;
			boolean validLengthLastname = true;
			boolean validLengthAddress = true;
			boolean fieldsFilled = true;
			
			//if btnAddClient is clicked
			if(e.getSource() == btnAddClient) 
			{
				//create Client object to hold the new client attributes
				Client newClient = null;
				
				//add all the data user entered into variables
				long newId = Long.parseLong(tfIdClient.getText());
				String newFirstName = tfFirstNameClient.getText();
				validLengthFirstname = maxTextFieldLength(20, newFirstName);
				String newLastName = tfLastNameClient.getText();
				validLengthLastname = maxTextFieldLength(20, newLastName);
				String newAddress = tfAddressClient.getText();
				validLengthLastname = maxTextFieldLength(50, newLastName);
				char newClientType = ((String) cbTypeClient.getSelectedItem()).charAt(0);
				String newPhoneNumber = tfPhoneNumberClient.getText();
				String newPostalCode = tfPostalCodeClient.getText();
				
				fieldsFilled = checkFliedsEmpty();


					//further validation for the length of the fields the user entered
					if(validLengthFirstname && validLengthLastname && validLengthAddress && fieldsFilled) 
					{
						//try is for phone number and postal code exceptions (for their formats)
						try {
							//if everything passes validation and exceptions, add all the new variables to the newClient object
							newClient = new Client(newId,newFirstName, newLastName, newAddress, newPostalCode, newPhoneNumber, newClientType);
						
						
						} catch (InvalidPhoneNumberException e1) 
						{
							JOptionPane.showMessageDialog(null, "Invalid format for Phone Number!");
						} catch(InvalidPostalCodeException e2) 
						{
							JOptionPane.showMessageDialog(null, "Invalid format for PostalCode!");
						}
						//add newClient to the database using ClientBorker persists method
						cb.persist(newClient);
						
						//display the newly generated id 
						tfIdClient.setText(newClient.getClientId() + "");
						
						//display message so user knows it worked. 
						JOptionPane.showMessageDialog(null, "Item Saved! Id#: " + newClient.getClientId());
						
						//clear info so user can start fresh
						//clearInformationClient();
						
					}
					
					//Messages for when information entered does not pass the exceptions and validations 
					else if (validLengthFirstname == false && validLengthLastname  && validLengthAddress )
					{
						JOptionPane.showMessageDialog(null, "Over Character Limit in Firstname!");
					} 
					else if (validLengthFirstname == true && validLengthAddress && validLengthLastname == false) 
					{
						JOptionPane.showMessageDialog(null, "Over Character Limit in Lastname!");
					}
					else if(validLengthAddress == false && validLengthFirstname && validLengthLastname) 
					{
						JOptionPane.showMessageDialog(null, "Over Character Limit in Address!");
					}
					else if (validLengthFirstname == false && validLengthLastname == false) 
					{
						JOptionPane.showMessageDialog(null, "Over Character Limit in Firstname and Lastname!");
					}
					else if(fieldsFilled == false) 
					{
						JOptionPane.showMessageDialog(null, "A field is empty. Please return and enter information!");
					}	
				
				}
			
		}

	}
	
	/**
	 * MyActionListenerRemoveButtons will remove an object
	 * from the ArrayList.  The object is determined by
	 * what the user selected in the JList. 
	 * @author 783661
	 *
	 */
	private class MyActionListenerRemoveButtons implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			//if user client btnRemoveClient
			if(e.getSource()==btnRemoveClient) 
			{
				//exception if nothing is there to remove
				try {
					//use ClientBrokers remove method to remove the selected index
					cb.remove(searchResult.get(index));
					//display message to communicate to user
					JOptionPane.showMessageDialog(null, "Item Removed");
					//clear the information so user can start fresh and so it doesn't look like entry still exists
					clearInformationClient();
					clearSearchClient();
				}
				catch(Exception e1) 
				{
					//Message if there is nothing to remove
					JOptionPane.showMessageDialog(null, "No item selected to remove!");
				}

			
			}
			
		}
			
	}
	
	/**
	 * MyActionListnerClearButtons will clear the screens of text.
	 * Whether that is text in the textfields or JList - Depends
	 * which clear button they click. 
	 * @author 783661
	 *
	 */
	public class MyActionListenerClearButtons implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			//if user clicks btnClearSearchClient 
			if(e.getSource() == btnClearSearchClient) 
			{
				clearSearchClient();
			}
			//if user clicks btnClearInfoClient
			else if(e.getSource() == btnClearInfoClient) 
			{
				clearInformationClient();
			}
			
		}
		
	}

}
