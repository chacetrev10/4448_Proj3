package store;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rolls.Roll;

public class RollStore {

	private Map<String, List<Roll>> rollInventory = new HashMap<>();
	private RollFactory factory;
	private PropertyChangeSupport support;

	public RollStore(RollFactory factory) {
		this.factory = factory;
		support = new PropertyChangeSupport(this);
	}

	public void makeRolls(String type) {

		if (!rollInventory.containsKey(type)) {
			rollInventory.put(type, new ArrayList<Roll>());
		}
		List<Roll> rollTypeCount = rollInventory.get(type);
		if (rollTypeCount.size() < 30) {
			Roll roll = factory.createRoll(type);
			rollTypeCount.add(roll);
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
			if (rollTypeCount.size() == 0) {
				setTask(type);
				rollInventory.remove(type);
			} else {
				retRoll = rollTypeCount.remove(0);
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

}
