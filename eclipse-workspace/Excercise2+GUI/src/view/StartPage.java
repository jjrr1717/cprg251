package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class StartPage extends JFrame {
	
	Container content;
	Border panelBorder;
	
	JLabel lblWelcome;
	
	JLabel lblReports;
	JLabel lblAdd;
	JLabel lblStatistics;
	
	JButton btnCustomer;
	JButton btnProduct;
	JButton btnOrder;
	
	JRadioButton rbCustomer;
	JRadioButton rbProduct;
	JRadioButton rbOrder;
	JButton btnCreate;
	
	JList<String> statsList;
	
	
	
	
	
	public StartPage()
	{
		super("Store App");
		
		this.setBounds(100, 100, 600, 350);
		
		content = this.getContentPane();
		
		panelBorder = BorderFactory.createEtchedBorder();
		
		
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		
		content.add(mainPanel);
		
		mainPanel.add(createTopPanel(), BorderLayout.NORTH);
		mainPanel.add(createLeftPanel(), BorderLayout.WEST);
		mainPanel.add(createMiddlePanel(), BorderLayout.CENTER);
		mainPanel.add(createRightPanel(), BorderLayout.EAST);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private JPanel createTopPanel() 
	{
		JPanel topPanel = new JPanel();
		
		topPanel.setBorder(panelBorder);
		
		lblWelcome = new JLabel("Welcome to our Store");
		
		topPanel.add(lblWelcome);
		
		return topPanel;
		
		
	}
	
	private JPanel createLeftPanel() 
	{
		JPanel leftPanel = new JPanel(new BorderLayout(10,10));
		
		leftPanel.setBorder(panelBorder);
		
		Box hBox = Box.createVerticalBox();
		
		lblReports = new JLabel("Reports   ");
		hBox.add(lblReports);
		
		hBox.add(Box.createVerticalStrut(40));
		
		btnCustomer = new JButton("Customer");
		hBox.add(btnCustomer);
		
		hBox.add(Box.createVerticalStrut(40));
		
		btnProduct = new JButton("Product    ");
		hBox.add(btnProduct);
		
		hBox.add(Box.createVerticalStrut(40));
		
		btnOrder = new JButton("Order         ");
		hBox.add(btnOrder);

		
		leftPanel.add(hBox);
		
		
		return leftPanel;
	}
	
	private JPanel createMiddlePanel() 
	{
		JPanel middlePanel = new JPanel();
		
		middlePanel.setBorder(panelBorder);
		
		Box hBox = Box.createVerticalBox();
		
		lblAdd = new JLabel("Add");
		hBox.add(lblAdd);
		
		hBox.add(Box.createVerticalStrut(30));
		
		rbCustomer = new JRadioButton("Customer");
		hBox.add(rbCustomer);
		
		hBox.add(Box.createVerticalStrut(30));
		
		rbProduct = new JRadioButton("Product");
		hBox.add(rbProduct);
		
		hBox.add(Box.createVerticalStrut(30));
		
		rbOrder = new JRadioButton("Order");
		hBox.add(rbOrder);
		
		hBox.add(Box.createVerticalStrut(30));
		
		btnCreate = new JButton("Create");
		hBox.add(btnCreate);
		
		middlePanel.add(hBox);

		
		return middlePanel;
	}
	
	private JPanel createRightPanel() 
	{
		JPanel rightPanel = new JPanel(new BorderLayout(10,10));
		
		rightPanel.setBorder(panelBorder);
		
		Box hBox = Box.createVerticalBox();
		
		lblStatistics = new JLabel("Statistics");
		
		hBox.add(lblStatistics);
		
		hBox.add(Box.createVerticalStrut(10));
		
		statsList = new JList<String>();
		statsList.setFixedCellWidth(200);
		statsList.setVisibleRowCount(13);
		
		JScrollPane myScrollPane = new JScrollPane(statsList);

		rightPanel.add(hBox);
		
		hBox.add(myScrollPane);
		
		return rightPanel;
	}
	
	


}
