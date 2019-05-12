/**
 * PlayListManager manages all the method's and items needed to 
 * run the PlayList app. 
 * @author Jocelyn Wegen
 * @version February 3, 2019
 */
package itemManagementDomain;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import itemDomain.Commercial;
import itemDomain.Item;
import itemDomain.Song;
import itemDomain.TalkShow;

public class PlayListManager {
	
	/**
	 * PLAYLIST_FILE is a string constant containing the path
	 * of the file containing the playlist items.
	 */
	private final String PLAYLIST_FILE = "res/database.txt";
	
	/**
	 * RANDOM_LIST_FILE is a string containing the path 
	 * of the file containing the random playlist created
	 * by the user. 
	 */
	private final String RANDON_LIST_FILE = "res/random_list.txt";
	
	/**
	 * itemList is an ArrayList to hold items of a playlist
	 */
	private ArrayList<Item> itemList;
	
	/**
	 * filteredList is an ArrayList to the the filtered 
	 * items of a playlist.
	 */
	private ArrayList<Item> filteredList = new ArrayList<Item>();
	
	/**
	 * randItemList is an ArrayList to hold items of a playlist in 
	 * a random order.
	 */
	private ArrayList<Item> randItemList;
	
	//keyboard input
	Scanner input = new Scanner(System.in);
		
	/**
	 * Default Constructor. Accepts no parameters.
	 */
	public PlayListManager() 
	{
		itemList = new ArrayList<Item>();
		randItemList = new ArrayList<Item>();
	}
	
	/**
	 * validateData is a method that validates the
	 * data going into the playlist.
	 * @param theId
	 * @return a boolean true is data is valid or 
	 * false if the data is not valid.
	 */
	private boolean validateData(String theId) 
	{
		boolean valid = false;
		
		/*if the id is four digits long and ends with
		 * 0-9 then the data is valid.
		 */
		
		if(theId.length() == 4 && 
		   (theId.charAt(3) == '0' ||
		   theId.charAt(3) == '1' ||
		   theId.charAt(3) == '2' ||
		   theId.charAt(3) == '3' ||
		   theId.charAt(3) == '4' ||
		   theId.charAt(3) == '5' ||
		   theId.charAt(3) == '6' ||
		   theId.charAt(3) == '7' ||
		   theId.charAt(3) == '8' ||
		   theId.charAt(3) == '9')) 
		{
				valid = true;
		}
		
		return valid;
	}
	
	/**
	 * searchById is a method that goes through
	 * the playlist to find the id entered
	 * by the user.
	 * @param idToSearch is an int representing the
	 * item's id. 
	 * @return an Item based on the idToSearch. 
	 * returns null if item is not found.
	 */
	private Item searchById (int idToSearch)
	{
		//create Item to hold searched Item
		Item searchedItem = new Song();
		//searchedItem is null until it finds an id
		searchedItem = null;
		
		//search through the itemList for the idToSearch
		for(Item anItem: itemList) 
		{
			/*if idToSearch matches an existing Item's id
			 * it will create a searchItem Item.
			 */
			if(idToSearch == anItem.getId()) 
			{
				if(anItem instanceof Song) 
				{
					searchedItem = new Song();
					searchedItem = anItem;
				}
				else if(anItem instanceof TalkShow) 
				{
					searchedItem = new TalkShow();
					searchedItem = anItem;
				}
				else if(anItem instanceof Commercial) 
				{
					searchedItem = new Commercial();
					searchedItem = anItem;
				}

			}

		}	

		return searchedItem;
	}
	
