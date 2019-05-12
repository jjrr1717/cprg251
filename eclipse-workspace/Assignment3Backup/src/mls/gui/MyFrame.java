package mls.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.mls.exceptions.clientale.InvalidClientTypeException;
import sait.mls.exceptions.clientale.InvalidPhoneNumberException;
import sait.mls.exceptions.clientale.InvalidPostalCodeException;
import sait.mls.exceptions.property.InvalidLegalDescriptionException;
import sait.mls.exceptions.property.InvalidNumberOfBathroomsException;
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
	private ActionListener actionListenerAddButtons;
	private ActionListener actionListenerRemoveButtons;
	private ActionListener actionListenerClearButtons;
	private int index;
	
	
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
	private JButton btnClearSearchClient;
	
	//Client right center components
	private JLabel lblIdClient;
	private JTextField tfIdClient;
	private JLabel lblFirstNameClient;
	private JTextField tfFirstNameClient;
	private JLabel lblLastNameClient;
	private JTextField tfLastNameClient;
	private JLabel lblAddressClient;
	private JTextField tfAddressClient;
	private JLabel lblTypeClient;
	private JComboBox<String>cbTypeClient;
	private JLabel lblPhoneNumberClient;
	private JTextField tfPhoneNumberClient;
	private JLabel lblPostalCodeClient;
	private JTextField tfPostalCodeClient;
	private JButton btnAddClient;
	private JButton btnRemoveClient;
	private JButton btnClearInfoClient;

	
	//Residential top left center Components
	private JLabel lblSearchByResidential;
	private JComboBox<String>cbSearchByResidential;
	private JTextField tfSearchResidential;
	private JButton btnSearchResidential;
	private JButton btnClearSearchResidential;
	
	//Residential right center Components
	private JLabel lblIdResidential;
	private JTextField tfIdResidential;
	private JLabel lblAddressResidential;
	private JTextField tfAddressResidential;
	private JLabel lblAskingPriceResidential;
	private JTextField tfAskingPriceResidential;
	private JLabel lblQuadrantResidential;
	private JComboBox<String> cbQuadrantResidential;
	private JLabel lblZoneResidential;
	private JComboBox<String> cbZoneResidential;
	private JLabel lblAreaResidential;
	private JTextField tfAreaResidential;
	private JLabel lblBedroomsResidential;
	private JTextField tfBedroomsResidential;
	private JLabel lblBathroomsResidential;
	private JTextField tfBathroomsResidential;
	private JLabel lblGarageTypeResidential;
	private JComboBox<String> cbGarageTypeResidential;
	private JLabel lblLegalDescriptionResidential;
	private JTextField tfLegalDescriptionResidential;
	private JLabel lblCommentsResidential;
	private JTextField tfCommentsResidential;
	private JButton btnAddResidential;
	private JButton btnRemoveResidential;
	private JButton btnClearInfoResidential;

	
	//Commercial top left center Components
	private JLabel lblSearchByCommercial;
	private JComboBox<String>cbSearchByCommercial;
	private JTextField tfSearchCommercial;
	private JButton btnSearchCommercial;
	private JButton btnClearSearchCommercial;
	
	//Commercial right center Components
	private JLabel lblIdCommercial;
	private JTextField tfIdCommercial;
	private JLabel lblAddressCommercial;
	private JTextField tfAddressCommercial;
	private JLabel lblAskingPriceCommercial;
	private JTextField tfAskingPriceCommercial;
	private JLabel lblQuadrantCommercial;
	private JComboBox<String> cbQuadrantCommercial;
	private JLabel lblZoneCommercial;
	private JComboBox<String> cbZoneCommercial;
	private JLabel lblFloorsCommercial;
	private JTextField tfFloorsCommercial;
	private JLabel lblTypeCommercial;
	private JComboBox<String> cbTypeCommercial;
	private JLabel lblLegalDescriptionCommercial;
	private JTextField tfLegalDescriptionCommercial;
	private JLabel lblCommentsCommercial;
	private JTextField tfCommentsCommercial;
	private JButton btnAddCommercial;
	private JButton btnRemoveCommercial;
	private JButton btnClearInfoCommercial;

	
	

	public MyFrame() {
		initialize();
	}

	private void initialize() {
		cb = ClientBroker.getBroker();
		rpb = ResidentialPropertyBroker.getBroker();
		cpb = CommercialPropertyBroker.getBroker();
		frame = new JFrame("SAIT MLS");
		frame.setBounds(10, 10, 500, 550);
		actionListener = new MyActionListener();
		actionListener2 = new MyActionListener2();
		actionListenerResidential = new MyActionListenerResidential();
		actionListenerCommercial = new MyActionListenerCommercial();
		actionListenerAddButtons = new MyActionListenerAddButtons();
		actionListenerRemoveButtons = new MyActionListenerRemoveButtons();
		actionListenerClearButtons = new MyActionListenerClearButtons();
		panelList = new ArrayList<JPanel>();
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		panelList.add(getClientPanel());
		panelList.add(getResidentialPanel());
		panelList.add(getCommercialPanel());
		
		frame.add(panelList.get(0), BorderLayout.CENTER);
		/*frame.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				cb.closeBroker();
				System.out.print("Test");
				System.exit(0);
			}
		});*/

		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(closeApp());
		frame.setVisible(true);
		
	}
	
	public WindowAdapter closeApp() 
	{
		return new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				cb.closeBroker();
				rpb.closeBroker();
				cpb.closeBroker();
				System.exit(0);
			}
		};
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
		tfIdClient.setText("0");
		tfIdClient.setEditable(false);
		
		lblFirstNameClient = new JLabel("First Name:");
		tfFirstNameClient = new JTextField();
		
		lblLastNameClient = new JLabel("Last Name:");
		tfLastNameClient = new JTextField();
		
		lblAddressClient = new JLabel("Address:");
		tfAddressClient = new JTextField();
		
		lblTypeClient = new JLabel("Client Type:");
		cbTypeClient = new JComboBox<String>();
		cbTypeClient.addItem("R");
		cbTypeClient.addItem("C");
		cbTypeClient.addItem("B");
		
		lblPhoneNumberClient = new JLabel("Phone Number:");
		tfPhoneNumberClient = new JTextField();
		
		lblPostalCodeClient = new JLabel("Postal Code");
		tfPostalCodeClient = new JTextField();
		
		btnAddClient = new JButton("Save");
		btnAddClient.addActionListener(actionListenerAddButtons); 
		btnRemoveClient = new JButton("Delete");
		btnRemoveClient.addActionListener(actionListenerRemoveButtons);
		btnClearInfoClient = new JButton("Clear");
		btnClearInfoClient.addActionListener(actionListenerClearButtons);

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
	
	private JPanel getRightCenterPanelResidential() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblIdResidential = new JLabel("Id:");
		tfIdResidential = new JTextField();
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
		cbGarageTypeResidential.addItem("a");
		cbGarageTypeResidential.addItem("d");
		cbGarageTypeResidential.addItem("n");
		
		
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

	private JPanel getRightCenterPanelCommercial() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.setBackground(Color.GRAY);
		
		lblIdCommercial = new JLabel("Id:");
		tfIdCommercial = new JTextField();
		tfIdCommercial.setText("0");
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
		
		btnClearSearchClient = new JButton("Clear");
		btnClearSearchClient.addActionListener(actionListenerClearButtons);
		
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
		panel.add(btnClearSearchClient);
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
		btnClearSearchResidential = new JButton("Clear");
		btnClearSearchResidential.addActionListener(actionListenerClearButtons);
		
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
		panel.add(btnClearSearchResidential);
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
		btnClearSearchCommercial = new JButton("Clear");
		btnClearSearchCommercial.addActionListener(actionListenerClearButtons);
		
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
		panel.add(btnClearSearchCommercial);
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
			index = lstSearchResult.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResult.get(index));
				
				tfIdClient.setText(searchResult.get(index).getClientID() + "");
				tfFirstNameClient.setText(searchResult.get(index).getFirstName());
				tfLastNameClient.setText(searchResult.get(index).getLastName());
				tfAddressClient.setText(searchResult.get(index).getAddress());
				cbTypeClient.setSelectedItem(searchResult.get(index).getClientType() + "");
				tfPhoneNumberClient.setText(searchResult.get(index).getPhoneNumber());
				tfPostalCodeClient.setText(searchResult.get(index).getPostalCode());
			}
			
		}
		
	}
	
	
	private class MyListSelectionListenerResidential implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			index = lstSearchResultResidential.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResultResidential.get(index));
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
	
	private class MyListSelectionListenerCommercial implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			index = lstSearchResultCommercial.getSelectedIndex();
			if(index >= 0) {
				//System.out.println(searchResultCommercial.get(index));
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
	
	private class MyActionListenerAddButtons implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			boolean validLengthFirstname = true;
			boolean validLengthLastname = true;
			boolean validLengthAddress = true;
			
			if(e.getSource() == btnAddClient) 
			{
				
				Client newClient = null;
				
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

				
				try {
					if(validLengthFirstname && validLengthLastname && validLengthAddress) 
					{
					
						newClient = new Client(newId,newFirstName, newLastName, newAddress, newPostalCode, newPhoneNumber, newClientType);
						cb.persist(newClient);
						
						tfIdClient.setText(newClient.getClientID() + "");
						
						JOptionPane.showMessageDialog(null, "Item Saved!");
						clearInformationClient();
						
					}
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
					
						
					
				} catch (InvalidPhoneNumberException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invlaid format for Phone Number!");
				} catch(InvalidPostalCodeException e2) 
				{
					JOptionPane.showMessageDialog(null, "Invlaid format for PostalCode!");
				}
				
				
			}
			
			else if(e.getSource() == btnAddResidential) 
			{
				boolean validAddressLength = true;
				ResidentialProperty newResidential = null;
				
				int newId = Integer.parseInt(tfIdResidential.getText());
				String newAddress = tfAddressResidential.getText();
				validAddressLength = maxTextFieldLength(80, newAddress);
				double newAskingPrice = Double.parseDouble(tfAskingPriceResidential.getText());
				String newQuadrant = (String) cbQuadrantResidential.getSelectedItem();
				String newZone = (String) cbZoneResidential.getSelectedItem();
				double newArea = Double.parseDouble(tfAreaResidential.getText());
				int newBedrooms = Integer.parseInt(tfBedroomsResidential.getText());
				double newBathrooms = Double.parseDouble(tfBathroomsResidential.getText());
				char newGarageType = ((String) cbGarageTypeResidential.getSelectedItem()).charAt(0);
				String newLegalDescription = tfLegalDescriptionResidential.getText();
				String newComments = tfCommentsResidential.getText();
				
				try {
					
					if(validAddressLength) 
					{
						newResidential = new ResidentialProperty(newId, newLegalDescription, newAddress, newQuadrant, newZone, 
							newAskingPrice, newComments, newArea, newBathrooms, newBedrooms, newGarageType);
						rpb.persist(newResidential);
						
						tfIdResidential.setText(newResidential.getId() + "");
						
						JOptionPane.showMessageDialog(null, "Item Saved!");
						clearInformationResidential();
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Over Character Limit in Address!");
					}
				} catch (InvalidLegalDescriptionException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invlaid format for Legal Description!");
				}catch(InvalidNumberOfBathroomsException e2) {
					
					JOptionPane.showMessageDialog(null, "Invlaid format for NumberOfBathrooms!");
				
				}
				

				
				
			}
			
			
			else if(e.getSource() == btnAddCommercial) 
			{
				boolean validAddressLength = true;
				CommercialProperty newCommercial = null;
				
				long newId = Long.parseLong(tfIdCommercial.getText());
				String newAddress = tfAddressCommercial.getText();
				validAddressLength = maxTextFieldLength(80, newAddress);
				double newAskingPrice = Double.parseDouble(tfAskingPriceCommercial.getText());
				String newQuadrant = (String) cbQuadrantCommercial.getSelectedItem();
				String newZone = (String) cbZoneCommercial.getSelectedItem();
				int newFloors = Integer.parseInt(tfFloorsCommercial.getText());
				String newType = cbTypeCommercial.getSelectedItem().toString();
				String newLegalDescription = tfLegalDescriptionCommercial.getText();
				String newComments = tfCommentsCommercial.getText();
				
				try {
					if(validAddressLength) 
					{
						newCommercial = new CommercialProperty(newId,  newLegalDescription, newAddress, newQuadrant, newZone, 
							newAskingPrice, newComments, newType, newFloors);
						cpb.persist(newCommercial);
						tfIdCommercial.setText(newCommercial.getId() + "");
						
						JOptionPane.showMessageDialog(null, "Item Saved!");
						clearInformationCommercial();
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Over Character Limit in Address!");
					}
				} catch (InvalidLegalDescriptionException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invlaid format for Legal Description!");
				}
				
				cpb.persist(newCommercial);
				tfIdCommercial.setText(newCommercial.getId() + "");
				
				JOptionPane.showMessageDialog(null, "Item Saved!");
				clearInformationCommercial();
			}
			
		}

	}
	
	private class MyActionListenerRemoveButtons implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btnRemoveClient) 
			{

				
				cb.remove(searchResult.get(index));
				JOptionPane.showMessageDialog(null, "Item Removed");
				clearInformationClient();
				clearSearchClient();

			
			}
			else if(e.getSource() == btnRemoveResidential) 
			{
				rpb.remove(searchResultResidential.get(index));
				JOptionPane.showMessageDialog(null, "Item Removed");
				clearInformationResidential();
				clearSearchResidential();
			}
			else if(e.getSource() == btnRemoveCommercial) 
			{
				cpb.remove(searchResultCommercial.get(index));
				JOptionPane.showMessageDialog(null, "Item Removed");
				clearInformationCommercial();
				clearSearchCommercial();
			}
			
		}
		
			
	}
	
	public class MyActionListenerClearButtons implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnClearSearchClient) 
			{
				clearSearchClient();
			}
			else if(e.getSource() == btnClearInfoClient) 
			{
				clearInformationClient();
			}
			else if(e.getSource() == btnClearSearchResidential) 
			{
				clearSearchResidential();
			}
			else if(e.getSource() == btnClearInfoResidential) 
			{
				clearInformationResidential();
			}
			else if(e.getSource() == btnClearSearchCommercial) 
			{
				clearSearchCommercial();
			}
			else if(e.getSource() == btnClearInfoCommercial) 
			{
				clearInformationCommercial();
			}
			
		}
		
	}
	
	
	private void clearSearchClient() 
	{
		tfSearchClient.setText("");
		listModel.clear();	

	}
	
	private void clearInformationClient()
	{
		tfIdClient.setText("");
		tfFirstNameClient.setText("");
		tfLastNameClient.setText("");
		tfAddressClient.setText("");
		tfPhoneNumberClient.setText("");
		tfPostalCodeClient.setText("");
	}
	
	private void clearSearchResidential() 
	{
		tfSearchResidential.setText("");
		listModelResidential.clear();

	}
	
	private void clearInformationResidential()
	{
		tfIdResidential.setText("");
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
	
	private void clearSearchCommercial() 
	{
		tfSearchCommercial.setText("");
		listModelCommercial.clear();

	}
	
	private void clearInformationCommercial()
	{
		tfIdCommercial.setText("");
		tfAddressCommercial.setText("");
		tfAskingPriceCommercial.setText("");
		cbQuadrantCommercial.setSelectedItem("");
		cbZoneCommercial.setSelectedItem("");
		tfFloorsCommercial.setText("");
		tfLegalDescriptionCommercial.setText("");
		tfCommentsCommercial.setText("");
	}
	
	private boolean maxTextFieldLength(int maxLength, String field)
	{
		boolean isValidLength = true;
		
		
		if(field.length() > maxLength) 
		{
			isValidLength = false;
		}
		
		return isValidLength;
	} 
	
		
	
	
}
