package serializationFour;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadAndWrite {

	String filename = "res/competitor.ser";
	
	public ReadAndWrite() {}

	public void write(Competitor comp) {
		try {
			FileOutputStream theFile = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(theFile);

			out.writeObject(comp);

			out.close();
			theFile.close();

			System.out.println("Complete!");
		} catch (Exception e) {
			System.out.println("An exception has occured!");
		}

	}

	public Competitor read() {
		Competitor comp = new Competitor();
		try {
			FileInputStream theFile = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(theFile);

			comp = (Competitor) in.readObject();

			in.close();
			theFile.close();

			System.out.println("Complete!");
		} catch (Exception e) {
			System.out.println("An exception has occured!");
		}

		return comp;
	}

}
