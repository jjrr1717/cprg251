package myPack;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SimpleWindow {
	
	//Making a frame object
	private JFrame testWindow;
	private JTextField tf1;
	
	
	public SimpleWindow() 
	{
		
		// Create the Window! Inside the JFrame parenthesis is the title of the frame
		testWindow = new JFrame("My Window");
		
		//Close window(and close the running of the window
		testWindow.addWindowListener(closeApp());
		
		//create window dimensions
		testWindow.setBounds(200, 200, 400, 300);
		
		//Add a container - used the window for .getContentPane
		Container content = testWindow.getContentPane();
		
		//making it a flow layout - there are other options see slid 20 mod 4
		FlowLayout flow = new FlowLayout();
		
		//add the layout to container, which we named content
		content.setLayout(flow);
		
		//add some buttons to window
		JButton btn1 = new JButton("Print");
		
		//Add button to container
		content.add(btn1);
		
		//add a text field to write to window (Constructor accepts a value for size)
		tf1 = new JTextField(20);
		
		//add text field to container
		content.add(tf1);
		
		//create a label
		JLabel jl1 = new JLabel("Enter the first value");
		//add label to container
		content.add(jl1);
		
		//make text field size bigger so we can see it (this allows us to set height and width
		//tf1.setPreferredSize(new Dimension(50,50));
		
		
		//add listener to button note: can use ctrl + space to see options available
		btn1.addActionListener(btn1Action());
		
		//close window (and close the running of the window)Another way to close
		//testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	//Sometimes other classes may need this so make it public
	public void makeVisible() 
	{
		//make window visible
		testWindow.setVisible(true);
	}
	
	public ActionListener btn1Action() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//action to be performed when btn1 is clicked
				tf1.setText("Print");
				
			}
		};
	}
	
	public WindowAdapter closeApp() 
	{
		return new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		};
	}
}
	



