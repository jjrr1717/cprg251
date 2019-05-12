package serialization;

import java.io.Serializable;

public class Calculator implements Serializable {
	
	private int op1;
	private int op2;
	private static final long serialversionUID = 64861315L;
	
	public Calculator() {}
	

	public int getOp1() {
		return op1;
	}

	public void setOp1(int op1) {
		this.op1 = op1;
	}

	public int getOp2() {
		return op2;
	}

	public void setOp2(int op2) {
		this.op2 = op2;
	}
	
	public int sum() 
	{

		return getOp1() + getOp2();
	}
	
	public int mul() 
	{

		return getOp1() * getOp2();
	}
	
	public String toString() 
	{
		return "op1: " + getOp1() + 
				"\nop2: " + getOp2();
	}

}
