package recursion;

import java.util.ArrayList;

public class Factorial {
	
	
	public long factorial(long n) {
		
		long result = 1;
		
		for(int i = 1; i <= n; i++) {
			result *= i;
		}
		
		return result;
	}
	
	
	//the recursion one
	public long factTwo(long n) {
	
		if(n == 1 || n==0) {
			return 1;
		}
		else {
			return n*factTwo(n-1);
		}
	}
	
	public int fib(int n) 
	{
	
		
		int numOne = 1;
		int numTwo = 1;
		int temp = 0;
		
		if(n==1 || n==2) {
			temp = 1;
		}
		else {
			for(int i = 3; i <= n; i++) {
				temp = numOne + numTwo;
				numOne = numTwo;
				numTwo = temp;
			}
		}
		return temp;	
		
	}
	
	
	//Recursion for fib
	
	public int fib2(int n) {
		//Stopping point? When n is one or two
		//recursion call = fib2(n-1) + fib2(n-2)
		
		if(n==1 || n==2) {
			return 1;
		}
		else {
			return fib2(n-1) + fib2(n-2);
		}

	}
	

	
	public int mul(int m, int n) {
		//stopping point: n = 1
		//recursion call: mul(m,n) = m + mul(m, n-1)
		
		if(n == 1) {
			return m;
		}
		else {
			return m + mul(m, n-1);
		}
	}
	
	public int div(int m, int n) {
		//Stopping point when n = 1
		
		if(m < n) {
			return 0;
		}
		else {
			return 1 + div(m-n, n);
		}
	}
	
	
	public int mod(int m, int n) {
		//19%4, 15%4, 11%4, 7%4, 3%4
		
		if(m < n) {
			return m;
		}
		else {
			return mod(m-n, n);
		}
	}
	


}
