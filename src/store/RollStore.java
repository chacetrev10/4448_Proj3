package store;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rolls.ExtraFilling;
import rolls.ExtraSauce;
import rolls.ExtraTopping;
import rolls.Roll;

public class RollStore {

	private Map<String, List<Roll>> rollInventory = new HashMap<>();

	private RollFactory factory;
	private PropertyChangeSupport support;

	public RollStore(RollFactory factory) {
		support = new PropertyChangeSupport(this);
		this.factory = factory;
		rollInventory.put("spring", new ArrayList<Roll>());
		makeRolls("spring");
		rollInventory.put("egg", new ArrayList<Roll>());
		makeRolls("egg");
		rollInventory.put("jelly", new ArrayList<Roll>());
		makeRolls("jelly");
		rollInventory.put("sausage", new ArrayList<Roll>());
		makeRolls("sausage");
		rollInventory.put("pastry", new ArrayList<Roll>());
		makeRolls("pastry");
	}

	public void makeRolls(String type) {
		
		//the roll factory is called here when the inventory of any roll is 0
		List<Roll> rollTypeCount = rollInventory.get(type);
		if (rollTypeCount.size() == 0) {
			for (int x = 0; x < 30; x++) {
				Roll roll = factory.createRoll(type);
				rollTypeCount.add(roll);

			}
			rollInventory.put(type, rollTypeCount);
		}

	}

	public Map<String, List<Roll>> getInventory() {
		return rollInventory;
	}

	public Roll sellRoll(String type) {

		Roll retRoll = null;

		if (rollInventory.containsKey(type)) {
			List<Roll> rollTypeCount = rollInventory.get(type);
			if (rollTypeCount.size() > 0) {
				
				// decorator pattern occurs here by removing the roll, and adding on the random
				// values of extra toppings

				retRoll = rollTypeCount.remove(0);
				int numSauces = (int) (Math.random() * 4);
				int numFillings = (int) (Math.random()*2);
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
				if (rollTypeCount.size() == 0) {
					setTask(type);
				}
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

}
