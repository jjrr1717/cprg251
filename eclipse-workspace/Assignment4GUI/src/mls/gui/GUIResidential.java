/**
 * GUIResidential is a class that hold all the methods and 
 * components needed in the Residential Panel.  
 * 
 * @author Jocelyn Wegen
 * @version Febraury 17, 2019
 */

package mls.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import mls.gui.MyFrame.MyListSelectionListenerResidential;
import sait.mls.exceptions.clientale.InvalidPhoneNumberException;
import sait.mls.exceptions.clientale.InvalidPostalCodeException;
import sait.mls.exceptions.property.InvalidLegalDescriptionException;
import sait.mls.exceptions.property.InvalidNumberOfBathroomsException;
import sait.mls.persistence.property.ResidentialPropertyBroker;
import sait.mls.problemdomain.clientale.Client;
import sait.mls.problemdomain.property.CommercialProperty;
import sait.mls.problemdomain.property.ResidentialProperty;

public class GUIResidential {
	
	/**
	 * actionListener2 is an ActionListner that listens
	 * for a button to perform a search function.  
	 */
	private ActionListener actionListenerResidential;
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
	
	//residential list and search variables
	/**
	 * lstSearchResult is a JList that displays the searched 
	 * results. 
	 */
	private JList<String> lstSearchResultResidential;
	/**
	 * listModel is a DefaultListModel that holds 
	 * the searched list to display in the JList. 
	 */
	private DefaultListModel<String> listModelResidential;
	/**
	 * rpb is an object of the ResidentailPropertyBroker to get access
	 * to the methods in that class. 
	 */
	private ResidentialPropertyBroker rpb;
	/**
	 * searchResult is an ArrayList of items
	 * created from the Broker. It is used to
	 * hold items that have been searched
	 * based on a search criteria (such as
	 * Id, address, etc.)
	 * 
	 */
	private ArrayList<ResidentialProperty> searchResultResidential;
	
	//Residential top left center Components
	/**
	 * lblSearchByResidential is a JLabel "Search by:" 
	 */
	private JLabel lblSearchByResidential;
	/**
	 * cbSearchByResidential is a JComboBox that holds 
	 * all the different variations a user can
	 * search by.
	 */
	private JComboBox<String>cbSearchByResidential;
	/**
	 * tfSearchResidential is a JTextField that a user
	 * can use to enter what they want to search.
	 */
	private JTextField tfSearchResidential;
	/**
	 * btnSearchResidential is a JButton that a user
	 * can click to search for whatever criteria 
	 * they have entered. 
	 */
	private JButton btnSearchResidential;
	/**
	 * btnClearSearchResidential is a JButton that a
	 * user can click to clear the JList and 
	 * textfield in the search function part of 
	 * the GUI. 
	 */
	private JButton btnClearSearchResidential;
	
	//Residential right center Components
	
	/**
	 * rightPanelLabel is a JLabel for the
	 * rightPanel label.  
	 */
	private JLabel lblRightPanelLabel;
	
