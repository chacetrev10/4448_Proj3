package store;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreObserver implements PropertyChangeListener {
	private List<String> availableInventory = 
			new ArrayList<>(Arrays.asList("spring", "egg","sausage","jelly","pastry" ));
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("The shop has just ran out of " + event.getNewValue() + " rolls!");
		availableInventory.remove(event.getNewValue());
		if(availableInventory.size() == 0) {
			System.out.println("The store has ran out of rolls and is closing for the day!");
		}
		
	}

}
