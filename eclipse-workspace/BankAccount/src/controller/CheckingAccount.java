package controller;

public class CheckingAccount extends BankAccount {
	
	private double insufficientFundsFee;
	
	public double processCheck(double checkToProcess) 
	{
		return checkToProcess;
	}

	@Override
	public void withdrawal(double amount) {
		balance -= (amount + insufficientFundsFee);
		
	}
	

}
