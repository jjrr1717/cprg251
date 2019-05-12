import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MySimpleWindow extends JFrame
{
  
  private JTextField tfFirstName;
  private JTextField tfLastName;
  private JTextField tfInitials;
  private JButton btnCreate;
  private JLabel lblTitle;
  private JLabel lblFirstName;
  private JLabel lblLastName;
  private JLabel lblInitials;
  
  private Container contents;
  
  Border panelBorder = BorderFactory.createEtchedBorder();
  
  public MySimpleWindow()
  {
    super("My Dr.Java Window");
    this.setBounds(50,50,300,200);
    contents = this.getContentPane();
    JPanel mainPanel = new JPanel(new BorderLayout(10,10));
    createMenu();
    contents.add(mainPanel);
    contents.add(createTopPanel(), BorderLayout.NORTH);
    contents.add(createCenterPanel(), BorderLayout.CENTER);
    contents.add(createBottomPanel(), BorderLayout.SOUTH);
    
    
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  
  private JPanel createTopPanel()
  {
    JPanel topPanel = new JPanel();
    
    topPanel.setBorder(panelBorder);
    
    lblTitle = new JLabel("Initials Creator");
    lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 14));
    lblTitle.setForeground(Color.PINK);
    
    topPanel.add(lblTitle);
    return topPanel;
  }
  
  private JPanel createCenterPanel()
  {
    JPanel centerPanel = new JPanel();
    centerPanel.setBorder(panelBorder);
    
    lblFirstName = new JLabel("Enter First Name: ");
    tfFirstName = new JTextField(15);
    
    lblLastName = new JLabel("Enter Last Name: ");
    tfLastName = new JTextField(15);
    
    lblInitials = new JLabel("Your Initials: ");
    tfInitials = new JTextField(15);
    tfInitials.setEditable(false);
    
    centerPanel.add(lblFirstName);
    centerPanel.add(tfFirstName);
    centerPanel.add(lblLastName);
    centerPanel.add(tfLastName);
    centerPanel.add(lblInitials);
    centerPanel.add(tfInitials);
    return centerPanel;
  }
  
  private JPanel createBottomPanel()
  {
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBorder(panelBorder);
    
    btnCreate = new JButton("Create");
    btnCreate.addActionListener(createInitialsListen());
    
    bottomPanel.add(btnCreate);
    return bottomPanel;
  }
  
  private ActionListener createInitialsListen()
  {
    return new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        char fNameInitial = tfFirstName.getText().charAt(0);
        char lNameInitial = tfLastName.getText().charAt(0);
        
        tfInitials.setText(Character.toString(fNameInitial) + Character.toString(lNameInitial));
        
      }
    };
  }
  
  private void createMenu()
  {
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBounds(0,0,300,25);
    
    JMenu mnuFile = new JMenu("File");
    JMenu mnuAbout = new JMenu("About");
    
    JMenuItem mnuSave = new JMenuItem("Save");
    mnuFile.add(mnuSave);
    
    JMenuItem mnuiInfo = new JMenuItem("Info");
    mnuAbout.add(mnuiInfo);
    
    mnuiInfo.addActionListener(InfoListen());
    
    menuBar.add(mnuFile);
    menuBar.add(mnuAbout);
    
    contents.add(menuBar);
  }
  
  private ActionListener InfoListen()
  {
    return new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String msg = "This was created by Jocelyn Wegen!";
        JOptionPane.showMessageDialog(null, msg);
      }
    };
  }
}

