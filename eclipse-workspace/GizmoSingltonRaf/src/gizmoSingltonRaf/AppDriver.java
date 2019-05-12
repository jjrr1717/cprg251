package gizmoSingltonRaf;

import java.util.List;

public class AppDriver {
	public static void main (String [] args) 
	{
		
		//because this is Singleton
		GizmoBroker gb = GizmoBroker.getGizmoBroker();
		
		//gb.printGizmo();
		
		/*System.out.println(gb.search("1005", "id"));
		System.out.println(gb.search("Piddley Pins", "description"));
		System.out.println(gb.search("321", "quantity"));
		System.out.println(gb.search("12.99", "price"));
		
		System.out.println(gb.search("20", "id"));*/
		
		List<Gizmo> sr = gb.search("1010", "id");
		for(Gizmo g: sr) 
		{
			System.out.print(g);
		}
	}
	
	

}
