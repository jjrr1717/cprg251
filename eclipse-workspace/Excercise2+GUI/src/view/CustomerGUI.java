package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class CustomerGUI extends JFrame {
	
	private Container content;
	private Border panelBorder;
	
	private JLabel lblCustomer;
	private JList<String> customerList;
	
	
	
	
	
	public CustomerGUI () 
	{
		
		super("Customers");
		this.setBounds(100, 100, 600, 350);
		content = this.getContentPane();
		panelBorder = BorderFactory.createEtchedBorder();
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		content.add(mainPanel);
		content.add(createTopPanel(), BorderLayout.NORTH);
		content.add(createCenterPanel(), BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private JPanel createTopPanel() 
	{
		JPanel topPanel = new JPanel();
		panelBorder = BorderFactory.createEtchedBorder();
		topPanel.setBorder(panelBorder);
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalStrut(20));
		
		lblCustomer = new JLabel("Customers");
		hBox.add(lblCustomer);
		topPanel.add(hBox);
		return topPanel;
	}
	
	private JPanel createCenterPanel() 
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(panelBorder);
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalStrut(20));
		customerList = new JList<String>();
		customerList.setVisibleRowCount(10);
		customerList.setFixedCellWidth(500);
		JScrollPane customerScroll = new JScrollPane(customerList);
		hBox.add(customerScroll);
		
		centerPanel.add(hBox);
		return centerPanel;
	}
	
}	
		