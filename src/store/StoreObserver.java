package store;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StoreObserver implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("The shop has just ran out of " + event.getNewValue() + " rolls!");
		
	}

}
