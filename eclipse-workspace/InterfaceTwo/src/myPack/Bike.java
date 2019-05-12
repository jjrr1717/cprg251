package myPack;

public class Bike implements Vehicle {
	
	private int speed = 0;
	private int gear = 0;
	
	public Bike(int speed, int gear) 
	{
		this.speed = speed;
		this.gear = gear;
	}
	
	@Override
	public void changeGear(int a) {
		this.gear = a;
		
	}
	@Override
	public void speedUp(int a) {
		this.speed += a;
		
	}
	@Override
	public void applyBrakes(int a) {
		this.speed -=a;
		
	}
		
}
