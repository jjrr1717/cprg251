package fractions;

public class AppDriver {
	public static void main (String [] args) 
	{
		Fraction fraction = new Fraction(1, 5);
		
		//System.out.println(fraction);
		
		WholeFraction wf = new WholeFraction(3,7,9);
		
		//System.out.println(wf);
		
		Fraction f2 = new WholeFraction(2,4,15); //this is polymorphism
		/*because this is actually a Fraction object can't have access to the methods in Whole Fraction
		 * even though we are using the WholeFraction constructor. Why would we do this? When working
		 * with client-server interactions we want to send a specific type of object (send the 
		 * generalized object over.
		 */
		
		WholeFraction wf2 = (WholeFraction) f2;
		System.out.println(wf2.getWhole());
		
		
		
		//System.out.println(f2);
		
		Fraction [] fArray = new Fraction[3];
		fArray[0] = fraction;
		fArray[1] = wf;
		fArray[2] = f2;
		
		/*for(Fraction aFraction: fArray) 
		{
			System.out.println(aFraction);
		}*/
		
		for(Fraction ff: fArray) 
		{
			if(ff instanceof WholeFraction) 
			{
				System.out.println(ff);
			}
		}
			
	}

}
