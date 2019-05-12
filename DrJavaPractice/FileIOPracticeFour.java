import java.io.*;
import java.util.Scanner;

public class FileIOPracticeFour
{
  public static void main(String [] args)
  {
    final String INPUT_FILE = "NumberList.txt";
    FileWriter inputFile = null;
    
    //open file to append to it
    try
    {
      inputFile = new FileWriter(INPUT_FILE, true);
    }catch(IOException e)
    {
      System.out.println("IOException happened");
    }
    PrintWriter writeFile = new PrintWriter(inputFile);
    
    for(int i = 101; i<=200; i++)
    {
      writeFile.println(i);
    }
    
    writeFile.close();
    
  }
}