package myPack;

public abstract class Car implements Vehicle {
	
	private long km = 0;
	private float engine;
	private int model;
	private char type;
	
	public Car(long km, float engine, int model, char type) 
	{
		this.km = km;
		this.engine = engine;
		this.model = model;
		this.type = type;
	}
	
	public String toString() 
	{
		return ("Km: " + getKm() + 
				"\nEngine: " + 
				getEngine() + 
				"\nModel: " + 
				getModel() + 
				"\nType: " + 
				getType());
	}
	
	
	public abstract void addKm(long m);
	
	public long getKm() {
		return km;
	}
	public void setKm(long km) {
		this.km = km;
	}
	public float getEngine() {
		return engine;
	}
	public void setEngine(float engine) {
		this.engine = engine;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
}
