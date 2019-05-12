package exceptionTwo;

public class ExceptionTest {
	
	public double findSquareRoot(double x) throws MyExceptionHandler //putting this forces the method to always be embraced in a try catch
	{
		if(x <0 ) 
		{
			throw new MyExceptionHandler("Can't take the square root of a negative number");
		}
		return Math.sqrt(x);
	}
	
	public static void main (String [] args) 
	{
		ExceptionTest et = new ExceptionTest();
		
		try 
		{
			System.out.println(et.findSquareRoot(5));
		} catch (MyExceptionHandler e) {
			System.out.println(e.getMessage());
		}
		finally 
		{
			System.out.println("hi");
		}
	}

}
