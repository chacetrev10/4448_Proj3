import customer.BuisnessCustomer;
import customer.CasualCustomer;
import customer.CateringCustomer;
import customer.Customer;
import rolls.ExtraFilling;
import rolls.ExtraSauce;
import rolls.ExtraTopping;
import rolls.JellyRoll;
import rolls.Roll;
import rolls.SausageRoll;
import store.RollFactory;
import store.RollStore;
import store.StoreObserver;

public class Proj3 {
	public static void main(String[]args) {
		RollFactory factory = new RollFactory();
		RollStore store = new RollStore(factory);
		StoreObserver observe = new StoreObserver();
		store.makeRolls("egg");
		store.makeRolls("jelly");
		store.addPropertyChangeListener(observe);
		Roll r1 = store.sellRoll("egg");
		r1 = store.sellRoll("egg");
		r1 = store.sellRoll("egg");
		
		Customer cus = new CateringCustomer();
		String [] order = cus.orderComposition();
		for(int x = 0; x < order.length; x++) {
			System.out.println(order[x]);
		}
		
	}
}
