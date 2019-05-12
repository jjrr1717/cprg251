import java.io.*;
import java.util.Scanner;

public class FileIOPracticeThree
{
  public static void main (String [] args)
  {
    //variables
    final String INPUT_FILE_NAME = "NumberList.txt";
    Scanner readInput = null;
    int counter = 0;
    
    //open file 
    File inputFile = new File(INPUT_FILE_NAME);
    
    try
    {
      readInput = new Scanner(inputFile);
    }catch(FileNotFoundException e)
    {
      System.out.println("File not found!");
    }
    
    while(readInput.hasNext())
    {
      int number = readInput.nextInt();
      counter += number;
    }
    
    System.out.println(counter);
      readInput.close();
  }
  

}