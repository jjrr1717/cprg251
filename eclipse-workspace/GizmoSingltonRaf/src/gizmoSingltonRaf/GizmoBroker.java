package gizmoSingltonRaf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fileHandling.FileInput;
import formatColumns.Column;

//use a Singleton (only want one GizmoBroker!! Hi Jocelyn (: )
public class GizmoBroker implements Broker {
	
	//One: make static object.  Only one instance so data will be shared. 
	private static GizmoBroker broker;
	
	private static final String BINARY_FILE = "res/gizmos.bin"; //the RandomAccessFile we will create
	private static final String TEXT_FILE = "res/gizmos.txt";
	private static final String MODE = "rw";
	
	private RandomAccessFile raf;
	
	//Two: make private constructor
	private GizmoBroker()
	{
		try {
			raf = new RandomAccessFile(BINARY_FILE, MODE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public boolean persist(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List search(String searchItem, String type) {
		ArrayList<Gizmo> searchResult = new ArrayList<Gizmo>();
		
		switch(type) 
		{
		case "id":
			searchResult = searchId(searchItem);
			break;
		case "description":
			searchResult = searchDescription(searchItem);
			break;
		case "quantity":
			searchResult = searchQuantity(searchItem);
			break;
		case "price":
			searchResult = searchPrice(searchItem);
			break;
		default:
			System.out.println("Invalid Input!");
			break;
		}
		
		return searchResult;
	}
	
	
	private ArrayList<Gizmo> searchId(String searchItem)
	{
		ArrayList<Gizmo> searchResult = new ArrayList<>();
		int id = Integer.parseInt(searchItem);
		boolean found = false;
		
		try 
		{
			raf.seek(0);
			for(long i = 0; i <raf.length() && !found; i += Gizmo.SIZE) 
			{
				Gizmo g = readRecord();
				if(g.getId() == id && g.isActive()) 
				{
					searchResult.add(g);
					found = true;
				}
			}
		}catch (Exception e) 
		{
			
		}
		
		return searchResult;
	}
	
	private ArrayList<Gizmo> searchDescription(String searchItem)
	{
		ArrayList<Gizmo> searchResult = new ArrayList<>();

			

		try {
			raf.seek(0);
			for(long i = 0; i<raf.length(); i +=Gizmo.SIZE) 
			{
				Gizmo g = readRecord();
				if(searchItem.equalsIgnoreCase(g.getDescription()) && g.isActive())
				{
					searchResult.add(g);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResult;
	}
	
	private ArrayList<Gizmo> searchPrice(String searchItem)
	{
		ArrayList<Gizmo>searchResult = new ArrayList<>();
		double price = Double.parseDouble(searchItem);
			
		
		try {
			raf.seek(0);
			for(long i = 0; i < raf.length(); i += Gizmo.SIZE) 
			{
				Gizmo g = readRecord();
				
				if(g.getPrice() == price && g.isActive()) 
				{
					searchResult.add(g);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		return searchResult;
	}
	
	private ArrayList<Gizmo> searchQuantity(String searchItem)
	{
		ArrayList<Gizmo> searchResult = new ArrayList<>();
		int quantity = Integer.parseInt(searchItem);
		
		
		try {
			raf.seek(0);
			for(long i = 0; i < raf.length(); i+=Gizmo.SIZE) 
			{
				Gizmo g = readRecord();
				if(quantity == g.getQuantity() && g.isActive())
				{
					searchResult.add(g);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResult;
	}
	
	public Gizmo findRecord(int index) 
	{
		long offset = (index-1) * Gizmo.SIZE;
		Gizmo g = null;
		
		try {
			raf.seek(offset);
			g = readRecord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return g;
	}
	
	public Gizmo lastRecord() 
	{
		Gizmo g = null;
		
		return g;
	}
	
	/* This was my attempt - more efficient way above.
	 * private ArrayList<Gizmo> searchId(String searchItem)
	{
		ArrayList<Gizmo> items = new ArrayList<Gizmo>();
		Scanner readFile;
		Gizmo g;
		
		File inputFile = new File(TEXT_FILE);
		try {
			readFile = new Scanner(inputFile);
			
			while(readFile.hasNextLine()) 
			{
				String [] splittedLine = readFile.nextLine().split(";");
				if(Integer.parseInt(splittedLine[0]) == Integer.parseInt(searchItem)) 
				{
					g = new Gizmo(Integer.parseInt(splittedLine[0]), 
									splittedLine[1], 
									Integer.parseInt(splittedLine[2]), 
									Double.parseDouble(splittedLine[3]));
					items.add(g);
				}
				else 
				{
					g = null;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;	
	} */


	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void closeBroker() {
		close();
		
	}
	
	private void close() 
	{
		try {
			raf.close();
			broker = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void printGizmo() 
	{
		try {
			raf.seek(0);
			
			for(long i = 0; i<raf.length(); i+=Gizmo.SIZE) 
			{
				Gizmo g = readRecord();
				
				if(g.isActive()) {
					System.out.print(g);
					System.out.println();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	
	public boolean addGizmo(Gizmo g) 
	{
		try {
			raf.seek(raf.length());
			writeRecord(g);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
	}
	
	public boolean deleteGizmo(Gizmo g) 
	{	
		boolean flag = false;
		try {
			long offset = searchGizmoId(g.getId());
			if(offset>0) 
			{
				raf.seek(offset);
				raf.writeBoolean(false);
				flag = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return flag;
		}
		
		return flag;
	}
	
	private long searchGizmoId(long id) 
	{
		
		boolean found = false;
		long position = -1l;
		try {
			raf.seek(0);
			
			for(long i = 0; i < raf.length() && !found; i+=Gizmo.SIZE) 
			{
				Gizmo g = readRecord();
				if(g.getId() == id && g.isActive()) 
				{
					found = true;
					position = i;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return position;
		
	}
	
	
	/*
	 * Three: implement the getter - must make it static so 
	 * we don't need to create an object to access it.
	 * In static methods we do not need to make an object.
	 * NameOfClass.NameOfMethod.  
	 * 
	 */
	public static GizmoBroker getGizmoBroker() 
	{
		File file = new File(BINARY_FILE);
		
		try 
		{
			//if file already exits
			if(file.exists()) 
			{
				if(broker == null) 
				{
					//only create gizmo instance
					broker = new GizmoBroker();
				}
			}
			else 
			{
				//if file does not exist
				if(broker == null) 
				{
					//create gizmo and create RandomAccessFile
					broker = new GizmoBroker();
					broker.loadBinaryFile();
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return broker;
	}
	
	
	/*******************************Private Methods**************************************************/
	private void loadBinaryFile() 
	{
		FileInput fin = new FileInput(TEXT_FILE);
		
		String line = fin.readLine();
		
		while(line != null) 
		{
			try 
			{
				Gizmo g = new Gizmo(line);
				writeRecord(g);
				
			}catch(Exception e)
			{
				
			}
			
			line = fin.readLine();
		}
		
		System.out.println("Created binary file");
		fin.closeInputFile();
	}
	
	
	private void writeRecord(Gizmo g) 
	{
		try 
		{
			
			raf.writeBoolean(g.isActive());
			raf.writeInt(g.getId());
			raf.writeUTF(Column.leftJustify(g.getDescription(),21));
			raf.writeInt(g.getQuantity());
			raf.writeDouble(g.getPrice());
			
			
			
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private Gizmo readRecord() 
	{
		Gizmo g = null;
		
		try 
		{
			
			g = new Gizmo();
			g.setActive(raf.readBoolean());
			g.setId(raf.readInt());
			g.setDescription(raf.readUTF().trim());
			g.setQuantity(raf.readInt());
			g.setPrice(raf.readDouble());;
			
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return g;
	}
}
