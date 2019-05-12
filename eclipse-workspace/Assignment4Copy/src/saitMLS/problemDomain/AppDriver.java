package saitMLS.problemDomain;

import java.util.List;


import saitMLS.persistance.ClientBroker;

public class AppDriver {
	public static void main (String [] args) 
	{
		ClientBroker cb = ClientBroker.getClientBroker();
		cb.printClient();

		
		System.out.println("=============================================");

		List<Client> sr =cb.search("62", "Id");
		for(Client c: sr) 
		{
			System.out.print(c);
		}
		
		System.out.println("==================================");

		

		


	}

}
