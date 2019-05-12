/**
 * GUICommercial is a class that hold all the methods and 
 * components needed in the Commercial Panel.  
 * 
 * @author Jocelyn Wegen
 * @version Febraury 17, 2019
 */

package frontEndGUI.mls.gui;

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

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.persistance.CommercialPropertyBroker;
import saitMLS.problemDomain.CommercialProperty;


public class GUICommercial {
	
	/**
	 * actionListener2 is an ActionListner that listens
	 * for a button to perform a search function.  
	 */
	private ActionListener actionListenerCommercial;
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

	//commercial list and search variables
	/**
	 * lstSearchResult is a JList that displays the searched 
	 * results. 
	 */
	private JList<String> lstSearchResultCommercial;
	/**
	 * listModel is a DefaultListModel that holds 
	 * the searched list to display in the JList. 
	 */
	private DefaultListModel<String> listModelCommercial;
	/**
	 * cpb is an object of the CommercialPropertyBroker to get access
	 * to the methods in that class. 
	 */
	private CommercialPropertyBroker cpb;
	/**
	 * searchResult is an ArrayList of items
	 * created from the Broker. It is used to
	 * hold items that have been searched
	 * based on a search criteria (such as
	 * Id, address, etc.)
	 * 
	 */
	private ArrayList<CommercialProperty> searchResultCommercial;
	
	//Commercial top left center Components
	/**
	 * lblSearchByCommercial is a JLabel "Search by:" 
	 */
	private JLabel lblSearchByCommercial;
	/**
	 * cbSearchByCommerical is a JComboBox that holds 
	 * all the different variations a user can
	 * search by.
	 */
	private JComboBox<String>cbSearchByCommercial;
	/**
	 * tfSearchCommerical is a JTextField that a user
	 * can use to enter what they want to search.
	 */
	private JTextField tfSearchCommercial;
	/**
	 * btnSearchCommercial is a JButton that a user
	 * can click to search for whatever criteria 
	 * they have entered. 
	 */
	private JButton btnSearchCommercial;
	/**
	 * btnClearSearchCommercial is a JButton that a
	 * user can click to clear the JList and 
	 * textfield in the search function part of 
	 * the GUI. 
	 */
	private JButton btnClearSearchCommercial;
	
	//Commercial right center Components
	
	/**
	 * rightPanelLabel is a JLabel for the
	 * rightPanel label.  
	 */
	private JLabel lblRightPanelLabel;
	
	/**
	 * lblIdCommercail is a Jlabel for the id
	 */
	private JLabel lblIdCommercial;
	/**
	 * tfIdCommercial is a JTextField for the id
	 */
	private JTextField tfIdCommercial;
	/**
	 * lblAddressCommercial is a JLabel
	 * for Address
	 */
	private JLabel lblAddressCommercial;
	/**
	 * tfAddressCommercial is a JTextField
	 * for the user to enter an address. 
	 */
	private JTextField tfAddressCommercial;
	/**
	 * lblAskingPriceCommercial is a JLabel
	 * for Asking Price
	 */
	private JLabel lblAskingPriceCommercial;
	/**
	 * tfAskingPriceCommercial is a JTextField
	 * so user can enter the Asking Price
	 */
	private JTextField tfAskingPriceCommercial;
	/**
	 * lblQuadrantCommercial is a JLabel for the 
	 * Quadrant. 
	 */
	private JLabel lblQuadrantCommercial;
	/**
	 * cbQuadrantCommercial is a JComboBox so the
	 * user can select the quadrant. 
	 */
	private JComboBox<String> cbQuadrantCommercial;
	/**
	 *  lblZoneCommercial is a JLabel for the Zone
	 */
	private JLabel lblZoneCommercial;
	/**
	 * cbZoneCommercial is a JComboBox
	 * that the user can select a Zone. 
	 */
	private JComboBox<String> cbZoneCommercial;
	/**
	 * lblFloorsCommercial is a JLabel for the
	 * Floors
	 */
	private JLabel lblFloorsCommercial;
	/**
	 * tfFloorsCommercial is a JTextField that 
	 * the user can enter the number of floors
	 * in a commerical property. 
	 */
	private JTextField tfFloorsCommercial;
	/**
	 * lblTypeCommercial is a JLabel for the 
	 * type
	 */
	private JLabel lblTypeCommercial;
	/**
	 * cbTypeCommercial is a JComboBox for the
	 * user to select the commercial property type. 
	 */
	private JComboBox<String> cbTypeCommercial;
	/**
	 * lblLegalDescriptionCommercial is a JLabel for 
	 * the Legal Description
	 */
	private JLabel lblLegalDescriptionCommercial;
	/**
	 * tfLegalDescriptionCommercial is a JTextField
	 * for the user to enter the Legal Description
	 */
	private JTextField tfLegalDescriptionCommercial;
	/**
	 * lblCommentsCommercial is a JLabel for 
	 * the comments
	 */
	private JLabel lblCommentsCommercial;
	/**
	 * tfCommentsCommercial is a JTextField
	 * for the user to enter comments
	 */
	private JTextField tfCommentsCommercial;
	/**
	 * btnAddCommercial is a JButton that the 
	 * user can click to add or save a modified
	 * commercial property. 
	 */
	private JButton btnAddCommercial;
	/**
	 * btnRemoveCommercial is a JButton that the
	 * user can click to remove a commercial property. 
	 */
	private JButton btnRemoveCommercial;
	/**
	 * btnClearInfoCommercial is a JButton the user
	 * can click to clear the information displayed in the
	 * right panel. 
	 */
	private JButton btnClearInfoCommercial;
	
