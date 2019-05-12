package randomAccessFileOne;

import java.io.RandomAccessFile;

public class RandomAccessFileTest {
	
	public void createRAF() throws Exception
	{
		/*
		 * Constructor accepts two arguements ("file path", "mode");
		 * Mode can be r, w, rw, rwd, rws (read and write)
		 * rwd and rws saves the infromation to the file right away
		 * rwd = write into file right away but won't put a time stamp until file is closed
		 * rws = write to file right away and time stamp right away.
		 * Without d or s you can lose all your information if you system crashes. 
		 */
		RandomAccessFile raf = new RandomAccessFile("res/myRAF.txt", "rws");
		
		//Character takes two bytes, so it will take up 0 and 1.  Pointer is now at 2
		raf.writeChar('A');
		
		/*10.5 is default as a double, so we need to change it to a float, just put f at end
		 * Float takes four Bytes 2,3,4,5.  Now pointer is at 6.  
		 */
		raf.writeFloat(10.5f);
		
		/*
		 * 1 Byte for boolean, 6.  Pointer at 7.
		 */
		raf.writeBoolean(true);
		
		raf.writeDouble(21.589);
		
		/*
		 * UTF is to write Strings.
		 * UTF is 1 Byte plus 2 Bytes at the beginning
		 * e.g. Hello = 2Hello
		 */
		raf.writeUTF("Hello my name is Jocelyn");
		
		raf.close();
		
	}
	
	public void readRAF() throws Exception
	{
		//open just to read - that's why only "r" in the mode
		RandomAccessFile raf = new RandomAccessFile("res/myRAF.txt", "r");
		
		//seek changes where the pointer is
		raf.seek(5);
		System.out.println(raf.readBoolean());
		
		raf.seek(0);
		System.out.println(raf.readChar());
		//prints out where the pointer is
		System.out.println(raf.getFilePointer());
		raf.seek(15);
		System.out.println(raf.readUTF());
		System.out.println(raf.getFilePointer());
		raf.close();
	}

}
