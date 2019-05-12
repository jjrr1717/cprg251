package goodMorningWindow;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;

public class GoodMorningWindow {
	
	//create Frame object
	private JFrame goodMorningWin;
	
	public GoodMorningWindow() 
	{
		createGoodMorningWin();
	}
	
	private void createGoodMorningWin() 
	{
		//create frame
		goodMorningWin = new JFrame("Good Morning");
		
		//create window size
		goodMorningWin.setBounds(200, 200, 400, 300);
		
		//create container
		Container content = goodMorningWin.getContentPane();
		
		//create Layout type
		FlowLayout flow = new FlowLayout();
		
		//put layout into container
		content.setLayout(flow);
		
		//create the enter button
		JButton btnEnter = new JButton("Enter");
		
		//add button to container
		content.add(btnEnter);
		
		//add a listener to the Enter Button
		btnEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		closeWindow();
		
	}
	
	public void makeVisible() 
	{
		goodMorningWin.setVisible(true);
	}
	
	public void closeWindow() 
	{
		goodMorningWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
