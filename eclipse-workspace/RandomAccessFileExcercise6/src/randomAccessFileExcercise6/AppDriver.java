package randomAccessFileExcercise6;

public class AppDriver {
	public static void main (String [] args) throws Exception 
	{
		RandomAccessFilePractice rafp = new RandomAccessFilePractice();
		
		rafp.transferData();
		rafp.printCustomers();
		rafp.deleteRecord(2154);
		rafp.saveRecords();
		System.out.println("*********************");
		rafp.printCustomers();
		System.out.println(rafp.readRecord(2));
		
		
	}

}
