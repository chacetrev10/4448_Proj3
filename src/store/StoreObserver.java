package store;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rolls.Roll;

public class StoreObserver implements PropertyChangeListener {
	//Keep track of what is in inventory
	private List<String> availableInventory = new ArrayList<>(
			Arrays.asList("spring", "egg", "sausage", "jelly", "pastry"));
	
	//Keep track of what has been sold
	private Map<String, Integer> totalSold = new HashMap<>();

	private int rollCount;

	//Observe the RollStore to see if a roll type runs out
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getNewValue().equals("end")) {
			availableInventory = new ArrayList<>(Arrays.asList("spring", "egg", "sausage", "jelly", "pastry"));
		} else {
			if (!totalSold.containsKey(event.getNewValue())) {
				totalSold.put((String) event.getNewValue(), 0);
			}

			int currentRollSold = totalSold.get(event.getNewValue()) + 1;
			totalSold.put((String) event.getNewValue(), currentRollSold);
			if (currentRollSold % rollCount == 0) {
				System.out.println("The shop has just ran out of " + event.getNewValue() + " rolls!");
				availableInventory.remove(event.getNewValue());
			}
			if (availableInventory.size() == 0) {
				System.out.println("The store has ran out of rolls and is closing for the day!");

			}
		}

	}
	//Print the number of each roll sold and the total number of rolls sold
	public void printTotalRollsSold() {
		System.out.printf(
				"\nNumber of jelly rolls sold %d\n" + "Number of spring rolls sold %d \n"
						+ "Number of egg rolls sold %d \n" + "Number of sausage rolls sold %d \n"
						+ "Number of pastry rolls sold %d \n",
				totalSold.get("jelly"), totalSold.get("spring"), totalSold.get("egg"),
				totalSold.get("sausage"), totalSold.get("pastry"));
		System.out.printf("Total number of rolls sold: %d", totalSold.get("jelly") + totalSold.get("spring") 
		+ totalSold.get("egg") + totalSold.get("sausage") + totalSold.get("pastry"));
	}
	
	public void setRollCount(int rollCount) {
		this.rollCount = rollCount ;
	}

}
