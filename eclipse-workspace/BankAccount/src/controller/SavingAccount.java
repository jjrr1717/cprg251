package controller;

public class SavingAccount extends BankAccount {
	
	double annualInterestRate;
	
	public void depositInterstAmount(double interestAmountInPercent) 
	{
		balance += (1 + (interestAmountInPercent/100));
	}

	@Override
	public void withdrawal(double amount) {
		balance -= amount;
		
	}

}
