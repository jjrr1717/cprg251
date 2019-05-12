package excercise2;

import view.CustomerGUI;
import view.OrderGUI;
import view.ProductGUI;
import view.StartPage;

public class AppDriver {
	public static void main (String [] args) throws Exception
	{
	
		//StartPage sp = new StartPage();
		//CustomerGUI cg = new CustomerGUI();
		//cg.makeCustomerVisible();
		
		new OrderGUI();
		new ProductGUI();
		new CustomerGUI();
	
	}

}
