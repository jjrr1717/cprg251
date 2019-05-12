package jUnitTestPractice;

public class Calculator {

	public int sum(int first, int second) 
	{
		return first + second;
	}
	
	public double mul(int first, int second) 
	{
		return first * second;
	}
	
	public double sub(double first, double second) 
	{
		return first - second;
	}
	
	public int div(int first, int second) 
	{
		return first/second;
	}
	
	public boolean positive(int pos) 
	{
		boolean answer = false;
		if(pos >= 0)
			answer = true;
		return answer;
			
	}
}
