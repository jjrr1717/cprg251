package jUnitTestPracticeTwo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BankAccountTest1 {

	BankAccount ba;
	
	/*@Before 
	public void initialize() 
	{
		ba = new BankAccount();
	}*/
	
	@After
	public void close() 
	{
		ba = null;
	}
	
	@Test
	public void test() 
	{
		ba = new BankAccount("John", 1234, "Calgary", -598.26, 100);
		double result = ba.getBalance();
		assertEquals(0,result,0.00001);
	}
	
	@Test
	public void testDeposit() 
	{
		ba = new BankAccount("John", 1234, "Calgary", 50, 100);
		ba.withdraw(20);
		assertEquals(30, ba.getBalance(), 0.000001);
	}
	

	
}
