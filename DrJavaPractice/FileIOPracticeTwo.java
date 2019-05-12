import java.io.*;
import java.util.Scanner;

public class FileIOPracticeTwo
{
  public static void main(String [] args)
  {
    //variables
    final String INPUT_FILE_NAME = "NumberList.txt";
    Scanner readFile = null;
    
    
    //open file and print out the contents
    File inputFile = new File(INPUT_FILE_NAME);
    
    try
    {
      readFile = new Scanner(inputFile);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File not found!");
    }
    
    while(readFile.hasNextLine())
    {
      String [] splitted = readFile.nextLine().split("\r\n");
      
      int number = Integer.parseInt(splitted[0]);
      
      System.out.println(number);
    }
    
    readFile.close();
  }
  
}