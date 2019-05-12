package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
	public static void main(String [] args) 
	{
		String filename = "res/myFile.ser";
		
		EMP myEMP = new EMP(35,"Kohsro", 1111);
		EMP.setCounter(1000);
		
		try {
			FileOutputStream myFile = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(myFile);
			
			out.writeObject(myEMP);
			
			out.close();
			myFile.close();
			
			System.out.println("Done!");
	
		} catch (Exception e) {
			System.out.println("An exception occured!");
		}
		
		System.out.println("Data Before: ");
		System.out.println(myEMP);
		EMP.setCounter(7777);
		myEMP = null;
		System.out.println("After: " + myEMP);
		EMP.setCounter(9999);
		//myEMP.setName("Mark");
		try {
			
			//now deserialize the object
			FileInputStream myFile = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(myFile);
			
			myEMP = (EMP)in.readObject();
			System.out.println("Deserialized");
			System.out.println(myEMP);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
