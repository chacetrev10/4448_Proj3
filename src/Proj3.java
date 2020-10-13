import java.util.ArrayList;
import java.util.List;

import customer.BuisnessFactory;
import customer.CasualFactory;
import customer.CateringFactory;
import customer.Customer;
import customer.CustomerFactory;
import rolls.Roll;
import store.RollFactory;
import store.RollStore;
import store.StoreObserver;
import test.MyUnitTest;

public class Proj3 {
	public static void main(String[] args) {

		// Create roll factory and store
		RollFactory factory = new RollFactory();
		RollStore store = new RollStore(factory);

		CustomerFactory casualFact = new CasualFactory();
		CustomerFactory buisnessFact = new BuisnessFactory();
		CustomerFactory cateringFact = new CateringFactory();
		
		// Define store observer and subscribe it the store
		StoreObserver observe = new StoreObserver();
		store.addPropertyChangeListener(observe);
		
		MyUnitTest unitTests = new MyUnitTest();
		unitTests.testEggRollCost();
		unitTests.testBusinessOrder();
		unitTests.testLenCasualOrder();
		unitTests.testLenCateringOrder();
		unitTests.testTypeCateringOrder();
		unitTests.testExtraFillingDescription();
		unitTests.testExtraFillingCost();
		unitTests.testExtraSauceCost();
		unitTests.testExtraTopCost();
		unitTests.testExtraSauceDescription();

		for (int day = 1; day < 31; day++) {
			System.out.printf("\nIt is the start of day %d\n", day);
			store.printInventory();
			
			
			

			// Define daily customer load
			List<Customer> customerList = new ArrayList<>();
			int numCasual = (int) (Math.random() * 12) + 1;
			int numBuisness = (int) (Math.random() * 3 + 1);
			int numCatering = (int) (Math.random() * 3 + 1);

			// Create customer load using customer factory
			for (int x = 0; x < numCasual; x++) {
				customerList.add(casualFact.createCustomer("casual"));
			}
			for (int x = 0; x < numBuisness; x++) {
				customerList.add(buisnessFact.createCustomer("buisness"));
			}
			for (int x = 0; x < 8; x++) {
				customerList.add(cateringFact.createCustomer("catering"));
			}

			for (Customer c : customerList) {
				String[] order = c.orderComposition();
				boolean possible = store.checkOrderFeasability(order);
				if (!possible) {
					store.setAffected(1);
					order = c.retryOrder(store.getInventory());
				}
				if (order != null) {
					for (String type : order) {
						Roll currentRoll = store.sellRoll(type);
						if (currentRoll != null) {
//						System.out.println(currentRoll.getDescription());
//						System.out.printf("%.2f", currentRoll.getCost());
							store.setDaySales(currentRoll.getCost());
//						System.out.println();
						}
					}
				}
			}
			store.printInventory();
			System.out.printf("The store had %.2f in total sales for the day\n", store.getDaySales());
			System.out.printf("The store had %d sales affected by outages today", store.getAffected());
			store.makeRolls();
			store.setAffected(-1*store.getAffected());
		}
		
		observe.printTotalRollsSold();
	}
}
