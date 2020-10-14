package store;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rolls.ExtraFilling;
import rolls.ExtraSauce;
import rolls.ExtraTopping;
import rolls.Roll;

public class RollStore {

	private Map<String, List<Roll>> rollInventory = new HashMap<>();
	private Map<String, Double> salesByCustomerType = new HashMap<>();
	private Map<String, Integer> outagesByCustomerType = new HashMap<>();

	private RollFactory factory;
	private PropertyChangeSupport support;
	private double daySales = 0;
	private double totalSales = 0;
	private int affectedCustomers = 0;
	private int totalAffected = 0;
	
	public RollStore(RollFactory factory, int maxRolls) {
		//Create subject from RollStore
		support = new PropertyChangeSupport(this);
		this.factory = factory;
		
		//Load the different roll types into inventory
		rollInventory.put("spring", new ArrayList<Roll>());
		rollInventory.put("egg", new ArrayList<Roll>());
		rollInventory.put("jelly", new ArrayList<Roll>());
		rollInventory.put("sausage", new ArrayList<Roll>());
		rollInventory.put("pastry", new ArrayList<Roll>());
		//Populate inventory
		makeRolls(maxRolls);
		
		//Set initial sale numbers per customer type to 0
		salesByCustomerType.put("casual", 0.0);
		salesByCustomerType.put("business", 0.0);
		salesByCustomerType.put("catering", 0.0);
		
		//Set initial number of customers affected by outages to 0
		outagesByCustomerType.put("casual", 0);
		outagesByCustomerType.put("business", 0);
		outagesByCustomerType.put("catering", 0);
	}

	public void makeRolls(int maxNumRolls) {

		// the roll factory is called here when the inventory of any roll is 0
		for (String type : rollInventory.keySet()) {
			List<Roll> rollTypeCount = rollInventory.get(type);
			if (rollTypeCount.size() == 0) {
				for (int x = 0; x < maxNumRolls; x++) {
					Roll roll = factory.createRoll(type);
					rollTypeCount.add(roll);

				}
				rollInventory.put(type, rollTypeCount);
			}

		}
	}

	//Retrieve inventory listing
	public Map<String, List<Roll>> getInventory() {
		return rollInventory;
	}

	//Take roll out of inventory and sell it
	public Roll sellRoll(String type) {

		Roll retRoll = null;

		if (rollInventory.containsKey(type)) {
			List<Roll> rollTypeCount = rollInventory.get(type);
			if (rollTypeCount.size() > 0) {

				// decorator pattern occurs here by removing the roll, and adding on the random
				// values of extra toppings

				retRoll = rollTypeCount.remove(0);
				int numSauces = (int) (Math.random() * 4);
				int numFillings = (int) (Math.random() * 2);
				int numToppings = (int) (Math.random() * 3);
				for (int x = 0; x < numSauces; x++) {
					retRoll = new ExtraSauce(retRoll);
				}
				for (int x = 0; x < numFillings; x++) {
					retRoll = new ExtraFilling(retRoll);
				}
				for (int x = 0; x < numToppings; x++) {
					retRoll = new ExtraTopping(retRoll);
				}
//				if (rollTypeCount.size() == 0) {
					setTask(type);
//				}
			}

		}

		return retRoll;
	}

	// Handle tasks as passed to the observable
	// These tasks are observed by the observer
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	public void setTask(String newTask) {
		support.firePropertyChange("type", "placehold", newTask);

	}

	public void printInventory() {
		System.out.printf(
				"Number of jelly rolls in inventory %d\n" + "Number of spring rolls in inventory %d \n"
						+ "Number of egg rolls in inventory %d \n" + "Number of sausage rolls in inventory %d \n"
						+ "Number of pastry rolls in inventory %d \n",
				rollInventory.get("jelly").size(), rollInventory.get("spring").size(), rollInventory.get("egg").size(),
				rollInventory.get("sausage").size(), rollInventory.get("pastry").size());
	}

	//Check to see if order can be filled by current inventory
	public boolean checkOrderFeasability(String[] order) {
		List<String> orderToList = Arrays.asList(order);
		int jelly = Collections.frequency(orderToList, "jelly");
		int sausage = Collections.frequency(orderToList, "sausage");
		int egg = Collections.frequency(orderToList, "egg");
		int spring = Collections.frequency(orderToList, "spring");
		int pastry = Collections.frequency(orderToList, "pastry");
		if (jelly > rollInventory.get("jelly").size() || spring > rollInventory.get("spring").size()
				|| egg > rollInventory.get("egg").size() || sausage > rollInventory.get("sausage").size()
				|| pastry > rollInventory.get("pastry").size()) {

			return false;
		}
		return true;

	}

	//Get total sales for the day
	public double getDaySales() {
		return daySales;
	}
	
	//Retrieve sales numbers broken down by customer type
	public Map<String, Double> getSalesByCustomerType() {
		return salesByCustomerType;
	}
	
	//Retrieve outage data broken down by customer type
	public Map<String, Integer> getOutagesByCustomerType() {
		return outagesByCustomerType;
	}

	//Increase total sale number and sale number for specific customer type
	public void setDaySales(double rollPrice, String customerType) {
		daySales += rollPrice;
		salesByCustomerType.put(customerType, salesByCustomerType.get(customerType) + rollPrice); 
	}
	
	//Set total day sales back to 0 for next day
	public void resetDaySales() {
		daySales = 0;
	}
	
	//Reset day sales for each customer type back to 0 for next day
	public void resetSalesByCustomerType() {
		salesByCustomerType.put("casual", 0.0);
		salesByCustomerType.put("business", 0.0);
		salesByCustomerType.put("catering", 0.0);
	}

	//Retrieve the total number of people affected by roll outages in a day
	public int getAffected() {
		return affectedCustomers;
	}

	//Increase the total number of people affected by roll outages and the number for specific customer type
	public void setAffected(int num, String customerType) {
		affectedCustomers += num;
		outagesByCustomerType.put(customerType, outagesByCustomerType.get(customerType) + num);
	}
	
	//Reset number of roll outages for each customer type for one day back to 0 for next day
	public void resetOutagesByCustomerType() {
		outagesByCustomerType.put("casual", 0);
		outagesByCustomerType.put("business", 0);
		outagesByCustomerType.put("catering", 0);
	}
	
	//Keep track of the total number of sales during entire simulation
	public void setTotalSales(double num) {
		totalSales += num;
	}
	
	//Retrieve total sale numbers during entire simulation
	public double getTotalSales() {
		return totalSales;
	}
	
	//Keep track of total number of people affected by roll outages in entire simulation
	public void setTotalAffected(int num) {
		totalAffected += num;
	}
	
	//Retrieve total number of people affected by roll outages in entire simulation
	public int getTotalAffected() {
		return totalAffected;
	}

}
