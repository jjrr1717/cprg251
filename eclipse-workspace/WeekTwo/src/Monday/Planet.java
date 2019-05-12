/** @author Jocelyn Wegen
 * @version January 14, 2019
 * Class to create instances of planets that will have the radius and density for attributes.
 * Methods to calculate volume and mass of the planet. 
 */

package Monday;

public class Planet {
	
	/** 
	 * the radius of the planet in meters
	 */
	private double rad;
	
	/**
	 * the density of the planet in kg/m3
	 */
	private double dens;
	
	/** Default constructor assigns 1.0 to both radius and density
	 * No parameters 
	 */
	public Planet() {
		//this.rad = 1.0;
		//this.dens = 1.0;
		this(1.0,1.0);
	}
	
	/** Constructor to allow user to assign values to radius and density
	 * 
	 * @param rad is a double to assign radius
	 * @param dens is a double to assign density
	 */
	public Planet(double rad, double dens) {
		this.rad = rad;
		this.dens = dens;
	}
	
	/** set radius of planet in meters
	 * 
	 * @param rad is a double to set the radius
	 */
	public void setRad(double rad) {
		this.rad = rad;
	}
	
	/** get radius of planet in meters
	 * 
	 * @return a double for radius
	 */
	public double getRad() {
		return rad;
	}
	
	/**set dens of planet in kg/m3
	 * 
	 * @param dens a double to set the density
	 */
	public void setDens(double dens) {
		this.dens = dens;
	}
	
	/** get dens of planet in kg/m3
	 * 
	 * @return a double for density
	 */
	public double getDens() {
		return dens;
	}
	
	/**get volume of planet
	 * 
	 * @return a double for the volume of the planet
	 * 
	 * Formula = (4.0 * 3.0) Math.PI * (Math.pow(getRad(), 3.0);
	 */
	public double getVolume() {
		return (4.0/3.0) * Math.PI * Math.pow(getRad(), 3.0);
	}
	
	/**get mass of planet in kg
	 * 
	 * @return a double for the mass of the planet
	 * 
	 * Formula = getDens() * getVolume();
	 */
	public double getMass() {
		return getDens() * getVolume();
	}
	
}
