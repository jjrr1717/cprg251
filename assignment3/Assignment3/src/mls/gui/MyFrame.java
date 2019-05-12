package mls.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.mls.exceptions.clientale.InvalidClientTypeException;
import sait.mls.exceptions.clientale.InvalidPostalCodeException;
import sait.mls.persistence.clientale.ClientBroker;
import sait.mls.persistence.property.CommercialPropertyBroker;
import sait.mls.persistence.property.ResidentialPropertyBroker;
import sait.mls.problemdomain.clientale.Client;
import sait.mls.problemdomain.property.CommercialProperty;
import sait.mls.problemdomain.property.ResidentialProperty;


public class MyFrame {


	private JButton btnClient;
	private JButton btnResidential;
	private JButton btnCommercial;
	private ArrayList<JPanel> panelList;
	private ActionListener actionListener;
	private ActionListener actionListener2;
	private ActionListener actionListenerResidential;
	private ActionListener actionListenerCommercial;
	
	private JFrame frame;
	
	//client list and search variables
	private JList <String>lstSearchResult;
	private DefaultListModel<String> listModel;
	private ClientBroker cb;
	private ArrayList<Client> searchResult;
	
	//residential list and search variables
	private JList<String> lstSearchResultResidential;
	private DefaultListModel<String> listModelResidential;
	private ResidentialPropertyBroker rpb;
	private ArrayList<ResidentialProperty> searchResultResidential;
	
	//commercial list and search variables
	private JList<String> lstSearchResultCommercial;
	private DefaultListModel<String> listModelCommercial;
	private CommercialPropertyBroker cpb;
	private ArrayList<CommercialProperty> searchResultCommercial;
	
	//Client top left center components
	private JLabel lblSearchByClient;
	private JComboBox<String>cbSearchByClient;
	private JTextField tfSearchClient;
	private JButton btnSearchClient;
	
	//Client right center components
	private JLabel lblIdClient;
	private JTextField tfIdClient;
	private JLabel lblFirstNameClient;
	private JTextField tfFirstNameClient;
	private JLabel lblLastNameClient;
	private JTextField tfLastNameClient;
	private JLabel lblTypeClient;
	private JTextField tfTypeClient;
	private JLabel lblPhoneNumberClient;
	private JTextField tfPhoneNumberClient;
	private JLabel lblPostalCodeClient;
	private JTextField tfPostalCodeClient;
	private JButton btnAddClient;
	private JButton btnRemoveClient;
	private JButton btnUpdateClient;
	private JButton btnClose;
	
	//Residential top left center Components
	private JLabel lblSearchByResidential;
	private JComboBox<String>cbSearchByResidential;
	private JTextField tfSearchResidential;
	private JButton btnSearchResidential;
	
	//Residential right center Components
	private JLabel lblIdResidential;
	private JTextField tfIdResidential;
	private JLabel lblAddressResidential;
	private JTextField tfAddressResidential;
	private JLabel lblAskingPriceResidential;
	private JTextField tfAskingPriceResidential;
	private JLabel lblQuadrantResidential;
	private JTextField tfQuadrantResidential;
	private JLabel lblZoneResidential;
	private JTextField tfZoneResidential;
	private JLabel lblAreaResidential;
	private JTextField tfAreaResidential;
	private JLabel lblBedroomsResidential;
	private JTextField tfBedroomsResidential;
	private JLabel lblBathroomsResidential;
	private JTextField tfBathroomsResidential;
	private JLabel lblGarageTypeResidential;
	private JTextField tfGarageTypeResidential;
	private JLabel lblLegalDescriptionResidential;
	private JTextField tfLegalDescriptionResidential;
	private JLabel lblCommentsResidential;
	private JTextField tfCommentsResidential;
	private JButton btnAddResidential;
	private JButton btnRemoveResidential;
	private JButton btnUpdateResidential;
	
	//Commercial top left center Components
	private JLabel lblSearchByCommercial;
	private JComboBox<String>cbSearchByCommercial;
	private JTextField tfSearchCommercial;
	private JButton btnSearchCommercial;
	
