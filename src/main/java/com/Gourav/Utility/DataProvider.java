package com.Gourav.Utility;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DataProvider {

	private List<String> cities = new ArrayList<String>();
	private List<String> states = new ArrayList<String>();
	private List<String> categories = new ArrayList<String>();
	private List<String> paymentModes = new ArrayList<String>();
	
	public List<String> getAllCities()
	{
		cities.add("Indore");
		cities.add("Mhow");
		cities.add("Gwalior");
		cities.add("Jaipur");
		cities.add("Surat");
		cities.add("Mumbai");
		cities.add("Kanpur");
		cities.add("Nagpur");
		cities.add("Bhopal");
		cities.add("Sagar");
		
		return cities;
	}
	
	public List<String> getAllStates()
	{
		states.add("Delhi");
		states.add("Maharastra");
		states.add("Madhya Pradesh");
		states.add("Uttar Pradesh");
		states.add("Chattisgarh");
		states.add("Gujrat");
		states.add("Goa");
		states.add("Bihar");
		states.add("West Bangal");
		states.add("Rajasthan");
		
		return states;
	}
	
	public List<String> getAllCategories()
	{
		categories.add("Handmade Card");
		categories.add("Handmade Box");
		categories.add("Scrapbook");
		categories.add("Wall Hanging");
		categories.add("Wind Chime");
		categories.add("Showcase Item");
		categories.add("Other");
		
		return categories;
	}
	
	public List<String> getAllPaymentModes()
	{
		paymentModes.add("Cash on Delivery");
		paymentModes.add("Paytm");
		paymentModes.add("Online Payment");
		
		return paymentModes;
	}
}
