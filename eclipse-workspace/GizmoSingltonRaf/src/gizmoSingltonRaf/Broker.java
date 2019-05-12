package gizmoSingltonRaf;

import java.util.List;

public interface Broker {
	
	//boolean is just to provide feedback if it worked
	public boolean persist (Object o);
	
	//List is the superclass of ArrayList
	public List search(String searchItem, String type);
	
	public boolean remove(Object o);
	
	public void closeBroker();

}
