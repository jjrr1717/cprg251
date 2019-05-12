import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Manager {
	public static void main (String [] args) 
	{
		final String INPUT_FILE = "res/book.txt";
		final String OUTPUT_FILE = "res/newFile.txt";
		String userWord;
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter word to search: ");
		userWord = keyboard.next();
		boolean wordFound = false;
		

			File openfile = new File(INPUT_FILE);
			
			try 
			{
				Scanner scanFile = new Scanner(openfile);
				
		
				while(scanFile.hasNextLine()) 
				{
					String [] splitted = scanFile.nextLine().split(" |,|.");
					
					for(int i = 0; i< splitted.length; i++) 
					{
						
						if(splitted[i].equalsIgnoreCase("userWord")) 
						{
							wordFound = true;
						}		
					}
				}
				
				if(wordFound == true) 
				{
					try
					{
						PrintWriter newFile = new PrintWriter(OUTPUT_FILE);
						newFile.print("Word is Found");
					}
					catch(Exception e) 
					{
						System.out.println("Not sure what exception will be encountered");
					}
				}

				else 
				{
					
					System.out.println("Word not found in File!");
				}
				
				scanFile.close();
			}
			catch(Exception e)
			{
				System.out.println("There is a file I/O error!");
			}
			

			
		
		
		
		
		
		
	}

}
