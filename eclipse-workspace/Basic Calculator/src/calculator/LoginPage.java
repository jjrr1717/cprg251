package calculator;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage {
	
	private JFrame theLoginPage;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	
	private JButton btnLogin;
	
	Container contents;
	
	private String validUsername = "cprg";
	private String validPassword = "251";
	
	public LoginPage() 
	{
		theLoginPage = new JFrame("Login");
		
		theLoginPage.setBounds(100, 100, 450, 250);
		
		//create container
		contents = theLoginPage.getContentPane();
		
		//set up the layout
		contents.setLayout(null);
		
		//set up the closing application
		closeLoginPage();
		
		//create large label
		JLabel lblBigLabel = new JLabel("Login Page");
		lblBigLabel.setBounds(160, 10, 200, 30);
		lblBigLabel.setFont(new Font("Arial", Font.BOLD, 24));
		contents.add(lblBigLabel);
		
		//create username label
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(100, 50, 100, 30);
		contents.add(lblUsername);
		
		//create password label
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(100, 90, 100, 30);
		contents.add(lblPassword);
		
		//create username textField
		tfUsername = new JTextField();
		tfUsername.setBounds(170, 50, 100, 30);
		contents.add(tfUsername);
		
		//create password textField
		tfPassword = new JPasswordField();
		tfPassword.setBounds(170, 90, 100, 30);
		contents.add(tfPassword);
		
		//create login button
		btnLogin = new JButton("Login");
		btnLogin.setBounds(165, 130, 100, 30);
		btnLogin.addActionListener(validatePassword());
		contents.add(btnLogin);
	}
	
	private void closeLoginPage () 
	{
		theLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private ActionListener validatePassword() 
	{
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String passwordString = new String(tfPassword.getText());
				if(tfUsername.getText().equals(validUsername) && passwordString.equals(validPassword))
					{
						BasicCalculator bc = new BasicCalculator();
						bc.makeVisible();
					}
				else 
				{
					JOptionPane.showMessageDialog(theLoginPage, "Incorrect Username or Password! Please try again!", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		};
	}
	
	public void makeLoginVisible() 
	{
		theLoginPage.setVisible(true);
	}

}
