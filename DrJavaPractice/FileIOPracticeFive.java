import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class FileIOPracticeFive
{
  public static void main (String [] args)
  {
    //variables
    final String INPUT_FILE = "ReadMe.txt";
    Scanner readFile = null;
    String word;
    boolean wordFound = false;
    
    //open file
    File inputFile = new File(INPUT_FILE);
    
    try
    {
      readFile = new Scanner(inputFile);
    }catch(FileNotFoundException e)
    {
      System.out.println("File not found!");
    }
    
    //get user input
    Scanner keyboard = new Scanner(System.in);
    word = JOptionPane.showInputDialog("Please enter a word to search:");
    
    try
    {
      readFile = new Scanner(inputFile);
    }catch(Exception e)
    {
      System.out.println("File not found!");
    }
    
    while(readFile.hasNext())
    {
      String [] splitted = readFile.next().split(" ");

      for(int i = 0; i < splitted.length; i++)
      {
        if(word.equalsIgnoreCase(splitted[i]))
        {
          wordFound = true;
        }
      }
    }
    
    if(wordFound)
    {
      JOptionPane.showMessageDialog(null, "Word Found!");
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Word Not Found!");
    }
  }
}