	//Commercial right center Components
	private JLabel lblIdCommercial;
	private JTextField tfIdCommercial;
	private JLabel lblAddressCommercial;
	private JTextField tfAddressCommercial;
	private JLabel lblAskingPriceCommercial;
	private JTextField tfAskingPriceCommercial;
	private JLabel lblQuadrantCommercial;
	private JTextField tfQuadrantCommercial;
	private JLabel lblZoneCommercial;
	private JTextField tfZoneCommercial;
	private JLabel lblFloorsCommercial;
	private JTextField tfFloorsCommercial;
	private JLabel lblTypeCommercial;
	private JTextField tfTypeCommercial;
	private JLabel lblLegalDescriptionCommercial;
	private JTextField tfLegalDescriptionCommercial;
	private JLabel lblCommentsCommercial;
	private JTextField tfCommentsCommercial;
	private JButton btnAddCommercial;
	private JButton btnRemoveCommercial;
	private JButton btnUpdateCommercial;
	
	

	public MyFrame() {
		initialize();
	}

	private void initialize() {
		cb = ClientBroker.getBroker();
		rpb = ResidentialPropertyBroker.getBroker();
		cpb = CommercialPropertyBroker.getBroker();
		frame = new JFrame("SAIT MLS");
		frame.setBounds(10, 10, 500, 500);
		actionListener = new MyActionListener();
		actionListener2 = new MyActionListener2();
		actionListenerResidential = new MyActionListenerResidential();
		actionListenerCommercial = new MyActionListenerCommercial();
		panelList = new ArrayList<JPanel>();
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		panelList.add(getClientPanel());
		panelList.add(getResidentialPanel());
		panelList.add(getCommercialPanel());
		
		frame.add(panelList.get(0), BorderLayout.CENTER);
		frame.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				cb.closeBroker();
				System.exit(0);
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	private JPanel getClientPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthCenterPanel(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanel(), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel getResidentialPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthCenterPanelResidential(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanelResidential(), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel getCommercialPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getNorthCenterPanelCommercial(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanelCommercial(), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel getCenterCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanel());
		panel.add(getRightCenterPanel());
		return panel;
	}
	
	private JPanel getCenterCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanelResidential());
		panel.add(getRightCenterPanelResidential());
		return panel;
	}
	