	/**
	 * Constructor
	 * Accepts no parameters
	 */
	public GUICommercial() 
	{
		actionListenerCommercial = new MyActionListenerCommercial();
		actionListenerAddButtons = new MyActionListenerAddButtons();
		actionListenerRemoveButtons = new MyActionListenerRemoveButtons();
		actionListenerClearButtons = new MyActionListenerClearButtons();
		
		cpb = CommercialPropertyBroker.getBroker();
	}
	
	/**
	 * getCommercialPanel contains all the panels that 
	 * make up the commercial panel.
	 * @return a JPanel of the complete commercial panel
	 */
	public JPanel getCommercialPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthCenterPanelCommercial(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanelCommercial(), BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * getCenterCenterPanel creates the whole center
	 * panel, which will hold all the subsection 
	 * panels for the center.  
	 * @return a JPanel of complete center panel
	 */
	private JPanel getCenterCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanelCommercial());
		panel.add(getRightCenterPanelCommercial());
		return panel;
	}
	
	/**
	 * getRightCenterPanel creates the right panel.
	 * This will hold all the details of the Clinet.
	 * @return a Jpanel of the complete right panel
	 */
	private JPanel getRightCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.LIGHT_GRAY);
		
		//heading
		lblRightPanelLabel = new JLabel("Commercial Property Information");
		lblRightPanelLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		//create all the components for panel
		lblIdCommercial = new JLabel("Id:");
		tfIdCommercial = new JTextField();
		/*
		 * set id to zero so that when user enters a new item
		 * it will generate a new id for them. 
		 */
		tfIdCommercial.setText("0");
		/*
		 * make id not editable so user can not change id. 
		 */
		tfIdCommercial.setEditable(false);
		
		lblAddressCommercial = new JLabel("Address:");
		tfAddressCommercial = new JTextField();
		
		lblAskingPriceCommercial = new JLabel("Asking Price:");
		tfAskingPriceCommercial = new JTextField();
		
		lblQuadrantCommercial = new JLabel("Quadrant:");
		cbQuadrantCommercial = new JComboBox<String>();
		cbQuadrantCommercial.addItem("NE");
		cbQuadrantCommercial.addItem("NW");
		cbQuadrantCommercial.addItem("SE");
		cbQuadrantCommercial.addItem("SW");
		
		
		lblZoneCommercial = new JLabel("Zone:");
		cbZoneCommercial = new JComboBox<String>();
		cbZoneCommercial.addItem("R1");
		cbZoneCommercial.addItem("R2");
		cbZoneCommercial.addItem("R3");
		cbZoneCommercial.addItem("R4");
		cbZoneCommercial.addItem("I1");
		cbZoneCommercial.addItem("I2");
		cbZoneCommercial.addItem("I3");
		cbZoneCommercial.addItem("I4");
		
		lblFloorsCommercial = new JLabel("Number of Floors:");
		tfFloorsCommercial = new JTextField();
		
		lblTypeCommercial = new JLabel("Type:");
		cbTypeCommercial = new JComboBox<String>();
		cbTypeCommercial.addItem("O");
		cbTypeCommercial.addItem("M");
		
		
		lblLegalDescriptionCommercial = new JLabel("LegalDescription:");
		tfLegalDescriptionCommercial = new JTextField();
		
		lblCommentsCommercial = new JLabel("Comments:");
		tfCommentsCommercial = new JTextField();
		
		btnAddCommercial = new JButton("Save");
		btnAddCommercial.addActionListener(actionListenerAddButtons);
		btnRemoveCommercial = new JButton("Delete");
		btnRemoveCommercial.addActionListener(actionListenerRemoveButtons);
		btnClearInfoCommercial = new JButton("Clear");
		btnClearInfoCommercial.addActionListener(actionListenerClearButtons);
		
		//add components to the panel
		panel.add(lblRightPanelLabel);
		panel.add(lblIdCommercial);
		panel.add(tfIdCommercial);
		
		panel.add(lblAddressCommercial);
		panel.add(tfAddressCommercial);
		
		panel.add(lblAskingPriceCommercial);
		panel.add(tfAskingPriceCommercial);
		
		panel.add(lblQuadrantCommercial);
		panel.add(cbQuadrantCommercial);
		
		panel.add(lblZoneCommercial);
		panel.add(cbZoneCommercial);
		
		panel.add(lblFloorsCommercial);
		panel.add(tfFloorsCommercial);
		
		panel.add(lblTypeCommercial);
		panel.add(cbTypeCommercial);
		
		panel.add(lblLegalDescriptionCommercial);
		panel.add(tfLegalDescriptionCommercial);
		
		panel.add(lblCommentsCommercial);
		panel.add(tfCommentsCommercial);
		
		panel.add(btnAddCommercial);
		panel.add(btnRemoveCommercial);
		panel.add(btnClearInfoCommercial);

		
		return panel;
	}
	
	/**
	 * getLeftCenterPanel creates the left panel.
	 * This will be further split into a Top and
	 * Bottom panel.
	 * @return a JPanel of the complete left panel
	 */
	private JPanel getLeftCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanelCommercial());
		panel.add(getBottomLeftCenterPanelCommercial());
		return panel;
	}
	
	/**
	 * getBottomLeftCenterPanel creates the bottom
	 * panel on the left side.  This will hold
	 * all the search results.
	 * @return a JPanle of the complete bottom left
	 * panel
	 */
	private JPanel getBottomLeftCenterPanelCommercial() {
		JPanel panel = new JPanel();
		Border listBorder = BorderFactory.createEtchedBorder();
		
		/*
		 * create JList so user can see search results 
		 * and select from search results
		 */
		panel.setBorder(listBorder);
		listModelCommercial = new DefaultListModel<String>();
		lstSearchResultCommercial = new JList<String>(listModelCommercial);
		lstSearchResultCommercial.addListSelectionListener(new MyListSelectionListenerCommercial());
		JScrollPane scrollPane = new JScrollPane(lstSearchResultCommercial);
		lstSearchResultCommercial.setFixedCellWidth(260);
		lstSearchResultCommercial.setVisibleRowCount(15);
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
	private JPanel getTopLeftCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.LIGHT_GRAY);
		
		//create components
		lblSearchByCommercial = new JLabel("Search By:");
		lblSearchByCommercial.setFont(new Font("Arial", Font.BOLD, 20));
		cbSearchByCommercial = new JComboBox<String>();
		cbSearchByCommercial.addItem("Id");
		cbSearchByCommercial.addItem("Asking Price");
		//cbSearchByCommercial.addItem("Type of Business");
		//cbSearchByCommercial.addItem("Number of Floors");
		cbSearchByCommercial.addItem("Quadrant");
		cbSearchByCommercial.addItem("Legal Description");
		//cbSearchByCommercial.addItem("Zone");

		tfSearchCommercial = new JTextField();
		
		btnSearchCommercial = new JButton("Search");
		btnSearchCommercial.addActionListener(actionListenerCommercial);
		btnClearSearchCommercial = new JButton("Clear");
		btnClearSearchCommercial.addActionListener(actionListenerClearButtons);
		
		//add components to panel
		panel.add(lblSearchByCommercial);
		panel.add(cbSearchByCommercial);
		panel.add(tfSearchCommercial);
		panel.add(btnSearchCommercial);
		panel.add(btnClearSearchCommercial);
		return panel;
	}
	
	/**
	 * getNorthCenterPanle creates the very top 
	 * panel which holds the buttons to navigate  
	 * through the Client, Residential, and 
	 * Commercial buttons.
	 * @return a JPanel of the very top panel.
	 */
	private JPanel getNorthCenterPanelCommercial() {
		
		//create header panel for "COMMERCIAL"
		JPanel panel = new JPanel();
		JLabel label = new JLabel("COMMERCIAL");
		Border headingborder = BorderFactory.createEtchedBorder();
		panel.setBorder(headingborder);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		panel.add(label);
		panel.setBackground(Color.LIGHT_GRAY);
		return panel;
	}
	
	/**
	 * clearSearchCommercial clears the 
	 * search textfield of text and
	 * also clears the JList of items. 
	 */
	private void clearSearchCommercial() 
	{
		//set tfSearchCommerical to empty String
		tfSearchCommercial.setText("");
		//clear out JList
		listModelCommercial.clear();

	}
	
	/**
	 * clearInformationClient clears all the
	 * textfields in the right panel. 
	 */
	private void clearInformationCommercial()
	{
		/*
		 * set all textfields in right panel to empty String. 
		 * Except id, set to 0 so user can add new Commercial 
		 * property
		 */
		tfIdCommercial.setText("0");
		tfAddressCommercial.setText("");
		tfAskingPriceCommercial.setText("");
		cbQuadrantCommercial.setSelectedItem("");
		cbZoneCommercial.setSelectedItem("");
		tfFloorsCommercial.setText("");
		tfLegalDescriptionCommercial.setText("");
		tfCommentsCommercial.setText("");
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
		
		//if user goes over maxLength isValidLength will be set to false
		if(field.length() > maxLength) 
		{
			isValidLength = false;
		}
		
		return isValidLength;
	} 
	
	/**
	 * checkFieldsEmpty will validate that every field has
	 * information entered before it can be saved. 
	 * @return a boolean if all is filled =true, any empty = false
	 */
	private boolean checkFieldsEmpty() 
	{
		boolean fieldsFilled = true;
		
		if(tfIdCommercial.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfAddressCommercial.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfAskingPriceCommercial.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfFloorsCommercial.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfLegalDescriptionCommercial.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		if(tfCommentsCommercial.getText().equals("")) 
		{
			fieldsFilled = false;
		}
		
		return fieldsFilled;
		
	}	
	//******************************************************************
	/**
	 * MyActionListener2 searches for whatever the user typed
	 * in the textfield in an ArrayList. If it is found, if will
	 * print it out in the JList.  
	 * @author 783661
	 *
	 */
	private class MyActionListenerCommercial implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			
				if(e.getSource() == btnSearchCommercial) {
					
					//if there is already a search in JList clear it out
					if(listModelCommercial.size() >0) 
					{
						//clear out the DefaultListModel list
						listModelCommercial.clear();	
					}
					
					CommercialProperty commercial = new CommercialProperty();
					//client.setLastName("Duck");
					
					switch(cbSearchByCommercial.getSelectedItem().toString()) 
					{
						case "Id":
							commercial.setId(Long.parseLong(tfSearchCommercial.getText()));
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial.getId() + "", "id");
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Asking Price":
							commercial.setAskingPrice(Double.parseDouble(tfSearchCommercial.getText()));
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial.getAskingPrice() + "", "price");
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Legal Description":
						try {
							commercial.setLegalDescription(tfSearchCommercial.getText());
						} catch (InvalidLegalDescriptionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial.getLegalDescription() + "", "legal description");
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						/*case "Type of Business":
							commercial.setType(tfSearchCommercial.getText().toUpperCase());
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial.getType(), "type");
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Number of Floors":
							commercial.setNoFloors(Integer.parseInt(tfSearchCommercial.getText()));
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial.getNoFloors() + "", "floors");
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;*/
						case "Quadrant":
							commercial.setQuadrant(tfSearchCommercial.getText().toUpperCase());
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial.getQuadrant(), "quadrant");
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						/*case "Zone":
							commercial.setZone(tfSearchCommercial.getText().toUpperCase());
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial.getZone(), "zone");
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;*/
						
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
	private class MyListSelectionListenerCommercial implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			//to hold index of item user selected in JList
			index = lstSearchResultCommercial.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResultCommercial.get(index));
				//display attributes of item selected in right panel
				tfIdCommercial.setText(searchResultCommercial.get(index).getId() + "");
				tfAddressCommercial.setText(searchResultCommercial.get(index).getAddress());
				tfAskingPriceCommercial.setText(searchResultCommercial.get(index).getAskingPrice() + "");
				cbQuadrantCommercial.setSelectedItem(searchResultCommercial.get(index).getQuadrant());
				cbZoneCommercial.setSelectedItem(searchResultCommercial.get(index).getZone());
				tfFloorsCommercial.setText(searchResultCommercial.get(index).getNoFloors() + "");
				cbTypeCommercial.setSelectedItem(searchResultCommercial.get(index).getType());
				tfLegalDescriptionCommercial.setText(searchResultCommercial.get(index).getLegalDescription());
				tfCommentsCommercial.setText(searchResultCommercial.get(index).getComments());
				
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

			if(e.getSource() == btnAddCommercial) 
			{
				boolean validAddressLength = true;
				boolean fieldsFilled = true;
				double newAskingPrice = -1;
				int newFloors = -1;
				CommercialProperty newCommercial = null;
				
				//assign what user entered in textfields into variables
				long newId = Long.parseLong(tfIdCommercial.getText());
				String newAddress = tfAddressCommercial.getText();
				validAddressLength = maxTextFieldLength(80, newAddress);
				try {
					newAskingPrice = Double.parseDouble(tfAskingPriceCommercial.getText());
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid format Bathrooms. Please check you " +
								"entered in numeric format.");
				}
				String newQuadrant = (String) cbQuadrantCommercial.getSelectedItem();
				String newZone = (String) cbZoneCommercial.getSelectedItem();
				try {
					newFloors = Integer.parseInt(tfFloorsCommercial.getText());
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid format Bathrooms. Please check you " +
								"entered in numeric format.");
				}
				String newType = cbTypeCommercial.getSelectedItem().toString();
				String newLegalDescription = tfLegalDescriptionCommercial.getText();
				String newComments = tfCommentsCommercial.getText();
				
				fieldsFilled = checkFieldsEmpty();
				
				//try is for Legal Description Exception
				try {
					//validation for address length
					if(validAddressLength && fieldsFilled) 
					{
						//if everything passes exceptions and validations, add new variables to newCommerical object
						newCommercial = new CommercialProperty(newId,  newLegalDescription, newAddress, newQuadrant, newZone, 
							newAskingPrice, newComments, newType, newFloors);
						cpb.persist(newCommercial);
						tfIdCommercial.setText(newCommercial.getId() + "");
						
						JOptionPane.showMessageDialog(null, "Item Saved! Id#: " + newCommercial.getId());
						clearInformationCommercial();
					}
					
					//messages if it doesn't pass exceptions and validations
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
					JOptionPane.showMessageDialog(null, "Invlaid format for Legal Description!");
				}catch(Exception e3) 
				{
					JOptionPane.showMessageDialog(null, "Error! Please confirm data you are adding!");
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
			/*
			 * if user clicks btnRemoveCommerical it will remove
			 * commerical property from databases 
			 */
			if(e.getSource() == btnRemoveCommercial) 
			{
				//try if there is nothing to remove
				try {
					//uses the CommerialPropertyBroker remove method to remove item
					cpb.remove(searchResultCommercial.get(index));
					JOptionPane.showMessageDialog(null, "Item Removed");
					clearInformationCommercial();
					clearSearchCommercial();
				}catch(Exception e1) 
				{
					//message if nothing to remove
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
			//buttons used to clear the information from the screens so user can start fresh
			if(e.getSource() == btnClearSearchCommercial) 
			{
				clearSearchCommercial();
			}
			else if(e.getSource() == btnClearInfoCommercial) 
			{
				clearInformationCommercial();
			}
			
		}
		
	}
}
