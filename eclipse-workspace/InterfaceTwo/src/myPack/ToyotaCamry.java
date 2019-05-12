package myPack;

public class ToyotaCamry extends Car {
	
	private boolean smart;
	private int speed = 0;
	private int gear = 0;
	private long km = 0;

	public ToyotaCamry(long km, float engine, int model, char type, boolean smart) 
	{
		super(km, engine, model, type);
		this.smart = smart;
	}
	
	public String toString() 
	{
		return (super.toString() +
				"Speed: " + 
				getSpeed() + 
				"Gear: " + 
				getGear() + 
				"Smart? " + 
				getSpeed());
	}

	public long getKm() {
		return km;
	}

	public void setKm(long km) {
		this.km = km;
	}
	
	public boolean isSmart() {
		return smart;
	}

	public void setSmart(boolean smart) {
		this.smart = smart;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int gear) {
		this.gear = gear;
	}

	@Override
	public void changeGear(int a) {
		gear = a;
		
	}

	@Override
	public void speedUp(int a) {
		speed += a;
		
	}

	@Override
	public void applyBrakes(int a) {
		speed -=a;
		
	}

	@Override
	public void addKm(long m) {
		km += m;
		
	}


	
	

}
