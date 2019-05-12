package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class OrderGUI extends JFrame {
	
	private Container contents;
	
	private Border panelBorder;
	
	private JLabel lblOrder;
	
	JList<String> orderList;
	
	public OrderGUI() 
	{
		super("Orders");
		
		this.setBounds(100, 100, 600, 350);
		panelBorder = BorderFactory.createEtchedBorder();
		
		contents = getContentPane();
		
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		
		contents.add(mainPanel);
		contents.add(createTopPanel(), BorderLayout.NORTH);
		contents.add(createCenterPanel(), BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private JPanel createTopPanel() 
	{
		JPanel topPanel = new JPanel();
		topPanel.setBorder(panelBorder);
		Box hBox = Box.createVerticalBox();
		lblOrder = new JLabel("Orders");
		
		hBox.add(lblOrder);
		
		hBox.add(Box.createVerticalStrut(10));
		
		topPanel.add(hBox);
		return topPanel;
	}
	
	private JPanel createCenterPanel() 
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(panelBorder);
		Box hBox = Box.createVerticalBox();
		hBox.add(Box.createVerticalStrut(20));
		orderList = new JList<String>();
		orderList.setFixedCellWidth(500);
		orderList.setVisibleRowCount(10);
		JScrollPane scrollOrderList = new JScrollPane(orderList);
		
		
		hBox.add(scrollOrderList);
		centerPanel.add(hBox);
		
		return centerPanel;
	}



}
