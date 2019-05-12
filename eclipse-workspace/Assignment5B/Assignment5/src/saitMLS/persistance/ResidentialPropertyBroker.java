/**

 * 
 *  @author Jocelyn Wegen
 *  @version April 3, 2019
 */

package saitMLS.persistance;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;
import saitMLS.problemDomain.CommercialProperty;
import saitMLS.problemDomain.ResidentialProperty;
import java.util.List;
import utilities.SLL;

public class ResidentialPropertyBroker implements Broker {

	/**
	 * broker to represent the ResidentialPropertyBroker
	 */
	private static ResidentialPropertyBroker broker;
	/**
	 * Input file that holds the residential property information
	 */
	private static final String INPUT_FILE = "res/resprop.txt";
	/**
	 * Serialized file of the residential properties
	 */
	private static final String SERIAL_FILENAME = "res/residentialProperty.ser";

	/**
	 * highId holds the highest property id number
	 */
	public long highId = 0;

	/**
	 * Singly Linked List to hold all the residential data.
	 */
	private SLL propertyList;

	/*************** Constructor ********************/

	/**
	 * private constructor to only allow one object of the ResidentialProperty.
	 * Accepts no parameters.
	 */
	private ResidentialPropertyBroker() {
	}

	/**
	 * Method to give access to the broker.
	 * 
	 * @return the broker for the broker
	 */
	public static ResidentialPropertyBroker getBroker() {
		// create an instance of the serialized file
		File serialFile = new File(SERIAL_FILENAME);

		/*
		 * if file exists create just create a ResidentialProperty object, otherwise
		 * create ResidentialPropertyBroker and create the serialized file. Only create
		 * ResidentialPropertyBroker if it doesn't exist - when it is null.
		 */
		if (serialFile.exists()) {
			if (broker == null) {
				// create ResidentialProperty object
				broker = new ResidentialPropertyBroker();
				// read from the serial file that already exists
				broker.readFromSerialFile();
				// put the highId to the last id in the record
				broker.findHighestCurrentId();

			}
		} else {
			if (broker == null) {
				// create ResidentialPropertyBroker object
				broker = new ResidentialPropertyBroker();
				// Create a linked list from the txt file
				broker.loadLinkedList();
				// put the highId to the last id in the record
				broker.findHighestCurrentId();

			}
		}

		return broker;

	}

	/************************* public methods *********************/

	/**
	 * Method to print the binary file. Used for testing purposes.
	 */
	public void printProperty() {

		for (int i = 0; i < propertyList.size(); i++) {
			// System.out.println(raf.getFilePointer());
			System.out.println(propertyList.get(i));

		}

	}

	/**
	 * Method to save a new ResidentialProperty when the id is at zero. If the id is
	 * not at zero than it update the property if there were any changes made.
	 * 
	 * @return a boolean if the property was successfully updated or added.
	 */
	public boolean persist(Object o) {
		boolean propertyAdded = false;
		boolean found = false;

		// create client object
		ResidentialProperty rp = (ResidentialProperty) o;

		// if property id is 0 then add property to end of propertyList
		if (rp.getId() == 0l) {
			// make sure high id is correct
			findHighestCurrentId();
			// assign the highest Id to property
			rp.setId(++highId);
			propertyList.append(rp);
			propertyAdded = true;
		}
		/*
		 * if the property id is not 0 then it already exists and the propertyList
		 * should be updated.
		 */
		else if (rp.getId() > 0l) {
			// search for the id in the propertyList
			for (int i = 0; i < propertyList.size() || !found; i++) {
				ResidentialProperty propertyInList = (ResidentialProperty) propertyList.get(i);

				if (propertyInList.getId() == rp.getId()) {
					propertyList.set(rp, i);
					propertyAdded = true;
				}
			}
		}
		return propertyAdded;
	}

	/**
	 * Method to remove a property for the propertyList.
	 * 
	 * @param o is an Object the user wishes to remove
	 */
	public boolean remove(Object o) {
		boolean removed = false;

		// create a property object
		ResidentialProperty rp = (ResidentialProperty) o;

		// find the property by matching id numbers
		for (int i = 0; i < propertyList.size() || !removed; i++) {
			// create property for property in list
			ResidentialProperty propertyInList = (ResidentialProperty) propertyList.get(i);

			// remove property from propertyList if matches id
			if (propertyInList.getId() == rp.getId()) {
				propertyList.remove(i);
				removed = true;

			}
		}

		return removed;
	}

	/**
	 * Method to search for a property based on user entry of search type and and
	 * item.
	 * 
	 * @param searchItem is a String of the user input to search for
	 * @param type is the type of data the user is searching for.
	 */
	public List search(String searchItem, String type) {
		// create an array of Clients found in search
		ArrayList<ResidentialProperty> searchResult = new ArrayList<ResidentialProperty>();

		// perform different search based on user input for type
		switch (type) {
		case "id":
			searchResult = searchId(searchItem);
			break;
		case "legal description":
			searchResult = searchLegalDescription(searchItem);
			break;
		case "quadrant":
			searchResult = searchQuadrant(searchItem);
			break;
		case "price":
			searchResult = searchAskingPrice(searchItem);
			break;
		default:
			System.out.println("Invalid");
		}

		return searchResult;
	}

	/**
	 * Method to close the the broker
	 */
	@Override
	public void closeBroker() {
		// make broker null
		broker = null;
		// save contents to the serial file
		writeToSerialFile();
	}

