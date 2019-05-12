package singletonPractice;

public class AppDriver {
	public static void main (String [] args) 
	{
		//both of these are using the same object to get access to the methods
		SingletonTest st =  SingletonTest.getInstance();
		
		st.sayHi();
		st.AddCounter(10);
		System.out.println(st.getCounter());
		SingletonTest st2 = SingletonTest.getInstance();
		
		st2.sayBye();
		//because st and st2 is from the same object, getCounter will be the same value
		System.out.println(st2.getCounter());

	}

}
