package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class ProductGUI extends JFrame {
	
	private Container contents;
	private Border panelBorder;
	private JLabel lblProducts;
	private JList<String> productList;
	private Vector<String> productVector;
	
	public ProductGUI() 
	{
		super("Products");
		this.setBounds(100, 100, 600, 350);
		
		contents = getContentPane();
		
		panelBorder = BorderFactory.createEtchedBorder();
		
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
		
		lblProducts = new JLabel("Products");
		hBox.add(lblProducts);
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
		productList = new JList<String>();
		productList.setVisibleRowCount(10);
		productList.setFixedCellWidth(500);
		
		JScrollPane productListScroll = new JScrollPane(productList);
		hBox.add(productListScroll);
		
		productVector = new Vector<String>();
		
		productVector.add("Test");
		productVector.add("TestTwo");
		
		productList.setListData(productVector);
		
		
		
		centerPanel.add(hBox);
		return centerPanel;
	}

}