	/***************** private methods ***********************/
	/**
	 * Method to find the highest id, and assigns it to the highId.
	 */
	private void findHighestCurrentId() {
		// create ResidentialProperty to hold last item
		ResidentialProperty rp2 = (ResidentialProperty) propertyList.getLast();
		// to store the high id
		long highId = rp2.getId();

		// assign highId to attribute
		this.highId = highId;

	}

	/**
	 * searchId searches for the property id entered
	 * 
	 * @param searchItem is the property the user entered.
	 * @return a SSL of the results
	 */
	private ArrayList<ResidentialProperty> searchId(String searchItem) {
		// ArrayList to hold results
		ArrayList<ResidentialProperty> searchResults = new ArrayList<ResidentialProperty>();
		// parse searchItem to a long
		long id = Long.parseLong(searchItem);
		// stop searching through SLL once id is found
		boolean found = false;

		for (int i = 0; i < propertyList.size() || !found; i++) {
			// create property object
			ResidentialProperty rp2 = (ResidentialProperty) propertyList.get(i);
			if (rp2.getId() == id) {
				searchResults.add(rp2);
				found = true;
			}
		}

		return searchResults;

	}

	/**
	 * Method to search for Legal Description in the SLL. If found it will put
	 * property into a SLL.
	 * 
	 * @param searchItem the item to be searched for
	 * @return the SLL containing the properties found.
	 */
	private ArrayList<ResidentialProperty> searchLegalDescription(String searchItem) {
		// SLL list to hold results
		ArrayList<ResidentialProperty> searchResults = new ArrayList<ResidentialProperty>();

		// loop through SLL
		for (int i = 0; i < propertyList.size(); i++) {
			ResidentialProperty rp2 = (ResidentialProperty) propertyList.get(i);
			if (rp2.getLegalDescription().equalsIgnoreCase(searchItem))

				searchResults.add(rp2);
		}

		return searchResults;

	}

	/**
	 * Method to search for property quadrant
	 * 
	 * @param searchItem the item to be searched for
	 * @return the SLL containing the search results.
	 */
	private ArrayList<ResidentialProperty> searchQuadrant(String searchItem) {
		// SLL to hold the search results
		ArrayList<ResidentialProperty> searchResults = new ArrayList<ResidentialProperty>();

		// loop through SLL
		for (int i = 0; i < propertyList.size(); i++) {
			ResidentialProperty rp2 = (ResidentialProperty) propertyList.get(i);
			if (rp2.getQuadrant().equalsIgnoreCase(searchItem))

				searchResults.add(rp2);
		}

		return searchResults;

	}

	/**
	 * Method to search for prices greater than what user entered.
	 * 
	 * @param searchItem the item to be searched for
	 * @return the SLL containing the search results
	 */
	private ArrayList<ResidentialProperty> searchAskingPrice(String searchItem) {
		// SLL to hold the search results
		ArrayList<ResidentialProperty> searchResults = new ArrayList<ResidentialProperty>();

		double price = Double.parseDouble(searchItem);

		// loop through SLL
		for (int i = 0; i < propertyList.size(); i++) {
			ResidentialProperty rp2 = (ResidentialProperty) propertyList.get(i);
			if (rp2.getAskingPrice() >= price) {
				searchResults.add(rp2);
			}
		}

		return searchResults;

	}
	
	/**
	 * Method to load property data from the
	 * text file into a singly linked list. 
	 */
	private void loadLinkedList() {
		// create the linked list
		propertyList = new SLL();

		// open the text file
		File inputFile = new File(INPUT_FILE);

		// scanner
		try {
			Scanner readFile = new Scanner(inputFile);

			// loop through file
			while (readFile.hasNextLine()) {
				// put the lines in an Array
				String[] line = readFile.nextLine().split(";");

				// increase the id by one per each property
				++highId;

				// create a residentialProperty from each line
				ResidentialProperty rp = new ResidentialProperty(highId, line[0], line[1], line[2], line[3],
						Double.parseDouble(line[4]), line[5], Double.parseDouble(line[6]), Double.parseDouble(line[7]),
						Integer.parseInt(line[8]), line[9].charAt(0));

				// append rp to the linked list
				propertyList.append(rp);

			}

			readFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLegalDescriptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNumberOfBathroomsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to write contents in the singly linked list
	 * into a serial file. 
	 */
	private void writeToSerialFile() {
		try {
			//open serial file
			FileOutputStream theFile = new FileOutputStream(SERIAL_FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(theFile);

			// write list to serial file

			for (int i = 0; i < propertyList.size(); i++) {
				out.writeObject(propertyList.get(i));
			}

			//close serial file
			theFile.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to read the contents of the serial file
	 * and put into a singly linked list. 
	 * @return a singly linked list containing objects
	 * from the serial file. 
	 */
	private SLL readFromSerialFile() {
		//flag to stop reading serial file
		boolean continueReading = true;
		//start the SLL fresh
		propertyList = new SLL();
		try {
			//open serial file
			FileInputStream file = new FileInputStream(SERIAL_FILENAME);
			ObjectInputStream in = new ObjectInputStream(file);

			// create an property
			ResidentialProperty rp = null;
			//loop through serial file and read each object to put in serial file
			do 
			{
				rp = (ResidentialProperty) in.readObject();
				if(rp != null) 
				{
					propertyList.append(rp);
				}
				else 
				{
					continueReading = false;
				}
			}while(continueReading);

			//close serial file
			file.close();
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyList;

	}
}
