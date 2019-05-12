import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.mls.persistence.clientale.ClientBroker;
import sait.mls.problemdomain.clientale.Client;


public class MyFrame {


	private JButton btnClient;
	private JButton btnResidential;
	private JButton btnCommercial;
	private ArrayList<JPanel> panelList;
	private ActionListener actionListener;
	private ActionListener actionListener2;
	
	private JFrame frame;
	private JList <String>lstSearchResult;
	private DefaultListModel<String> listModel;
	private ClientBroker cb;
	private JButton btnSearchClient;
	private ArrayList<Client> searchResult;

	public MyFrame() {
		initialize();
	}

	private void initialize() {
		cb = ClientBroker.getBroker();
		frame = new JFrame();
		frame.setBounds(10, 10, 500, 500);
		actionListener = new MyActionListener();
		actionListener2 = new MyActionListener2();
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
		return panel;
	}
	
	private JPanel getCommercialPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lblTest = new JLabel("Commercial Panel");
		panel.add(lblTest);
		return panel;
	}
	
	private JPanel getCenterCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(getLeftCenterPanel());
		panel.add(getRightCenterPanel());
		return panel;
	}

	private JPanel getRightCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.DARK_GRAY);
		return panel;
	}

	private JPanel getLeftCenterPanel() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(getTopLeftCenterPanel());
		panel.add(getBottomLeftCenterPanel());
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

	private JPanel getTopLeftCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.CYAN);
		btnSearchClient = new JButton("Search");
		btnSearchClient.addActionListener(actionListener2);
		panel.add(btnSearchClient);
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
	
	private class MyActionListener2 implements ActionListener{

		

		@Override
		public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == btnSearchClient) {
					Client client = new Client();
					client.setLastName("Duck");
					searchResult = (ArrayList<Client>) cb.search(client);
					
					for(Client c : searchResult) {
						//System.out.println(c);
						listModel.addElement(c.getClientID()+ " " + c.getFirstName() + " " + c.getLastName() + 
								" " + c.getClientType());
					}
					
				}
			}
			
	}
	
	private class MyListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int index = lstSearchResult.getSelectedIndex();
			if(index >= 0) {
				System.out.println(searchResult.get(index));
			}
			
		}
		
	}
}
