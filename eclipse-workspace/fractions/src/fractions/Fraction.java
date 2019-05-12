package fractions;

public class Fraction {
	
	private int numinator;
	private int denominator;
	
	public Fraction() 
	{
		System.out.print("I'm the default constructor in Fraction");
	}
	
	public Fraction(int n, int d) 
	{
		numinator = n;
		denominator = d;
	}
	
	public void setNuminator(int n) 
	{
		numinator = n;
	}
	
	public int getNuminator()
	{
		return numinator;
	}
	
	public void setDenominator (int d) 
	{
		denominator = d;
	}
	
	public int getDenominator() 
	{
		return denominator;
	}
	
	public String toString () 
	{
		return (getNuminator() + "/" + getDenominator());
	}

}