	/**
	 * createItemList is a method to put all the playlist
	 * items into an ArrayList. Items are found in a 
	 * text file.  
	 * @throws Exception for IO
	 */
	public void createItemList() throws Exception 
	{
		//open file and set up Scanner
		File inputFile = new File(PLAYLIST_FILE);
		Scanner scanInputFile = new Scanner(inputFile);
		
		//Item will be help in the Array splitted
		String [] splitted;
		
		//create boolean that will indicate if data in file is valid
		boolean validData = false;
		
		//scan through file
		while(scanInputFile.hasNext()) 
		{
			//add items to Array
			splitted = scanInputFile.nextLine().split(",|:");
			//validate item using validateData method
			validData = validateData(splitted[0]);
			
			/*
			 * if id of an item is valid an Item is created, 
			 * either a Song, Talk Show, or Commercial, and
			 * then is added to the itemList ArrayList. 
			 */
			if(validData) 
			{
				switch(splitted[0].charAt(3)) 
				{
					//0-2 is a Talk Show
					case '0':
					case '1':
					case '2':
						//create Talk Show instance
						TalkShow ts = new TalkShow (Integer.parseInt(splitted[0]), 
								splitted[1].charAt(0), 
								Integer.parseInt(splitted[4]),
								Integer.parseInt(splitted[5]),
								splitted[6],
								splitted[2], 
								splitted[3]);
						//add item to itemList
						itemList.add(ts);
						break;
					//3-7 is a Song
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
						//create Song instance
						Song s = new Song(Integer.parseInt(splitted[0]), 
								splitted[1].charAt(0), 
								Integer.parseInt(splitted[4]),
								Integer.parseInt(splitted[5]),
								splitted[6],
								splitted[2], 
								splitted[3]);
						
						//add Song to itemList
						itemList.add(s);
						break;
					//8-9 is a commercial
					case '8':
					case '9':
						//create a commercial instance
						Commercial c = new Commercial(Integer.parseInt(splitted[0]), 
								splitted[1].charAt(0), 
								Integer.parseInt(splitted[3]),
								Integer.parseInt(splitted[4]),
								splitted[5],
								splitted[2]);
						//add commercial to itemList
						itemList.add(c);
						break;
				}
			}
		}
		//close file
		scanInputFile.close();
	}
	
	/**
	 * printFullList is a method that prints the entire
	 * playList.
	 */
	public void printFullList() 
	{
		//create Iterator for Item
		Iterator<Item> iterator = itemList.iterator();
		System.out.println("Complete List of playList:");
		//while itemList has an Item print it's toString method
		while(iterator.hasNext()) 
		{
			System.out.print(iterator.next() + "\n");
		}

	}
	
	/**
	 * printFilteredTalkShowList is a method that prints
	 * a filtered playList based on user input.  
	 * @param categorySelection is a char of the category the
	 * user selected to see. 
	 * @throws Exception for IO
	 */
	public void printFilteredTalkShowList(char categorySelection) throws Exception
	{
		//go through the itemList to find an item that matches categorySelection
		for(Item anItem: itemList) 
		{
			//if anItem's category matches categorySelection add it to the filteredList
			if(anItem.getCategory() == categorySelection && anItem instanceof TalkShow)
			{
				filteredList.add(anItem);
			}
		}
		
		//print the filtered list to the console
		Iterator<Item> iterator = filteredList.iterator();
		System.out.print("List of filtered playList:\n");
		while(iterator.hasNext()) 
		{
			System.out.print(iterator.next() + "\n");
		}
		
		//clear the filteredList for next use
		filteredList.clear();
	}
	
	/**
	 * printFilteredSongist is a method that prints
	 * a filtered playList based on user input.  
	 * @param categorySelection is a char of the category the
	 * user selected to see. 
	 * @throws Exception for IO
	 */
	public void printFilteredSongList(char categorySelection) throws Exception
	{
		//go through the itemList to find an item that matches categorySelection
		for(Item anItem: itemList) 
		{
			//if anItem's category matches categorySelection add it to the filteredList
			if(anItem.getCategory() == categorySelection && anItem instanceof Song)
			{
				filteredList.add(anItem);
			}
		}
		
		//print the filtered list to the console
		Iterator<Item> iterator = filteredList.iterator();
		System.out.print("List of filtered playList:\n");
		while(iterator.hasNext()) 
		{
			System.out.print(iterator.next() + "\n");
		}	
		
		//clear the filteredList for next use
		filteredList.clear();
	}
	
	/**
	 * printFilteredCommercialList is a method that prints
	 * a filtered playList based on user input.  
	 * @param categorySelection is a char of the category the
	 * user selected to see. 
	 * @throws Exception for IO
	 */
	public void printFilteredCommercialList(char categorySelection) throws Exception
	{
		//go through the itemList to find an item that matches categorySelection
		for(Item anItem: itemList) 
		{
			//if anItem's category matches categorySelection add it to the filteredList
			if(anItem.getCategory() == categorySelection && anItem instanceof Commercial)
			{
				filteredList.add(anItem);
			}
		}
		
		//print the filtered list to the console
		Iterator<Item> iterator = filteredList.iterator();
		System.out.print("List of filtered playList:\n");
		while(iterator.hasNext()) 
		{
			System.out.print(iterator.next() + "\n");
		}
		//clear the filteredList for next use
		filteredList.clear();
	}
	
