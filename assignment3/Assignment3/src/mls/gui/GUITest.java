package mls.gui;

import java.util.ArrayList;

import sait.mls.persistence.clientale.ClientBroker;
import sait.mls.persistence.property.CommercialPropertyBroker;
import sait.mls.persistence.property.ResidentialPropertyBroker;
import sait.mls.problemdomain.clientale.Client;
import sait.mls.problemdomain.property.CommercialProperty;
import sait.mls.problemdomain.property.ResidentialProperty;

public class GUITest {
	
	private ClientBroker cb;
	private ResidentialPropertyBroker rpb;
	private CommercialPropertyBroker cpb;
	
	
	public GUITest() 
	{
		cb = ClientBroker.getBroker();
		
		Client myClient = new Client();
		
		myClient.setLastName("Duck");
		
		ArrayList<Client> myList = (ArrayList<Client>) cb.search(myClient);
		
		for(Client c: myList) 
		{
			System.out.println(c);
		}
	}
	
	public void ResidentialPropertyTest() 
	{
		rpb = ResidentialPropertyBroker.getBroker();
		
		ResidentialProperty myResidential = new ResidentialProperty();
		
		myResidential.setGarage('A');
		
		ArrayList<ResidentialProperty> myResidentialList = (ArrayList<ResidentialProperty>) rpb.search(myResidential);
		
		for(ResidentialProperty rp : myResidentialList) 
		{
			System.out.println(rp);
		}
		
	}
	
	public void CommercialPropertyTest() 
	{
		cpb = CommercialPropertyBroker.getBroker();
		
		CommercialProperty myCommercial = new CommercialProperty();
		
		myCommercial.setQuadrant("SE");
		
		ArrayList<CommercialProperty> myCommercialList = (ArrayList<CommercialProperty>) cpb.search(myCommercial);
		
		for(CommercialProperty cp : myCommercialList) 
		{
			System.out.println(cp);
		}
		
	}

}
