package saitMLS.problemDomain;

import java.util.List;


import saitMLS.persistance.ClientBroker;

public class AppDriver {
	public static void main (String [] args) 
	{
		ClientBroker cb = ClientBroker.getClientBroker();
		cb.printClient();

		
		System.out.println("=============================================");

		List<Client> sr =cb.search("66", "Id");
		for(Client c: sr) 
		{
			System.out.println("The search result: " + c);
		}
		
		System.out.println("==================================");
		System.out.println(cb.highId);

		

		


	}

}
