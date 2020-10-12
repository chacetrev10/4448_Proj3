import java.util.ArrayList;
import java.util.List;
import customer.Customer;
import customer.CustomerFactory;
import rolls.Roll;
import store.RollFactory;
import store.RollStore;
import store.StoreObserver;

public class Proj3 {
	public static void main(String[] args) {

		// Create roll factory and store
		RollFactory factory = new RollFactory();
		RollStore store = new RollStore(factory);

		CustomerFactory custFact = new CustomerFactory();

		// Define store observer and subscribe it the store
		StoreObserver observe = new StoreObserver();
		store.addPropertyChangeListener(observe);

		// Define daily customer load
		List<Customer> customerList = new ArrayList<>();
		int numCasual = (int) (Math.random() * 12) + 1;
		int numBuisness = (int) (Math.random() * 3 + 1);
		int numCatering = (int) (Math.random() * 3 + 1);

		// Create customer load using customer factory
		for (int x = 0; x < numCasual; x++) {
			customerList.add(custFact.createCustomer("casual"));
		}
		for (int x = 0; x < numBuisness; x++) {
			customerList.add(custFact.createCustomer("buisness"));
		}
		for (int x = 0; x < 8; x++) {
			customerList.add(custFact.createCustomer("catering"));
		}

		for (Customer c : customerList) {
			String[] order = c.orderComposition();
			for (String type : order) {
				Roll currentRoll = store.sellRoll(type);
				if (currentRoll != null) {
					System.out.println(currentRoll.getDescription());
					System.out.printf("%.2f",currentRoll.getCost());
					System.out.println();
				}
			}
		}
		store.printInventory();

	}
}
