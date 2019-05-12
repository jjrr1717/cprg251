package randomAccessFileExcercise6;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class RandomAccessFilePractice {
	
	private RandomAccessFile raf;
	private File inputFile;
	private Scanner scanInput;
	
	private final int RECORD_LENGTH = 52; //record length changed to 52 because added the logical delete boolean
	private final String INPUT_FILE = "res/customers.txt";
	private final String RANDOM_FILE = "res/customersRaf.txt"; //because it's in bytes you can save it in any type of file. 
	
	private ArrayList<Customer> customersInfo; //array list to store the data from randomaccessFile
	
	public RandomAccessFilePractice() throws Exception
	{
		customersInfo = new ArrayList<>();
		transferData(); //to RandomAccessFile
		loadData(); //to ArrayList
	}
	
	public void loadData() throws IOException 
	{
		RandomAccessFile raf = new RandomAccessFile(RANDOM_FILE, "r");//open file
		
		for(int l = 0; l< raf.length(); l+=RECORD_LENGTH) 
		{
			Customer c = readOneRecord(l);
			customersInfo.add(c);
		}
		
		raf.close();
		
	}
	
	public Customer readOneRecord(int index) throws IOException 
	{
		RandomAccessFile raf = new RandomAccessFile(RANDOM_FILE, "r");
		raf.seek(index);
		Customer c = new Customer(raf.readBoolean(), 
									raf.readInt(), 
									raf.readUTF(),
									raf.readUTF(), 
									raf.readLong());
		
		raf.close();
		return c;
		
	}
	
	public void deleteRecord(int id) throws Exception
	{
		
		for(Customer c: customersInfo) 
		{
			if(c.getId() == id) 
			{
				c.setActive(false);
			}
		}
	}
	
	public void saveRecords() throws Exception 
	{
		raf = new RandomAccessFile(RANDOM_FILE, "rw");
		
		for(Customer c: customersInfo) 
		{
			if(c.isActive() == true) 
			{
				raf.writeBoolean(c.isActive());
				raf.writeInt(c.getId());
				raf.writeUTF(c.getName());
				raf.writeUTF(c.getCity());
				raf.writeLong(c.getPhone());
			}
		}
		
		raf.close();
	}
	
	public void printCustomers() 
	{
		for(Customer c:customersInfo) 
		{
			System.out.println(c);
		}
	}
	
	public void transferData() throws Exception
	{
		inputFile = new File(INPUT_FILE);
		scanInput = new Scanner(inputFile);
		raf = new RandomAccessFile(RANDOM_FILE, "rw");
		
		while(scanInput.hasNextLine()) 
		{
			String [] splittedLine = scanInput.nextLine().split(" ");
			raf.writeBoolean(true);//every record will be active and true 
			raf.writeInt(Integer.parseInt(splittedLine[0]));
			raf.writeUTF(padString2(splittedLine[1], 15));
			raf.writeUTF(padString2(splittedLine[2], 20));
			raf.writeLong(Long.parseLong(splittedLine[3]));
	
			
			
		}
		
		scanInput.close();
		raf.close();
		System.out.println("Done");
		
		
	}
	
	public String readRecord(int index) throws Exception
	{
		
		RandomAccessFile rf = new RandomAccessFile(RANDOM_FILE, "r");
		int pointer = (index-1) * (RECORD_LENGTH);
		
		rf.seek(pointer);
		
		String record = rf.readBoolean() + " " +  rf.readInt() + " " + rf.readUTF().trim() + " " + rf.readUTF().trim() + " " + rf.readLong();
		
		rf.close();
		return record;
	}
	
	public String padString(String inputString, int length) 
	{
		
		int difInLength = length - inputString.length();
		
		String theString = String.format("%1$", difInLength, "inputString", inputString).replace(' ', ' ');
		
		return theString;
	}
	
	public String padString2(String inputString, int length) 
	{
		if(inputString.length() >= length) 
		{
			return inputString;
		}
		
		StringBuilder sb = new StringBuilder();
		while(sb.length() < length - inputString.length()) 
		{
			sb.append(' ');
		}
		
		sb.append(inputString);
		
		return sb.toString();
	}

}
