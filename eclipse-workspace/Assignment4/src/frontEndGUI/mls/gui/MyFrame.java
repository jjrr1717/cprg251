/**
 * MyFrame is a class that creates the Frame for the
 * GUI. It also contains methods to control which
 * panels are visible. It contains a method to
 * close the app.
 * @author Jocelyn Wegen
 * @version February 17, 2019
 */

package frontEndGUI.mls.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;


import saitMLS.persistance.ClientBroker;
import saitMLS.persistence.property.CommercialPropertyBroker;
import saitMLS.persistence.property.ResidentialPropertyBroker;



public class MyFrame {


	/**
	 * actionListener is an ActionListener
	 * that determines which button is clicked
	 * to display either the Client, 
	 * Residential, or Commercial panel
	 */
	private ActionListener actionListener;
	/**
	 * btnClient is a JButton to 
	 * get to the client panel
	 */
	private JButton btnClient;
	/**
	 * btnResidential is a JButton to
	 * get to the Residential panel. 
	 */
	private JButton btnResidential;
	/**
	 * btnCommerical is a JButton
	 * to get to the Commercial 
	 * button. 
	 */
	private JButton btnCommercial;
	
	/**
	 * fram is a JFrame for the whole GUI
	 */
	private JFrame frame;
	/**
	 * panelList is an ArrayList of JPanels, 
	 * to hold the Client, Residential, 
	 * and Commercial Panels.  
	 */
	private ArrayList<JPanel> panelList;
	
	/**
	 * guiClient is an object
	 * of the GUIClient class to get 
	 * access to the contents of 
	 * the Client panel. 
	 */
	private GUIClient guiClient;
	/**
	 * guiCResidential is an object
	 * of the GUIResidential class to get 
	 * access to the contents of 
	 * the Residential panel. 
	 */
	private GUIResidential guiResidential;
	/**
	 * guiCommerical is an object
	 * of the GUICommerical class to get 
	 * access to the contents of 
	 * the Commerical panel. 
	 */
	private GUICommercial guiCommercial;
	
	/**
	 * cb is an object of the ClientBroker
	 * to have access to the methods in 
	 * the ClientBroker. 
	 */
	private ClientBroker cb;
	/**
	 * rpb is an object of the 
	 * ResidentialPropertyBroker to have 
	 * access to the methods in that 
	 * class
	 */
	private ResidentialPropertyBroker rpb;
	/**
	 * cpb is an object of the 
	 * CommercialPropertyBroker to have 
	 * access to the methods in that 
	 * class
	 */
	private CommercialPropertyBroker cpb;
	

	/**
	 * Constructor.  Has the initialize() method
	 * to display the GUI
	 */
	public MyFrame() {
		initialize();
	}

	/**
	 * initialize is a method to set up 
	 * the JFrame for the GUI.  Setup the 
	 * bounds of the JFrame.  Add the
	 * Client, Residential, and 
	 * Commerical panels. 
	 */
	private void initialize() {
		
		//need broker to close broker and save all the changes
		cb = ClientBroker.getClientBroker();
		rpb = ResidentialPropertyBroker.getBroker();
		cpb = CommercialPropertyBroker.getBroker();
		
		//create frame
		frame = new JFrame("SAIT MLS");
		frame.setBounds(10, 10, 700, 650);
		
		//actionListener for the top buttons to switch through main panels
		actionListener = new MyActionListener();
		
		//GUIClient, GUIResidential, GUICommercial for access to each panel
		guiClient = new GUIClient();
		guiResidential = new GUIResidential();
		guiCommercial = new GUICommercial();

		//add Client, Residential, and Commercial panels to panelList
		panelList = new ArrayList<JPanel>();
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		panelList.add(guiClient.getClientPanel());
		panelList.add(guiResidential.getResidentialPanel());
		panelList.add(guiCommercial.getCommercialPanel());
		frame.add(panelList.get(0), BorderLayout.CENTER);
		
		//WindowListener to close app
		frame.addWindowListener(closeApp());
		//make frame visible
		frame.setVisible(true);
		
	}
	

	
	/**
	 * getNorthPanel contains the JPanel and components of
	 * the Client, Residential, and Commerical buttons
	 * at the top of the GUI. 
	 * @return a JPanel of the North/Top panel
	 */
	private JPanel getNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(50, 30));
		Border buttonEdge = BorderFactory.createEtchedBorder();
		
		//create components
		btnClient = new JButton("Client");
		btnClient.setPreferredSize(new Dimension(200,25));
		btnClient.setBorder(buttonEdge);
		btnClient.addActionListener(actionListener);
		
		btnResidential = new JButton("Residential");
		btnResidential.setPreferredSize(new Dimension(200,25));
		btnResidential.setBorder(buttonEdge);
		btnResidential.addActionListener(actionListener);
		
		btnCommercial = new JButton("Commercial");
		btnCommercial.setPreferredSize(new Dimension(200,25));
		btnCommercial.setBorder(buttonEdge);
		btnCommercial.addActionListener(actionListener);
		
		//add components to panel
		panel.add(btnClient);
		panel.add(btnResidential);
		panel.add(btnCommercial);
		
		return panel;
	}

	/*********************************/
	
	/**
	 * MyActionListener listens for if the Client, 
	 * Residential, or Commerical buttons are 
	 * clicked.  It will change to the panel of whatever
	 * button was clicked. 
	 * @author 783661
	 *
	 */
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JPanel tp;
			
			/*
			 * set all the panels to setVisible(false) so only the
			 * panel chosen will be visible. 
			 */
			for(int i = 0; i < panelList.size(); i++)
			{
				tp = panelList.get(i);
				frame.remove(tp);
				tp.setVisible(false);
			}
			
			//set client panel visible if btnClient clicked
			if(e.getSource() == btnClient)
			{
				tp = panelList.get(0);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			//set residential panel visible if btnResidential clicked
			else if(e.getSource() == btnResidential)
			{
				tp = panelList.get(1);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			//set commercial panel visible if btnCommerical clicked. 
			else if(e.getSource() == btnCommercial)
			{
				tp = panelList.get(2);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			
		}

	}
	
	/**
	 * closeApp closes the window and also save changed data. 
	 * @return a WindowAdapter that contains a windowClosing method
	 */
	private WindowAdapter closeApp() 
	{
		return new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				//close brokers to save all changed data
				cb.closeBroker();
				rpb.closeBroker();
				cpb.closeBroker();
				System.exit(0);
			}
		};
	}
	
}
