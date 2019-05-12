import java.io.*;

public class FileIOPractice
{
  public static void main (String [] args)
  {
    final String FILE_NAME = "NumberList.txt";
    PrintWriter numberFile = null; 
    //create and write to a file named NumberList.txt
    try
    {
      numberFile = new PrintWriter(FILE_NAME);
    }catch(Exception e)
    {
      System.out.print("This is a file io Exception");
    }
    for(int i = 1; i <=100; i++)
    {
      numberFile.println(i);
    }
    
    numberFile.close();
  }
}