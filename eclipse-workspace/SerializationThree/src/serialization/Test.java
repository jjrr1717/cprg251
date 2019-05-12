package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
	public static void main(String[] args) {
		
		String filename = "res/myFile.ser";
		
		Calculator myCal = new Calculator();
		myCal.setOp1(3);
		myCal.setOp2(2);
		
		try {
			FileOutputStream myFile = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(myFile);
			
			out.writeObject(myCal);
			
			out.close();
			myFile.close();
			
			System.out.println("Done!");
			
		} catch (Exception e) {
			System.out.println("Exception occured!");
		}
		
		System.out.println("Before: " + myCal);
		myCal = null;
		System.out.println("After: " + myCal);
		
		
		try {
			FileInputStream myFile = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(myFile);
			myCal = (Calculator) in.readObject();
			
			System.out.println("Deserialization Complete!");
			in.close();
			myFile.close();
			
		} catch (Exception e) {
			System.out.println("Exception occured!");
		}
		
		System.out.println(myCal.sum());
		System.out.println(myCal.mul());
	}

}
