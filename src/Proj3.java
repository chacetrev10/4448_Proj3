import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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
	public static void main(String[] args) throws FileNotFoundException {
//		Uncomment this is you want the output printed to a file
//		Referenced https://stackoverflow.com/questions/1994255/how-to-write-console-output-to-a-txt-file
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		System.setOut(out);

		int[] maxRollOptions = {30, 45, 60};
		
		//Outer loop to try all 3 different options for maximum number of rolls
		for (int maximum: maxRollOptions) {
		
			System.out.printf("\n\n\n-------------------------%d ROLLS MAXIMUM-------------------------\n\n\n", maximum);
			
			// Create roll factory and store
			RollFactory factory = new RollFactory();
			RollStore store = new RollStore(factory, maximum);
	
			CustomerFactory casualFact = new CasualFactory();
			CustomerFactory buisnessFact = new BuisnessFactory();
			CustomerFactory cateringFact = new CateringFactory();
			
			// Define store observer and subscribe it the store
			StoreObserver observe = new StoreObserver();
			store.addPropertyChangeListener(observe);
			
			//Run unit tests before simulation begins
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
				for (int x = 0; x < numCatering; x++) {
					customerList.add(cateringFact.createCustomer("catering"));
				}
	
				//Assess order for each customer
				for (Customer c : customerList) {
					String[] order = c.orderComposition();
					boolean possible = store.checkOrderFeasability(order);
					//Check to see if order is possible
					if (!possible) {
						store.setAffected(1, c.customerType);
						order = c.retryOrder(store.getInventory());
					}
					//If order ends up working, record it as a sale
					if (order != null) {
						System.out.printf("New Order Customer Type: %s\n", c.customerType);
						System.out.println("Order: ");
						for (String type : order) {
							Roll currentRoll = store.sellRoll(type);
							if (currentRoll != null) {
								System.out.println(currentRoll.getDescription());
	//							System.out.printf("%.2f", currentRoll.getCost());
								store.setDaySales(currentRoll.getCost(), c.customerType);
	//							System.out.println();
							}
						}
						System.out.println();
					}
				}
				store.printInventory();
				
				//Record the sales breakdown by customer type
				Map<String, Double> salesByCustomer = store.getSalesByCustomerType();
				for( Map.Entry<String, Double> entry : salesByCustomer.entrySet() ){
				    System.out.printf("%s customers had %.2f in sales for the day", entry.getKey(), entry.getValue() );
				    System.out.println();
				}
				
				//Record the roll outages breakdown by customer type
				Map<String, Integer> outagesByCustomer = store.getOutagesByCustomerType();
				for( Map.Entry<String, Integer> entry : outagesByCustomer.entrySet() ){
				    System.out.printf("%s customers had %d outages during this day", entry.getKey(), entry.getValue() );
				    System.out.println();
				}
				
				//Reset sales and number of affected people for the day back to 0 for next day
				store.resetSalesByCustomerType();
				System.out.printf("The store had %.2f in total sales for the day\n", store.getDaySales());
				store.setTotalSales(store.getDaySales());
				store.resetDaySales();
				System.out.printf("The store had %d sales affected by outages today", store.getAffected());
				store.setTotalAffected(store.getAffected());
				store.makeRolls(maximum);
				store.setAffected(-1*store.getAffected(), "casual");
				store.resetOutagesByCustomerType();
				System.out.println();
			}
			
			observe.printTotalRollsSold();
			System.out.println();
			System.out.printf("Store made a total of %.2f dollars\n", store.getTotalSales());
			System.out.printf("There were a total of %d roll outage impacts", store.getTotalAffected());
		}
	}
}