	/**
	 * addItem is a method that adds an Item to 
	 * the itemList ArrayList.
	 * @throws Exception for IO
	 */
	public void addItem() throws Exception
	{
		//create an item to hold an Item that exists
		Item itemPresence = new Song();
		
		//boolean for testing if an Item's id is valid
		boolean validId = false;
		System.out.println("Please enter the following information" +
				" to add and item to the playlist.");
		
		//while id is present or not valid keep asking to enter id. 
		do
		{
			//get newId from user
			System.out.print("Enter Id: ");
			int newId = input.nextInt();
			
			//test if Item already exits with searchById method
			itemPresence = searchById(newId);
			//test if id is valid with validId method
			validId = validateData(newId+"");
			
			//if it passes all tests allow user to enter rest of information
			if(itemPresence == null && validId == true) 
			{
				System.out.print("Enter Category: ");
				char newCategory = input.next().charAt(0);
			
				System.out.print("Enter Minutes: ");
				int newMinutes = input.nextInt();
			
				System.out.print("Enter Seconds: ");
				int newSeconds = input.nextInt();
			
				System.out.print("Enter Audio File: ");
				String newAudioFile = input.next();
				
				switch(Integer.toString(newId).charAt(3)) 
				{
				case '0':
				case '1':
				case '2':
					input.nextLine();
					System.out.print("Enter Title: ");
					String newTitle = input.nextLine();
					String newHost = "N/A";
					
					TalkShow newTalkShow = new TalkShow(newId, newCategory,
							newMinutes, newSeconds, newAudioFile, newTitle,
							newHost);
					
					itemList.add(newTalkShow);
					break;
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
					input.nextLine();//consume line
					System.out.print("Enter Artist or Group: ");
					String newArtistGroup = input.nextLine();
					
					System.out.print("Enter title: ");
					newTitle = input.nextLine();
					
					Song newSong = new Song(newId, newCategory,
							newMinutes, newSeconds, newAudioFile, newTitle,
							newArtistGroup);
					itemList.add(newSong);
					break;
				case '8':
				case '9':
					input.nextLine();
					System.out.print("Enter company: ");
					String newCompany = input.nextLine();
					
					Commercial newCommercial = new Commercial(newId, newCategory,
							newMinutes, newSeconds, newAudioFile, newCompany);
					
					itemList.add(newCommercial);
					
				}
				
				System.out.println("\nItem added!");
			
			}
			else if (itemPresence != null)
			{
				System.out.println("That Id already exits. Please" +
						" enter a different Id.\n");
			}
			else if (validId != true)
			{
				System.out.println("Invalid id entered, please try again!\n");
			}

			
		}while(itemPresence != null || validId == false);
		

		System.out.println(); //print extra line for appearance
		//System.out.println(itemList);
			
	}
	
	/**
	 * deleteItem is a method that will remove an Item
	 * from the ArrayList itemList
	 */
	public void deleteItem() 
	{
		//itemPresence to hold item if it exists
		Item itemPresence = new Song();
		System.out.println("Remove an item.");
		
		//while item does not exist keep asking for id
		do 
		{
			//get user input for id to remove
			System.out.print("Enter Id: ");
			int itemRemoval = input.nextInt();
			
			//test for Item existance using searchById method
			itemPresence = searchById(itemRemoval);
			
			//if item exits (not null) remove item from itemList ArrayList
			if(itemPresence != null) 
			{
				//remove item with Iterator
				Iterator<Item> iterator = itemList.iterator();
				while(iterator.hasNext()) 
				{
					Item anItem = new Song();
					anItem = iterator.next();
					if(itemRemoval == anItem.getId()) 
					{
						iterator.remove();
					}
				}

			}
			else 
			{
				System.out.println("The Id you entered can not be found. " +
									"Please try again.");
				System.out.println();
			}
		}
		while(itemPresence == null);
		
		System.out.println("\nItem Removed!");
		System.out.println(); //print extra line for appearance
		
		//System.out.println(itemList);
	}
	
	/**
	 * showSearchedId is a method to print an Item
	 * that has been searched using searchById method. 
	 */
	public void showSearchedId() 
	{
		int idToBeSearched;
		System.out.print("Please enter the " +
						 "Id number you want "+
						 "to search for: ");
		idToBeSearched = input.nextInt();
		
		//if item exists (is not null) then it will print the item to console
		if(searchById(idToBeSearched) != null) 
		{
			System.out.println(searchById(idToBeSearched));
		}
		else 
		{
			System.out.println("The Id you entered can not be " +
								"found.  Please search again.");
			System.out.println();
		}
	}
	
