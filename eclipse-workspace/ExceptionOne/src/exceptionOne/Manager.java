package exceptionOne;

public class Manager {
	public static void main (String [] args) 
	{
		/*try 
		{
			System.out.println(8/0);
		}
		catch(Exception e)//this can be either catch or finally. e is an object of the Exception class. It will put exception in try into e
		{
			System.out.println("Oops! Divide by zero problem!");
			
			//you can take advantage of the e
			System.out.println(e.getMessage()); //getMessage() will show the message produced by the error
			
			e.printStackTrace();//will show the "detailed" error message(will show what point the error occurred 
		}*/
		
		
		
		//methodA();
		try 
		{
			System.out.println(8/0);
		}
		catch(Exception e) 
		{
			System.out.println("Ooops!");
		}
		finally 
		{
			System.out.println("I'm here!"); //Finally will run whether we have an exception or not
			//When to use finally? If you want to close the file no matter what happens - if it encounters no exception or if it does encounter an exception
		}

	}
	
	
	public static void methodA() 
	{
		methodB();
	}

	public static void methodB() 
	{
		methodC();
	}
	
	public static void methodC() 
	{
		//we could put this try catch in any of the methods, but the sooner you catch the problem, the better
		//possible to have multiple catch - but have to start from the most specific to the least specific
		//because the general will run all the time - so need to get to specific one first
		try
		{
			System.out.println(8/0);
		}
		catch(ArithmeticException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e) 
		{
			System.out.println("Something is wrong with calculation");
		}
	}
		
		
}