	/**
	 * lblIdResidential is a Jlabel for the id
	 */
	private JLabel lblIdResidential;
	/**
	 * tfIdResidential is a JTextField for the id
	 */
	private JTextField tfIdResidential;
	/**
	 * lblAddress Residential is a JLabel 
	 * for Address
	 */
	private JLabel lblAddressResidential;
	/**
	 * tfAddressResidential is a JTextField
	 * for the user to enter an Address
	 */
	private JTextField tfAddressResidential;
	/**
	 * lblAskingPriceResidential is a JLabel
	 * for the Asking Price. 
	 */
	private JLabel lblAskingPriceResidential;
	/**
	 * tfAskingPriceResidential is a JTextField for 
	 * the user to enter the Asking Price. 
	 */
	private JTextField tfAskingPriceResidential;
	/**
	 * lblQuadrantResidential is a JLabel for the
	 * Quadrant. 
	 */
	private JLabel lblQuadrantResidential;
	/**
	 * cbQuadrantResidential is a combo box for the
	 * user to select the Quadrant. 
	 */
	private JComboBox<String> cbQuadrantResidential;
	/**
	 * lblZoneResidential is a JLabel for the
	 * Zone. 
	 */
	private JLabel lblZoneResidential;
	/**
	 * cbZoneResidential is a JCombo for the
	 * user to select the Zone. 
	 */
	private JComboBox<String> cbZoneResidential;
	/**
	 * lblAreaResidential is a JLabel for the
	 * Area
	 */
	private JLabel lblAreaResidential;
	/**
	 * tfAreaResidential is a JTextField for the
	 * user to enter the Area. 
	 */
	private JTextField tfAreaResidential;
	/**
	 * lblBedroomResidential is a JLabel for the
	 * bedbrooms
	 */
	private JLabel lblBedroomsResidential;
	/**
	 * tfBedroomsResidential is a JTextField
	 * for the user to enter the number
	 * of bedrooms. 
	 */
	private JTextField tfBedroomsResidential;
	/**
	 * lblBathroomsResidential is a JLabel
	 * for the Bathrooms
	 */
	private JLabel lblBathroomsResidential;
	/**
	 * tfBathroomsResidential is a JTextfield
	 * for the user to enter the number 
	 * of bathrooms
	 */
	private JTextField tfBathroomsResidential;
	/**
	 * lblGarageTypeResidential is a JLabel
	 * for the garbage type. 
	 */
	private JLabel lblGarageTypeResidential;
	/**
	 * cbGarageTypeResidential is a JBomboBox
	 * for the user to select a garage type. 
	 */
	private JComboBox<String> cbGarageTypeResidential;
	/**
	 * lblLegalDesriptionResidential is a JLabel
	 * for LegalDescription.
	 */
	private JLabel lblLegalDescriptionResidential;
	/**
	 * tfLegalDescriptionResidential is a JTextfield
	 * for the user to enter the LegalDescription
	 */
	private JTextField tfLegalDescriptionResidential;
	/**
	 * lblCommentsResidential is a JLabel for the 
	 * Comments
	 */
	private JLabel lblCommentsResidential;
	/**
	 * tfCommentsResidential is a JTextField for
	 * the user to enter comments
	 */
	private JTextField tfCommentsResidential;
	/**
	 * btnAddResidential is a JButton
	 * the user can click to add or save a
	 * modified residential entry. 
	 */
	private JButton btnAddResidential;
	/**
	 * btnRemoveResidential is a JButton the
	 * user can click to remove a residential
	 * property. 
	 */
	private JButton btnRemoveResidential;
	/**
	 * btnClearInfoResidential is a JButton
	 * the user can click to clear the information
	 * displayed in the right panel. 
	 */
	private JButton btnClearInfoResidential;
	

	/**
	 * Constructor
	 * Accepts no parameters
	 */
	public GUIResidential() 
	{
		actionListenerResidential = new MyActionListenerResidential();
		actionListenerAddButtons = new MyActionListenerAddButtons();
		actionListenerRemoveButtons = new MyActionListenerRemoveButtons();
		actionListenerClearButtons = new MyActionListenerClearButtons();
		
		rpb = ResidentialPropertyBroker.getBroker();
	}
	