	private JPanel getCenterCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanelCommercial());
		panel.add(getRightCenterPanelCommercial());
		return panel;
	}

	private JPanel getRightCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblIdClient = new JLabel("Id");
		tfIdClient = new JTextField();
		tfIdClient.setEditable(false);
		
		lblFirstNameClient = new JLabel("First Name:");
		tfFirstNameClient = new JTextField();
		
		lblLastNameClient = new JLabel("Last Name:");
		tfLastNameClient = new JTextField();
		
		lblTypeClient = new JLabel("Client Type:");
		tfTypeClient = new JTextField();
		
		lblPhoneNumberClient = new JLabel("Phone Number:");
		tfPhoneNumberClient = new JTextField();
		
		lblPostalCodeClient = new JLabel("Postal Code");
		tfPostalCodeClient = new JTextField();
		
		btnAddClient = new JButton("Add");
		btnRemoveClient = new JButton("Remove");
		btnUpdateClient = new JButton("Update");
		btnClose = new JButton("Close");
		
		panel.add(lblIdClient);
		panel.add(tfIdClient);
		
		panel.add(lblFirstNameClient);
		panel.add(tfFirstNameClient);
		
		panel.add(lblLastNameClient);
		panel.add(tfLastNameClient);
		
		panel.add(lblTypeClient);
		panel.add(tfTypeClient);
		
		panel.add(lblPhoneNumberClient);
		panel.add(tfPhoneNumberClient);
		
		panel.add(lblPostalCodeClient);
		panel.add(tfPostalCodeClient);
		
		panel.add(btnAddClient);
		panel.add(btnRemoveClient);
		panel.add(btnUpdateClient);
		panel.add(btnClose);
		
		return panel;
	}
	
	private JPanel getRightCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblIdResidential = new JLabel("Id:");
		tfIdResidential = new JTextField();
		tfIdResidential.setEditable(false);
		
		lblAddressResidential = new JLabel("Address:");
		tfAddressResidential = new JTextField();
		
		lblAskingPriceResidential = new JLabel("Asking Price:");
		tfAskingPriceResidential = new JTextField();
		
		lblQuadrantResidential = new JLabel("Quadrant:");
		tfQuadrantResidential = new JTextField();
		
		lblZoneResidential = new JLabel("Zone:");
		tfZoneResidential = new JTextField();
		
		lblAreaResidential = new JLabel("Area:");
		tfAreaResidential = new JTextField();
		
		lblBedroomsResidential = new JLabel("Bedrooms:");
		tfBedroomsResidential = new JTextField();
		
		lblBathroomsResidential = new JLabel("Bathrooms");
		tfBathroomsResidential = new JTextField();
		
		lblGarageTypeResidential = new JLabel("Garage Type:");
		tfGarageTypeResidential = new JTextField();
		
		lblLegalDescriptionResidential = new JLabel("Legal Description:");
		tfLegalDescriptionResidential = new JTextField();
		
		lblCommentsResidential = new JLabel("Comments:");
		tfCommentsResidential = new JTextField();
		
		btnAddResidential = new JButton("Add");
		btnRemoveResidential = new JButton("Remove");
		btnUpdateResidential = new JButton("Update");
		btnClose = new JButton("Close");
		
		panel.add(lblIdResidential);
		panel.add(tfIdResidential);
		
		panel.add(lblAddressResidential);
		panel.add(tfAddressResidential);
		
		panel.add(lblAskingPriceResidential);
		panel.add(tfAskingPriceResidential);
		
		panel.add(lblQuadrantResidential);
		panel.add(tfQuadrantResidential);
		
		panel.add(lblZoneResidential);
		panel.add(tfZoneResidential);
		
		panel.add(lblAreaResidential);
		panel.add(tfAreaResidential);
		
		panel.add(lblBedroomsResidential);
		panel.add(tfBedroomsResidential);
		
		panel.add(lblBathroomsResidential);
		panel.add(tfBathroomsResidential);
		
		panel.add(lblGarageTypeResidential);
		panel.add(tfGarageTypeResidential);
		
		panel.add(lblLegalDescriptionResidential);
		panel.add(tfLegalDescriptionResidential);
		
		panel.add(lblCommentsResidential);
		panel.add(tfCommentsResidential);
		
		panel.add(btnAddResidential);
		panel.add(btnRemoveResidential);
		panel.add(btnUpdateResidential);
		panel.add(btnClose);
		
		return panel;
	}

	private JPanel getRightCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblIdCommercial = new JLabel("Id:");
		tfIdCommercial = new JTextField();
		tfIdCommercial.setEditable(false);
		
		lblAddressCommercial = new JLabel("Address:");
		tfAddressCommercial = new JTextField();
		
		lblAskingPriceCommercial = new JLabel("Asking Price:");
		tfAskingPriceCommercial = new JTextField();
		
		lblQuadrantCommercial = new JLabel("Quadrant:");
		tfQuadrantCommercial = new JTextField();
		
		lblZoneCommercial = new JLabel("Zone:");
		tfZoneCommercial = new JTextField();
		
		lblFloorsCommercial = new JLabel("Number of Floors:");
		tfFloorsCommercial = new JTextField();
		
		lblTypeCommercial = new JLabel("Type:");
		tfTypeCommercial = new JTextField();
		
		lblLegalDescriptionCommercial = new JLabel("LegalDescription:");
		tfLegalDescriptionCommercial = new JTextField();
		
		lblCommentsCommercial = new JLabel("Comments:");
		tfCommentsCommercial = new JTextField();
		
		btnAddCommercial = new JButton("Add");
		btnRemoveCommercial = new JButton("Remove");
		btnUpdateCommercial = new JButton("Update");
		btnClose = new JButton("Close");
		
		panel.add(lblIdCommercial);
		panel.add(tfIdCommercial);
		
		panel.add(lblAddressCommercial);
		panel.add(tfAddressCommercial);
		
		panel.add(lblAskingPriceCommercial);
		panel.add(tfAskingPriceCommercial);
		
		panel.add(lblQuadrantCommercial);
		panel.add(tfQuadrantCommercial);
		
		panel.add(lblZoneCommercial);
		panel.add(tfZoneCommercial);
		
		panel.add(lblFloorsCommercial);
		panel.add(tfFloorsCommercial);
		
		panel.add(lblTypeCommercial);
		panel.add(tfTypeCommercial);
		
		panel.add(lblLegalDescriptionCommercial);
		panel.add(tfLegalDescriptionCommercial);
		
		panel.add(lblCommentsCommercial);
		panel.add(tfCommentsCommercial);
		
		panel.add(btnAddCommercial);
		panel.add(btnRemoveCommercial);
		panel.add(btnUpdateCommercial);
		panel.add(btnClose);
		
		return panel;
	}
	
	private JPanel getLeftCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanel());
		panel.add(getBottomLeftCenterPanel());
		return panel;
	}
	
	private JPanel getLeftCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanelResidential());
		panel.add(getBottomLeftCenterPanelResidential());
		return panel;
	}
	
	private JPanel getLeftCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanelCommercial());
		panel.add(getBottomLeftCenterPanelCommercial());
		return panel;
	}
	

	private JPanel getBottomLeftCenterPanel() {
		JPanel panel = new JPanel();
		listModel = new DefaultListModel<String>();
		lstSearchResult = new JList<String>(listModel);
		lstSearchResult.addListSelectionListener(new MyListSelectionListener());
		JScrollPane scrollPane = new JScrollPane(lstSearchResult);
		lstSearchResult.setFixedCellWidth(230);
		lstSearchResult.setVisibleRowCount(10);
		panel.add(scrollPane);
		panel.setBackground(Color.MAGENTA);
		return panel;
	}
	
	private JPanel getBottomLeftCenterPanelResidential() {
		JPanel panel = new JPanel();
		listModelResidential = new DefaultListModel<String>();
		//listModelResidential.add(0,"Test");
		lstSearchResultResidential = new JList<String>(listModelResidential);
		lstSearchResultResidential.addListSelectionListener(new MyListSelectionListenerResidential());
		JScrollPane scrollPane = new JScrollPane(lstSearchResultResidential);
		lstSearchResultResidential.setFixedCellWidth(230);
		lstSearchResultResidential.setVisibleRowCount(10);
		panel.add(scrollPane);
		panel.setBackground(Color.CYAN);
		return panel;
	}
	
	private JPanel getBottomLeftCenterPanelCommercial() {
		JPanel panel = new JPanel();
		listModelCommercial = new DefaultListModel<String>();
		//listModelCommercial.add(0,"Test");
		lstSearchResultCommercial = new JList<String>(listModelCommercial);
		lstSearchResultCommercial.addListSelectionListener(new MyListSelectionListenerCommercial());
		JScrollPane scrollPane = new JScrollPane(lstSearchResultCommercial);
		lstSearchResultCommercial.setFixedCellWidth(230);
		lstSearchResultCommercial.setVisibleRowCount(10);
		panel.add(scrollPane);
		panel.setBackground(Color.MAGENTA);
		return panel;
	}

	private JPanel getTopLeftCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblSearchByClient = new JLabel("Search By:");
		
		cbSearchByClient = new JComboBox<String>();
		cbSearchByClient.addItem("Id");
		cbSearchByClient.addItem("Firstname");
		cbSearchByClient.addItem("Lastname");
		cbSearchByClient.addItem("Phone Number");
		cbSearchByClient.addItem("Postal Code");
		cbSearchByClient.addItem("Type");
		
		/*rbIdClient = new JRadioButton("Id");
		rbFirstNameClient = new JRadioButton("First Name");
		rbLastNameClient = new JRadioButton("Last Name");
		rbPhoneNumberClient = new JRadioButton("Phone Number");
		rbPostalCodeClient = new JRadioButton("Postal Code");
		rbTypeClient = new JRadioButton("Type (R-residential, C-Commercial");*/
		
		tfSearchClient = new JTextField();
		
		btnSearchClient = new JButton("Search");
		btnSearchClient.addActionListener(actionListener2);
		
		panel.add(lblSearchByClient);
		panel.add(cbSearchByClient);
		/*panel.add(rbIdClient);
		panel.add(rbFirstNameClient);
		panel.add(rbLastNameClient);
		panel.add(rbPhoneNumberClient);
		panel.add(rbPostalCodeClient);
		panel.add(rbTypeClient);*/
		panel.add(tfSearchClient);
		panel.add(btnSearchClient);
		return panel;
	}
	
	private JPanel getTopLeftCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblSearchByResidential = new JLabel("Search By:");
		cbSearchByResidential = new JComboBox<String>();
		cbSearchByResidential.addItem("Id");
		cbSearchByResidential.addItem("Asking Price");
		cbSearchByResidential.addItem("Number of Bedrooms");
		cbSearchByResidential.addItem("Garage Type");
		cbSearchByResidential.addItem("Quadrant");
		cbSearchByResidential.addItem("Zone");
		/*rbIdResidential = new JRadioButton("Id");
		rbAskingPriceResidential = new JRadioButton("Asking Price");
		rbNumberOfBedroomsResidential = new JRadioButton("Number of Bedrooms");
		rbGarageTypeResidential = new JRadioButton("Garabe Type (a, d, n)");
		rbQuadrantResidential = new JRadioButton("Quadrant");
		rbZoneResidential = new JRadioButton("Zone");*/
		
		tfSearchResidential = new JTextField();
		
		btnSearchResidential = new JButton("Search");
		btnSearchResidential.addActionListener(actionListenerResidential);
		
		panel.add(lblSearchByResidential);
		panel.add(cbSearchByResidential);
		/*panel.add(rbIdResidential);
		panel.add(rbAskingPriceResidential);
		panel.add(rbNumberOfBedroomsResidential);
		panel.add(rbGarageTypeResidential);
		panel.add(rbQuadrantResidential);
		panel.add(rbZoneResidential);*/
		panel.add(tfSearchResidential);
		panel.add(btnSearchResidential);
		return panel;
	}
	
	private JPanel getTopLeftCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblSearchByCommercial = new JLabel("Search By:");
		cbSearchByCommercial = new JComboBox<String>();
		cbSearchByCommercial.addItem("Id");
		cbSearchByCommercial.addItem("Asking Price");
		cbSearchByCommercial.addItem("Type of Business");
		cbSearchByCommercial.addItem("Number of Floors");
		cbSearchByCommercial.addItem("Quadrant");
		cbSearchByCommercial.addItem("Zone");
		/*rbIdCommercial = new JRadioButton("Id");
		rbAskingPriceCommercial = new JRadioButton("AskingPrice");
		rbTypeOfBusinessCommercial = new JRadioButton("Type of Business");
		rbNumberOfFloorsCommercial = new JRadioButton("Number of Floors");
		rbQuadrantCommercial = new JRadioButton("Quadrant");
		rbZoneCommercial = new JRadioButton("Zone");*/
		
		tfSearchCommercial = new JTextField();
		
		btnSearchCommercial = new JButton("Search");
		btnSearchCommercial.addActionListener(actionListenerCommercial);
		
		panel.add(lblSearchByCommercial);
		panel.add(cbSearchByCommercial);
		/*panel.add(rbIdCommercial);
		panel.add(rbAskingPriceCommercial);
		panel.add(rbTypeOfBusinessCommercial);
		panel.add(rbNumberOfFloorsCommercial);
		panel.add(rbQuadrantCommercial);
		panel.add(rbZoneCommercial);*/
		panel.add(tfSearchCommercial);
		panel.add(btnSearchCommercial);
		return panel;
	}

	private JPanel getNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		btnClient = new JButton("Client");
		btnClient.setBorder(buttonEdge);
		btnClient.addActionListener(actionListener);
		
		btnResidential = new JButton("Residential");
		btnResidential.setBorder(buttonEdge);
		btnResidential.addActionListener(actionListener);
		
		btnCommercial = new JButton("Commercial");
		btnCommercial.setBorder(buttonEdge);
		btnCommercial.addActionListener(actionListener);
		
		panel.add(btnClient);
		panel.add(btnResidential);
		panel.add(btnCommercial);
		
		return panel;
	}
	
	private JPanel getNorthCenterPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("CLIENT");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		panel.add(label);
		panel.setBackground(Color.RED);
		return panel;
	}
	
	private JPanel getNorthCenterPanelResidential() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("RESIDENTIAL");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		panel.add(label);
		panel.setBackground(Color.RED);
		return panel;
	}
	
	private JPanel getNorthCenterPanelCommercial() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("COMMERCIAL");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("TimesRoman",Font.BOLD,28));
		panel.add(label);
		panel.setBackground(Color.RED);
		return panel;
	}
	
	/*********************************/
	
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JPanel tp;
			for(int i = 0; i < panelList.size(); i++)
			{
				tp = panelList.get(i);
				frame.remove(tp);
				tp.setVisible(false);
			}
			
			if(e.getSource() == btnClient)
			{
				tp = panelList.get(0);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			else if(e.getSource() == btnResidential)
			{
				tp = panelList.get(1);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			else if(e.getSource() == btnCommercial)
			{
				tp = panelList.get(2);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			
		}

	}
	
	private class MyActionListener2 implements ActionListener  {

		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
				
				if(e.getSource() == btnSearchClient) {
					Client client = new Client();
					//client.setLastName("Duck");
					
					switch(cbSearchByClient.getSelectedItem().toString()) 
					{
						case "Id":
							client.setClientID(Long.parseLong(tfSearchClient.getText()));
							searchResult = (ArrayList<Client>) cb.search(client);
							for(Client c : searchResult) {
								listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
						case "Firstname":
							client.setFirstName(tfSearchClient.getText());
							searchResult = (ArrayList<Client>) cb.search(client);
							for(Client c : searchResult) {
								listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
						case "Lastname":
							client.setLastName(tfSearchClient.getText());
							searchResult = (ArrayList<Client>) cb.search(client);
							for(Client c : searchResult) {
								listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
						case "Phone Number":
							client.setLastName(tfSearchClient.getText());
							searchResult = (ArrayList<Client>) cb.search(client);
							for(Client c : searchResult) {
								listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
						case "Postal Code":
						try {
							client.setPostalCode(tfSearchClient.getText());
						} catch (InvalidPostalCodeException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							searchResult = (ArrayList<Client>) cb.search(client);
							for(Client c : searchResult) {
								listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
						case "Type":
						try {
							client.setClientType(tfSearchClient.getText().charAt(0));
						} catch (InvalidClientTypeException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							searchResult = (ArrayList<Client>) cb.search(client);
							for(Client c : searchResult) {
								listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
										" " + c.getClientType());
							}
							break;
						
					}
					//client.setLastName(tfSearchClient.getText());
					//searchResult = (ArrayList<Client>) cb.search(client);
					
					/*for(Client c : searchResult) {
						//System.out.println(c);
						listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
								" " + c.getClientType());
					}*/
					
				}
			}
			
	}
	
	private class MyActionListenerResidential implements ActionListener  {

		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
				
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
							residential.setGarage(tfSearchResidential.getText().charAt(0));
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
						case "Quadrant":
							residential.setQuadrant(tfSearchResidential.getText());
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
						case "Zone":
							residential.setZone(tfSearchResidential.getText());
							searchResultResidential = (ArrayList<ResidentialProperty>) rpb.search(residential);
							for(ResidentialProperty rp : searchResultResidential) {
								listModelResidential.addElement(rp.getId()+ " " + rp.getAddress() + " " + rp.getZone());
							}
							break;
					}
					
				}
			}		
	}
	
	private class MyActionListenerCommercial implements ActionListener  {

		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
				
				if(e.getSource() == btnSearchCommercial) {
					CommercialProperty commercial = new CommercialProperty();
					//client.setLastName("Duck");
					
					switch(cbSearchByCommercial.getSelectedItem().toString()) 
					{
						case "Id":
							commercial.setId(Long.parseLong(tfSearchCommercial.getText()));
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial);
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Asking Price":
							commercial.setAskingPrice(Double.parseDouble(tfSearchCommercial.getText()));
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial);
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Type of Business":
							commercial.setType(tfSearchCommercial.getText());
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial);
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Number of Floors":
							commercial.setNoFloors(Integer.parseInt(tfSearchCommercial.getText()));
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial);
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Quadrant":
							commercial.setQuadrant(tfSearchCommercial.getText());
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial);
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						case "Zone":
							commercial.setZone(tfSearchCommercial.getText());
							searchResultCommercial = (ArrayList<CommercialProperty>) cpb.search(commercial);
							for(CommercialProperty cp : searchResultCommercial) {
								listModelCommercial.addElement(cp.getId()+ " " + cp.getAddress() + " " + cp.getType());
							}
							break;
						
					}
					
				}
			}
	}
	
	private class MyListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int index = lstSearchResult.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResult.get(index));
				
				tfIdClient.setText(searchResult.get(index).getClientID() + "");
				tfFirstNameClient.setText(searchResult.get(index).getAddress());
				tfLastNameClient.setText(searchResult.get(index).getLastName());
				tfTypeClient.setText(searchResult.get(index).getClientType() + "");
				tfPhoneNumberClient.setText(searchResult.get(index).getPhoneNumber());
				tfPostalCodeClient.setText(searchResult.get(index).getPostalCode());
			}
			
		}
		
	}
	
	
	private class MyListSelectionListenerResidential implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int index = lstSearchResultResidential.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResultResidential.get(index));
				tfIdResidential.setText(searchResultResidential.get(index).getId() + "");
				tfAddressResidential.setText(searchResultResidential.get(index).getAddress());
				tfAskingPriceResidential.setText(searchResultResidential.get(index).getAskingPrice() + "");
				tfQuadrantResidential.setText(searchResultResidential.get(index).getQuadrant());
				tfZoneResidential.setText(searchResultResidential.get(index).getZone());
				tfAreaResidential.setText(searchResultResidential.get(index).getArea()+ "");
				tfBedroomsResidential.setText(searchResultResidential.get(index).getBedrooms() + "");
				tfBathroomsResidential.setText(searchResultResidential.get(index).getBathrooms() + "");
				tfCommentsResidential.setText(searchResultResidential.get(index).getComments());
				tfLegalDescriptionResidential.setText(searchResultResidential.get(index).getLegalDescription());
				tfGarageTypeResidential.setText(searchResultResidential.get(index).getGarage() + "");
				
			}
			
		}
		
	}
	
	private class MyListSelectionListenerCommercial implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int index = lstSearchResultCommercial.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResultCommercial.get(index));
				tfIdCommercial.setText(searchResultCommercial.get(index).getId() + "");
				tfAddressCommercial.setText(searchResultCommercial.get(index).getAddress());
				tfAskingPriceCommercial.setText(searchResultCommercial.get(index).getAskingPrice() + "");
				tfQuadrantCommercial.setText(searchResultCommercial.get(index).getQuadrant());
				tfZoneCommercial.setText(searchResultCommercial.get(index).getZone());
				tfFloorsCommercial.setText(searchResultCommercial.get(index).getNoFloors() + "");
				tfTypeCommercial.setText(searchResultCommercial.get(index).getType());
				tfLegalDescriptionCommercial.setText(searchResultCommercial.get(index).getLegalDescription());
				tfCommentsCommercial.setText(searchResultCommercial.get(index).getComments());
				
			}
			
		}
		
	}
}
