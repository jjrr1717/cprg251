	package myPackage;
	
	public class Driver {
		public static void main (String [] args) {
			//variables
			Simple mySimple = new Simple();
			Simple yourSimple = new Simple(5, 2.0, "CPRG 251");
			
			System.out.println(mySimple.getLine()); //will get null
			System.out.println(yourSimple.getLine()); //will get CPRG 251
			
			mySimple.setLine("my line");
			
			System.out.println(mySimple.getLine()); //will get my line
			System.out.println(yourSimple.getLine()); //will get CPRG 251
			
		}
		
}