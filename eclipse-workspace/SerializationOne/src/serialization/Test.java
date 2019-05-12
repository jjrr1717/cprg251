package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
	
	public static void main (String [] args) 
	{
		Demo myObject = new Demo(1,"John");
		
		//.ser means the file is for serialization
		String filename = "res/file.ser";
		
		try {
			FileOutputStream myFile = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(myFile);
			
			out.writeObject(myObject);
			
			out.close();
			myFile.close();
			
			System.out.println("Object has been serialized!");
		} catch (Exception e) {
			System.out.println("An Exception hasppened");
		}
		System.out.println("Before: " + myObject);
		
		myObject = null;
		System.out.println("After: " + myObject);
		
		try {
			FileInputStream myFile = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(myFile);
			
			//returns an Object, so must convert it to the demo Object
			myObject = (Demo)in.readObject();
			
			System.out.println("Object has been deserialized!");
			in.close();
			myFile.close();
		} catch (Exception e) {
			System.out.println("An Exception hasppened");
		}
		
		System.out.println(myObject);

	}
}
