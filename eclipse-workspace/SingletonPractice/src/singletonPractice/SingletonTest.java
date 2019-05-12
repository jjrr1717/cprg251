package singletonPractice;

public class SingletonTest {
	
	
	private static SingletonTest myInstance; 
	
	private int counter;
	
	//making the constructor private means we can't make an object from this class
	private SingletonTest() 
	{
		System.out.println("An object is created");
		counter = 0;
	}
	
	
	public int getCounter() {
		return counter;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	/*
	 * when something is static you do not need to make an object to call it. 
	 * E.g. Just say SingletonTest.getInstance();
	 * Don't do SingletonTest t = new SingletonTest(); - in fact we can't
	 * because the constructor is private. 
	 * 
	 * In static methods, all objects will use the same methods or attributes, 
	 * they will not be started a new. 
	 * 
	 * For example, if you want a counter to count the number of website
	 * visiters, you want the counter to be static so all visitors (which 
	 * could be an object) count the same counter. 
	 */
	public static SingletonTest getInstance() 
	{
		if(myInstance == null) 
		{
			myInstance = new SingletonTest();
			
		}
		return myInstance;
	}
	
	public void sayHi() 
	{
		System.out.println("Hi");
		
	}
	
	public void sayBye() 
	{
		System.out.println("Bye");
	}
	
	public void AddCounter(int x) 
	{
		counter += x;
	}

}
