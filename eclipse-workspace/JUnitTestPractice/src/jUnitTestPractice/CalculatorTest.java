package jUnitTestPractice;

import static org.junit.Assert.*;// imported package, it is static

import org.junit.After;
import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {

	Calculator c;
	
	//Before and after runs for each test
	@Before
	public void prep() 
	{
		c = new Calculator();
		//System.out.println("I'm Prep!");
	}
	
	@After
	public void closing() 
	{
		c = null;
		//System.out.println("I'm closing!");
	}
	
	//Must, Must!!! have the @Test before methods to run the tests
	@Test
	public void test_sum_positive_numbers() {
		//System.out.println("Test One");
		int result = c.sum(5,2);
		assertEquals("I tried this method with 5 and 2 :",7, result);
	}
	
	//@Ignore 
	@Test 
	public void test_mul() 
	{
		//System.out.println("Test two");
		double result = c.mul(5,2);
		/*
		 * Why put the small number? It is an acceptable margin of error.
		 * Because a double may have many floating points
		 */
		assertEquals("I tried this method with 5 and 2 :",10, result, 0.00000001);
	}
	
	@Test
	public void test_sub() 
	{
		double result = c.sub(5,2);
		assertEquals("I tried this method with 5 and 2:",3,result,0.000000001);
	}
	
	@Test
	public void test_by_zero() 
	{
		try {
			int result = c.div(5, 0);
			fail("You have handled the exception incorrectly");
	
		}catch(ArithmeticException e) 
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void test_positive() 
	{
		boolean result = c.positive(-6);
		//usually used with booleans 
		assertFalse(result);
	}
	
	
}