	/**
	 * getRandList is a method to return a random list
	 * of the Items in the playlist file. It
	 * will only return items that add up to
	 * plus or minus 30 seconds of a user's 
	 * input. 
	 * @return an ArrayList of the randomly selected items. 
	 */
	public ArrayList<Item> getRandList()
	{	
		final int graceTime = 30;
		int minutes;
		int seconds;
		int totalUserTimeSeconds;
		int secondsCounter = 0;
		int minTime;
		int maxTime;

		//get the time from the user
		System.out.println("Please enter the time for "+ 
							"the randomly generated " +
							"playlist.");
		System.out.print("Enter the minutes: ");
		minutes = input.nextInt();
		
		System.out.print("Enter the seconds: ");
		seconds = input.nextInt();
		
		//calculate the total time entered by user to seconds
		totalUserTimeSeconds = (minutes *60) + seconds;
		//calculate min time (user input - 30)
		minTime = totalUserTimeSeconds - graceTime;
		//calculate max time (user input + 30)
		maxTime = totalUserTimeSeconds + graceTime;
		
		/*while seconds counter is less than the min 
		 * time continue obtaining random numbers 
		 */
		while(secondsCounter <= minTime ) //&& secondsCounter <= maxTime
		{	
			//create Random object
			Random random = new Random();
			//random can only grab integers for the index the length of the itemList ArrayList
			int randomItemIndex = random.nextInt(itemList.size());
			//get the total time of the random Item in seconds
			int timeOfRandItemInSeconds = (itemList.get(randomItemIndex).getMinutes() * 60) + itemList.get(randomItemIndex).getSeconds();
			
			randItemList.add(itemList.get(randomItemIndex));
			
			//add the time of the Index to the secondsCounter
			secondsCounter += timeOfRandItemInSeconds;
			
			/*
			 * if the last item goes over the max time remove the item 
			 * from the randItemList ArrayList and subtract the the
			 * time of the item from the secondsCounter.
			 */
			
			if(secondsCounter >= maxTime) 
			{
				randItemList.remove(itemList.get(randomItemIndex));
				secondsCounter -= timeOfRandItemInSeconds;
			}
				
		}
		
		return randItemList;
		
	}
	
	/**
	 * createRandPlayListFile creates an output file
	 * for the randomized playlist. 
	 * @throws Exception for IO
	 */
	public void createRandPlayListFile() throws Exception
	{
		//create output file 
		FileWriter fw = new FileWriter(RANDON_LIST_FILE, false);
		PrintWriter writeRandList = new PrintWriter(fw);
		
		//for all the Items in randItemList write it to output file
		for(Item anItem: randItemList) 
		{
			
			writeRandList.print(anItem.getId() + "," +
								 anItem.getCategory() + ",");
			if(anItem instanceof TalkShow) 
			{
				writeRandList.print(((TalkShow) anItem).getTitle() + "," +
								((TalkShow)anItem).getHost() + ",");
			}
			
			else if(anItem instanceof Song) 
			{
				writeRandList.print(((Song) anItem).getTitle() + "," +
									((Song)anItem).getArtistGroup() + ",");
			}
			else if(anItem instanceof Commercial) 
			{
				writeRandList.print(((Commercial) anItem).getCompany() + ",");
			}
			
			writeRandList.print(anItem.getMinutes() + ":" +
								anItem.getSeconds() + "," + 
								anItem.getAudioFile() + "\r\n");

		}
		
		//close output file
		writeRandList.close();
	}
	
	
	/**
	 * saveChanges is a method that will re-write all the 
	 * changes the user made to the ArrayList itemList to
	 * the input file PLAYLIST_FILE.
	 * @throws Exception for IO
	 */
	public void saveChanges() throws Exception
	{
		//open up the file to write changes to it
		FileWriter fw = new FileWriter(PLAYLIST_FILE, false);
		PrintWriter updatePlaylist = new PrintWriter(fw);
		
		//for every Item in itemList write it to the file.  
		for(Item anItem: itemList) 
		{
			
			updatePlaylist.print(anItem.getId() + "," +
					anItem.getCategory() + ",");
			//specialized attributes for a TalkShow
			if(anItem instanceof TalkShow) 
			{
				updatePlaylist.print(((TalkShow) anItem).getTitle() + "," +
								((TalkShow)anItem).getHost() + ",");
			}
			
			//specialized attributes for a Song 
			else if(anItem instanceof Song) 
			{
				updatePlaylist.print(((Song) anItem).getTitle() + "," +
									((Song)anItem).getArtistGroup() + ",");
			}
			
			//specialized attributes for a Commercial
			else if(anItem instanceof Commercial) 
			{
				updatePlaylist.print(((Commercial) anItem).getCompany() + ",");
			}
			
			updatePlaylist.print(anItem.getMinutes() + ":" +
								anItem.getSeconds() + "," + 
								anItem.getAudioFile() + "\r\n");

		}
		
		//close file
		updatePlaylist.close();
		
		
	}

}