	/**
	 * getResidentialPanel contains all the panels that 
	 * make up the residential panel.
	 * @return a JPanel of the complete residential panel
	 */
	public JPanel getResidentialPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthCenterPanelResidential(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanelResidential(), BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * getCenterCenterPanel creates the whole center
	 * panel, which will hold all the subsection 
	 * panels for the center.  
	 * @return a JPanel of complete center panel
	 */
	private JPanel getCenterCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanelResidential());
		panel.add(getRightCenterPanelResidential());
		return panel;
	}
	
	/**
	 * getRightCenterPanel creates the right panel.
	 * This will hold all the details of the Clinet.
	 * @return a Jpanel of the complete right panel
	 */
	private JPanel getRightCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		//set panel background to light gray
		panel.setBackground(Color.LIGHT_GRAY);
		
		//heading
		lblRightPanelLabel = new JLabel("Residential Property Information");
		lblRightPanelLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		lblIdResidential = new JLabel("Id:");
		tfIdResidential = new JTextField();
		/*
		 * set tfIdResidential to 0 so user can add new item 
		 * and it will generate new id for them
		 */
		tfIdResidential.setText("0");
		tfIdResidential.setEditable(false);
		
		lblAddressResidential = new JLabel("Address:");
		tfAddressResidential = new JTextField();
		
		lblAskingPriceResidential = new JLabel("Asking Price:");
		tfAskingPriceResidential = new JTextField();
		
		lblQuadrantResidential = new JLabel("Quadrant:");
		cbQuadrantResidential = new JComboBox<String>();
		cbQuadrantResidential.addItem("NE");
		cbQuadrantResidential.addItem("NW");
		cbQuadrantResidential.addItem("SE");
		cbQuadrantResidential.addItem("SW");
		
		lblZoneResidential = new JLabel("Zone:");
		cbZoneResidential = new JComboBox<String>();
		cbZoneResidential.addItem("R1");
		cbZoneResidential.addItem("R2");
		cbZoneResidential.addItem("R3");
		cbZoneResidential.addItem("R4");
		cbZoneResidential.addItem("I1");
		cbZoneResidential.addItem("I2");
		cbZoneResidential.addItem("I3");
		cbZoneResidential.addItem("I4");
		
		
		lblAreaResidential = new JLabel("Area:");
		tfAreaResidential = new JTextField();
		
		lblBedroomsResidential = new JLabel("Bedrooms:");
		tfBedroomsResidential = new JTextField();
		
		lblBathroomsResidential = new JLabel("Bathrooms");
		tfBathroomsResidential = new JTextField();
		
		lblGarageTypeResidential = new JLabel("Garage Type:");
		cbGarageTypeResidential = new JComboBox<String>();
		cbGarageTypeResidential.addItem("A");
		cbGarageTypeResidential.addItem("D");
		cbGarageTypeResidential.addItem("N");
		
		
		lblLegalDescriptionResidential = new JLabel("Legal Description:");
		tfLegalDescriptionResidential = new JTextField();
		
		lblCommentsResidential = new JLabel("Comments:");
		tfCommentsResidential = new JTextField();
		
		btnAddResidential = new JButton("Save");
		btnAddResidential.addActionListener(actionListenerAddButtons);
		btnRemoveResidential = new JButton("Delete");
		btnRemoveResidential.addActionListener(actionListenerRemoveButtons);
		btnClearInfoResidential = new JButton("Clear");
		btnClearInfoResidential.addActionListener(actionListenerClearButtons);
		
		//add the components to the panel
		panel.add(lblRightPanelLabel);
		panel.add(lblIdResidential);
		panel.add(tfIdResidential);
		
		panel.add(lblAddressResidential);
		panel.add(tfAddressResidential);
		
		panel.add(lblAskingPriceResidential);
		panel.add(tfAskingPriceResidential);
		
		panel.add(lblQuadrantResidential);
		panel.add(cbQuadrantResidential);
		
		panel.add(lblZoneResidential);
		panel.add(cbZoneResidential);
		
		panel.add(lblAreaResidential);
		panel.add(tfAreaResidential);
		
		panel.add(lblBedroomsResidential);
		panel.add(tfBedroomsResidential);
		
		panel.add(lblBathroomsResidential);
		panel.add(tfBathroomsResidential);
		
		panel.add(lblGarageTypeResidential);
		panel.add(cbGarageTypeResidential);
		
		panel.add(lblLegalDescriptionResidential);
		panel.add(tfLegalDescriptionResidential);
		
		panel.add(lblCommentsResidential);
		panel.add(tfCommentsResidential);
		
		panel.add(btnAddResidential);
		panel.add(btnRemoveResidential);
		panel.add(btnClearInfoResidential);

		
		return panel;
	}
	
	/**
	 * getLeftCenterPanel creates the left panel.
	 * This will be further split into a Top and
	 * Bottom panel.
	 * @return a JPanel of the complete left panel
	 */
	private JPanel getLeftCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanelResidential());
		panel.add(getBottomLeftCenterPanelResidential());
		return panel;
	}
	
	/**
	 * getBottomLeftCenterPanel creates the bottom
	 * panel on the left side.  This will hold
	 * all the search results.
	 * @return a JPanle of the complete bottom left
	 * panel
	 */
	private JPanel getBottomLeftCenterPanelResidential() {
		JPanel panel = new JPanel();
		
		//create the JList to show search results
		listModelResidential = new DefaultListModel<String>();
		Border listBorder = BorderFactory.createEtchedBorder();
		panel.setBorder(listBorder);
		lstSearchResultResidential = new JList<String>(listModelResidential);
		lstSearchResultResidential.addListSelectionListener(new MyListSelectionListenerResidential());
		JScrollPane scrollPane = new JScrollPane(lstSearchResultResidential);
		lstSearchResultResidential.setFixedCellWidth(260);
		lstSearchResultResidential.setVisibleRowCount(15);
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
	private JPanel getTopLeftCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.LIGHT_GRAY);
		
		//create search functionality for Residential Property
		lblSearchByResidential = new JLabel("Search By:");
		lblSearchByResidential.setFont(new Font("Arial", Font.BOLD, 20));
		cbSearchByResidential = new JComboBox<String>();
		cbSearchByResidential.addItem("Id");
		cbSearchByResidential.addItem("Asking Price");
		cbSearchByResidential.addItem("Number of Bedrooms");
		cbSearchByResidential.addItem("Garage Type");
		cbSearchByResidential.addItem("Quadrant");
		cbSearchByResidential.addItem("Zone");

		
		tfSearchResidential = new JTextField();
		
		btnSearchResidential = new JButton("Search");
		btnSearchResidential.addActionListener(actionListenerResidential);
		btnClearSearchResidential = new JButton("Clear");
		btnClearSearchResidential.addActionListener(actionListenerClearButtons);
		
		panel.add(lblSearchByResidential);
		panel.add(cbSearchByResidential);
		panel.add(tfSearchResidential);
		panel.add(btnSearchResidential);
		panel.add(btnClearSearchResidential);
		return panel;
	}
	
	/**
	 * getNorthCenterPanle creates the very top 
	 * panel which holds the buttons to navigate  
	 * through the Client, Residential, and 
	 * Commercial buttons.
	 * @return a JPanel of the very top panel.
	 */
	
	private JPanel getNorthCenterPanelResidential() {
		
		//create panel and Label at top for "RESIDENTIAL"
		JPanel panel = new JPanel();
		JLabel label = new JLabel("RESIDENTIAL");
		Border headingborder = BorderFactory.createEtchedBorder();
		panel.setBorder(headingborder);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		panel.add(label);
		panel.setBackground(Color.LIGHT_GRAY);
		return panel;
	}
	
	/**
	 * clearSearchClient clears the 
	 * search textfield of text and
	 * also clears the JList of items. 
	 */
	
	private void clearSearchResidential() 
	{
		//set search textfield to emptyString
		tfSearchResidential.setText("");
		//clear out DefaulListModel
		listModelResidential.clear();

	}
	
	/**
	 * clearInformationClient clears all the
	 * textfields in the right panel. 
	 */
	
	private void clearInformationResidential()
	{
		/*
		 * set all textfields to empty string, expect id, set to zero
		 * so user can add new Residential Property. 
		 */
		tfIdResidential.setText("0");
		tfAddressResidential.setText("");
		tfAskingPriceResidential.setText("");
		cbQuadrantResidential.setSelectedItem("");
		cbZoneResidential.setSelectedItem("");
		tfAreaResidential.setText("");
		tfBedroomsResidential.setText("");
		tfBathroomsResidential.setText("");
		tfCommentsResidential.setText("");
		tfLegalDescriptionResidential.setText("");
		cbGarageTypeResidential.setSelectedItem("");
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
		boolean isValidLength = true;
		
		/*
		 * if String in textfield is over max set the boolean isValidLength to false
		 */
		if(field.length() > maxLength) 
		{
			isValidLength = false;
		}
		
		return isValidLength;
	}
	
	/**
	 * MyActionListener2 searches for whatever the user typed
	 * in the textfield in an ArrayList. If it is found, if will
	 * print it out in the JList.  
	 * @author 783661
	 *
	 */
	

	/**
	 * checkFieldsEmpty will validate that every field has
	 * information entered before it can be saved. 
	 * @return a boolean if all is filled =true, any empty = false
	 */
	private boolean checkFieldsEmpty() 
	{
		boolean fieldsFilled = true;
		
		if(tfIdResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfAddressResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfAskingPriceResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfAreaResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfBedroomsResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfBathroomsResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfCommentsResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfLegalDescriptionResidential.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		
		
		return fieldsFilled;
		
	}	
	//******************************************************************
	
	private class MyActionListenerResidential implements ActionListener  {

		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
				/*
				 * if btnSearchResidential is clicked display search results
				 * specified by criteria user provided 
				 */
				if(e.getSource() == btnSearchResidential) {
					ResidentialProperty residential = new ResidentialProperty();

					
					switch(cbSearchByResidential.getSelectedItem().toString()) 
					{
						case "Id":
							residential.setId(Long.parseLong(tfSearchResidential.getText()));
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
						case "Asking Price":
							residential.setAskingPrice(Double.parseDouble(tfSearchResidential.getText()));
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
						case "Number of Bedrooms":
							residential.setBedrooms(Integer.parseInt(tfSearchResidential.getText()));
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
						case "Garage Type":
							residential.setGarage(Character.toUpperCase(tfSearchResidential.getText().charAt(0)));
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
						case "Quadrant":
							residential.setQuadrant(tfSearchResidential.getText().toUpperCase());
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
						case "Zone":
							residential.setZone(tfSearchResidential.getText().toUpperCase());
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
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

	private class MyListSelectionListenerResidential implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			//index of user selection in JList
			index = lstSearchResultResidential.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResultResidential.get(index));
				
				//display the information of the selected item in the right Panel
				tfIdResidential.setText(searchResultResidential.get(index).getId() + "");
				tfAddressResidential.setText(searchResultResidential.get(index).getAddress());
				tfAskingPriceResidential.setText(searchResultResidential.get(index).getAskingPrice() + "");
				cbQuadrantResidential.setSelectedItem(searchResultResidential.get(index).getQuadrant());
				cbZoneResidential.setSelectedItem(searchResultResidential.get(index).getZone());
				tfAreaResidential.setText(searchResultResidential.get(index).getArea()+ "");
				tfBedroomsResidential.setText(searchResultResidential.get(index).getBedrooms() + "");
				tfBathroomsResidential.setText(searchResultResidential.get(index).getBathrooms() + "");
				tfCommentsResidential.setText(searchResultResidential.get(index).getComments());
				tfLegalDescriptionResidential.setText(searchResultResidential.get(index).getLegalDescription());
				cbGarageTypeResidential.setSelectedItem(searchResultResidential.get(index).getGarage() + "");
			
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
			
			//if user clicks btnAddResidential add the Residential Property to database
			if(e.getSource() == btnAddResidential) 
			{
				boolean validAddressLength = true;
				boolean fieldsFilled = true;
				double newAskingPrice = -1;
				double newArea = -1;
				int newBedrooms = -1;
				double newBathrooms = -1;
				ResidentialProperty newResidential = null;
				
				//assign what user entered in textfield to new variables
				int newId = Integer.parseInt(tfIdResidential.getText());
				String newAddress = tfAddressResidential.getText();
				validAddressLength = maxTextFieldLength(80, newAddress);
				
				try {
						newAskingPrice = Double.parseDouble(tfAskingPriceResidential.getText());
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid format for Asking Price. Please check you entered in numeric format.");
				}
				String newQuadrant = (String) cbQuadrantResidential.getSelectedItem();
				String newZone = (String) cbZoneResidential.getSelectedItem();
				try {
					newArea = Double.parseDouble(tfAreaResidential.getText());
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid format for Area. Please check you entered in numeric format.");
				}
				try {
					newBedrooms = Integer.parseInt(tfBedroomsResidential.getText());
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid format for Bedrooms. Please check you entered in numeric format.");
				}
				try {
					newBathrooms = Double.parseDouble(tfBathroomsResidential.getText());
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid format Bathrooms. Please check you " +
								"entered in numeric format.");
				}
				char newGarageType = ((String) cbGarageTypeResidential.getSelectedItem()).charAt(0);
				String newLegalDescription = tfLegalDescriptionResidential.getText();
				String newComments = tfCommentsResidential.getText();
				
				fieldsFilled = checkFieldsEmpty();
				
				//try is for LegalDrescription and number of bathrooms format exceptions
				try {
					
					//validate String length entered into Address
					if(validAddressLength && fieldsFilled) 
					{
						//add new attributes to newResidential object
						newResidential = new ResidentialProperty(newId, newLegalDescription, newAddress, newQuadrant, newZone, 
							newAskingPrice, newComments, newArea, newBathrooms, newBedrooms, newGarageType);
						//add newResidentail with the ResidentialPropertyBroker using persist method
						rpb.persist(newResidential);
						
						//display new id so user can see it
						tfIdResidential.setText(newResidential.getId() + "");
						
						//display message so user knows it worked
						JOptionPane.showMessageDialog(null, "Item Saved! Id#: " + newResidential.getId());
						//clear information so user can start fresh. 
						clearInformationResidential();
					}
					//messages if it doesn't pass validations and exceptions
					else if(validAddressLength == false)
					{
						JOptionPane.showMessageDialog(null, "Over Character Limit in Address!");
					}
					else if(fieldsFilled == false) 
					{
						JOptionPane.showMessageDialog(null, "A field is empty. Please return and enter information!");
					}
				} catch (InvalidLegalDescriptionException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invalid format for Legal Description!");
				}catch(InvalidNumberOfBathroomsException e2) {
					
					JOptionPane.showMessageDialog(null, "Invalid format for NumberOfBathrooms!");
				
				}
					
			}
			
		}

	}
	
	/**
	 * MyActionListenerRemoveButtons will remove an objecct
	 * from the ArrayList.  The object is determined by
	 * what the user selected in the JList. 
	 * @author 783661
	 *
	 */
	
	private class MyActionListenerRemoveButtons implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//if user clicks btnRemoveResidential
			if(e.getSource() == btnRemoveResidential) 
			{
				//try if there is nothing there to remove
				try {
					rpb.remove(searchResultResidential.get(index));
					JOptionPane.showMessageDialog(null, "Item Removed");
					clearInformationResidential();
					clearSearchResidential();
			
				}catch(Exception e1) 
				{
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
			// TODO Auto-generated method stub
			
			//clear information from screen
			if(e.getSource() == btnClearSearchResidential) 
			{
				clearSearchResidential();
			}
			else if(e.getSource() == btnClearInfoResidential) 
			{
				clearInformationResidential();
			}

			
		}
		
	}

}
